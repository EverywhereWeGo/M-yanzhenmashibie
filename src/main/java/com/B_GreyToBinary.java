package com;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.PicUtil.bufferedImageToPicture;
import static com.PicUtil.colorToRGB;

public class B_GreyToBinary {
    public static void binaryImage(String imagePath, String outPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));

        int width = image.getWidth();
        int height = image.getHeight();
        //大津法计算阈值
        int maxT = -1;
        double maxICV = -1;
        for (int T = 0; T < 256; T++) {
            int countA = 0;
            int grayAs = 0;
            int countB = 0;
            int grayBs = 0;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int color = image.getRGB(i, j);
                    int gray = (color >> 16) & 0xff;
                    if (T > gray) {
                        countA++;
                        grayAs += gray;
                    } else {
                        countB++;
                        grayBs += gray;
                    }
                }
            }
            if (countA == 0 || countB == 0) {
                continue;
            }
            double w0 = (double) countA / width / height;
            double w1 = (double) countB / width / height;

            double u0 = (double) grayAs / countA;
            double u1 = (double) grayBs / countB;

            double ICV = w0 * w1 * (u0 - u1) * (u0 - u1);

            if (ICV > maxICV) {
                maxICV = ICV;
                maxT = T;
            }
        }
        System.out.println("maxT:" + maxT);


        BufferedImage binaryImage = new BufferedImage(width, height, image.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = image.getRGB(i, j);
                int gray = (color >> 16) & 0xff;
                if (maxT < gray) {
                    binaryImage.setRGB(i, j, colorToRGB(255, 255, 255));
                } else {
                    binaryImage.setRGB(i, j, colorToRGB(0, 0, 0));
                }
            }
        }
        bufferedImageToPicture(binaryImage, outPath);
    }


    public static void main(String args[]) throws IOException {
        String inputpath = "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\";
        String filename = "1";
        String suffix = ".jpg";

        String outPath = "C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\结果\\";

        binaryImage(inputpath + filename + "_gray" + suffix, outPath + filename + "_binary" + suffix);
    }


}