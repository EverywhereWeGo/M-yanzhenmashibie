package com;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadColorTest {
    /**
     * 读取一张图片的RGB值
     *
     * @throws Exception
     */
    public void getImagePixel(String image) throws Exception {
        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        System.out.println("width=" + width + ",height=" + height + ".");
        for (int i = 0; i < width; i++) {
            int pixel = bi.getRGB(i, 0);
            int r = (pixel & 0xff0000) >> 16;
            int g = (pixel & 0xff00) >> 8;
            int b = (pixel & 0xff);
            long count = 0;

            for (int j = 0; j < height; j++) {
                int pixels = bi.getRGB(i, j);
                // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixels & 0xff0000) >> 16;
                rgb[1] = (pixels & 0xff00) >> 8;
                rgb[2] = (pixels & 0xff);

                count += (r - rgb[0]) * (r - rgb[0]) + (g - rgb[1]) * (g - rgb[1]) + (b - rgb[2]) * (b - rgb[2]);
//                System.out.print("i=" + i + ",j=" + j + ":(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
            }
//            System.out.println();
            System.out.println(count);

        }
    }




    //二值化
    public void binaryImage() throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\TYPE_BYTE_GRAY.jpg");
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);

            }
        }

        File newFile = new File("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\TYPE_BYTE_BINARY.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }

    //灰度化
    public void grayImage() throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\yanzhenma (2).jpg");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File("C:\\Users\\Administrator\\Desktop\\我的代码\\验证码素材\\TYPE_BYTE_GRAY.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }


    public static void main(String[] args) throws Exception {
        ReadColorTest rc = new ReadColorTest();
        rc.getImagePixel("C:\\Users\\Administrator\\Desktop\\我的代码\\yanzhenmashibie\\com.binary.jpg");
//        rc.grayImage();
//        rc.binaryImage();
    }


}