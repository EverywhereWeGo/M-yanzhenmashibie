package com;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.A_PicToGrey.grayImage;
import static com.B_GreyToBinary.binaryImage;
import static com.util.PicUtil.bufferedImageToPicture;

public class C_CutImage {
    //根据二值化图片找出头尾空白的序号
    public static int[] getBlankIndex1(String path) throws Exception {
        int[] index = new int[2];
        File file = new File(path);
        BufferedImage bi = ImageIO.read(file);

        int width = bi.getWidth();
        int height = bi.getHeight();
        int counts_wight[] = new int[width];

        for (int i = 0; i < width; i++) {
            counts_wight[i] = 0;
            for (int j = 0; j < height; j++) {
                int pixels = bi.getRGB(i, j);
                int r = (pixels >> 16) & 0xff;
                counts_wight[i] += (255 - r);
            }
            System.out.println("counts_wight:" + counts_wight[i]);
        }

        for (int i = 0; i < counts_wight.length; i++) {
            if (counts_wight[i] != 0) {
                index[0] = i;
                break;
            }
        }
        for (int i = counts_wight.length - 1; i >= 0; i--) {
            if (counts_wight[i] != 0) {
                index[1] = i;
                break;
            }
        }
        return index;
    }

    //根据二值化图片找出上下空白的序号
    public static int[] getBlankIndex2(BufferedImage bi) throws Exception {
        int[] index = new int[2];

        int width = bi.getWidth();
        int height = bi.getHeight();

        int counts_height[] = new int[height];

        for (int i = 0; i < height; i++) {
            counts_height[i] = 0;
            for (int j = 0; j < width; j++) {
                int pixels = bi.getRGB(j, i);
                int r = (pixels >> 16) & 0xff;
                counts_height[i] += (255 - r);
            }
            System.out.println("counts_height:" + counts_height[i]);
        }
        for (int i = 0; i < counts_height.length; i++) {
            if (counts_height[i] != 0) {
                index[0] = i;
                break;
            }
        }

        for (int i = counts_height.length - 1; i >= 0; i--) {
            if (counts_height[i] != 0) {
                index[1] = i;
                break;
            }
        }
        return index;
    }

    public static void cut(String inputPath, String outPath) throws Exception {
        int[] index = getBlankIndex1(inputPath);
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
        for (int i = 1; i <= 4; i++) {
            BufferedImage subImage1 = subImage.getSubimage(0 * l * i, 0, l, height);
            int[] index1 = getBlankIndex2(subImage1);
            System.out.println(index1[0] + "," + index1[1]);
            BufferedImage subImage11 = subImage1.getSubimage(0, index1[0], subImage1.getWidth(), index1[1] - index1[0] + 1);
            bufferedImageToPicture(subImage11, outPath + i + ".png");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            String imagepath = "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\down\\";

            String inputpath = "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\";
            String filename = String.valueOf(i);
            String suffix = ".png";

            String outPath = "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\";


            grayImage(4, imagepath + filename + ".jpg", outPath + filename + "_gray" + suffix);//加权法灰度化


            binaryImage(inputpath + filename + "_gray" + suffix, outPath + filename + "_binary" + suffix);


            cut(inputpath + filename + "_binary" + suffix, outPath + filename);
        }

    }
}
