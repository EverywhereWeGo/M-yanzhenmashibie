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
                counts[i] += r;
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


    //把图片平均分成四份
    public void cut4(String path) throws Exception {
        BufferedImage image = ImageIO.read(new File(path));

        int width = image.getWidth();
        int height = image.getHeight();
        int l = width / 4;


        BufferedImage subImage1 = image.getSubimage(0 * l, 0, l, height);
        BufferedImage subImage2 = image.getSubimage(1 * l, 0, l, height);
        BufferedImage subImage3 = image.getSubimage(2 * l, 0, l, height);
        BufferedImage subImage4 = image.getSubimage(3 * l, 0, l, height);


        bufferedImageToPicture(subImage1, "C:\\Users\\Administrator\\Desktop\\我的代码\\yanzhenmashibie\\cut1.jpg");
        bufferedImageToPicture(subImage2, "C:\\Users\\Administrator\\Desktop\\我的代码\\yanzhenmashibie\\cut2.jpg");
        bufferedImageToPicture(subImage3, "C:\\Users\\Administrator\\Desktop\\我的代码\\yanzhenmashibie\\cut3.jpg");
        bufferedImageToPicture(subImage4, "C:\\Users\\Administrator\\Desktop\\我的代码\\yanzhenmashibie\\cut4.jpg");


    }

    public static void cutBlank(String path) throws Exception {
        int[] index = getBlankIndex(path);
        System.out.println(index[0] + " " + index[1]);

        BufferedImage image = ImageIO.read(new File(path));

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage subImage = image.getSubimage(index[0], 0, index[1] - index[0], height);

        bufferedImageToPicture(subImage, "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\cut.jpg");
    }

    public static void main(String[] args) throws Exception {
        cutBlank("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\1 (1)_binary.jpg");
//        rc.cut4();


    }
}
