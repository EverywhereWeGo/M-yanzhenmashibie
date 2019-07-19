package com;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.PicUtil.bufferedImageToPicture;

public class C_CutImage {
    //根据二值化图片找出头尾空白的序号
    public static int[] getBlankIndex(String path) throws Exception {
        int[] index = new int[2];
        File file = new File(path);
        BufferedImage bi = ImageIO.read(file);

        int width = bi.getWidth();
        int height = bi.getHeight();
        int counts[] = new int[256];
        for (int i = 0; i < width; i++) {
            counts[i] = 0;
            for (int j = 0; j < height; j++) {
                int pixels = bi.getRGB(i, j);
                int r = (pixels >> 16) & 0xff;
                System.out.print(r + "\t");
                counts[i] += (r - 255);
            }
            System.out.println("counts:" + counts[i]);
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                index[0] = i;
                break;
            }
        }
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] != 0) {
                index[1] = i;
                break;
            }
        }
        return index;
    }

    public static void cut(String inputPath, String outPath) throws Exception {
        int[] index = getBlankIndex(inputPath);
        System.out.println(index[0] + " " + index[1]);

        BufferedImage image = ImageIO.read(new File(inputPath));
        int width = image.getWidth();
        int height = image.getHeight();
        //把两边空白去掉
        BufferedImage subImage = image.getSubimage(index[0], 0, index[1] - index[0], height);

        int subwidth = subImage.getWidth();
        int subheight = subImage.getHeight();
        int l = subwidth / 4;
        //按宽度分成四份
        BufferedImage subImage1 = subImage.getSubimage(0 * l, 0, l, height);
        BufferedImage subImage2 = subImage.getSubimage(1 * l, 0, l, height);
        BufferedImage subImage3 = subImage.getSubimage(2 * l, 0, l, height);
        BufferedImage subImage4 = subImage.getSubimage(3 * l, 0, l, height);

        bufferedImageToPicture(subImage1, outPath + "cut1.jpg");
        bufferedImageToPicture(subImage2, outPath + "cut2.jpg");
        bufferedImageToPicture(subImage3, outPath + "cut3.jpg");
        bufferedImageToPicture(subImage4, outPath + "cut4.jpg");
    }

    public static void main(String[] args) throws Exception {
        cut("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\1(1)_binary.jpg", "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\");


    }
}
