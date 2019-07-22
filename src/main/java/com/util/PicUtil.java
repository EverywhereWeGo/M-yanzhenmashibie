package com.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class PicUtil {
    //将BufferedImage输出成文件,此方法图片不会失真
//    public static void bufferedImageToPicture(BufferedImage bi, String path) throws IOException {
//        Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpeg");
//        ImageWriter imageWriter = iterator.next();
//        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
//        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        imageWriteParam.setCompressionQuality(1.0F);
//        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
//        imageWriter.setOutput(imageOutput);
//        IIOImage iio_image = new IIOImage(bi, null, null);
//        imageWriter.write(null, iio_image, imageWriteParam);
//        imageWriter.dispose();
//    }


//    public static void bufferedImageToPicture(BufferedImage img, String path) throws IOException {
//        //bufferedimage 转换成 inputstream
//        ByteArrayOutputStream bs = new ByteArrayOutputStream();
//        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
//        ImageIO.write(img, "jpg", imOut);
//        InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());
//
//        OutputStream outStream = new FileOutputStream(path);
//        inputStream.close();
//        outStream.close();
//    }




//    public static void bufferedImageToPicture(BufferedImage img, String path) throws IOException {
////    private void createImage(String fileLocation) {
//        try {
//            FileOutputStream fos = new FileOutputStream(path);
//            BufferedOutputStream bos = new BufferedOutputStream(fos);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
//            encoder.encode(img);
//            bos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static void bufferedImageToPicture(BufferedImage img, String path) throws IOException {
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");//自定义图像格式
        ImageWriter writer = it.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(new File(path));//自定义图像路径

        writer.setOutput(ios);
        writer.write(img);
        img.flush();
        ios.flush();
    }


    /**
     * 颜色分量转换为RGB值
     *
     * @param alpha
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public static int colorToRGB(int red, int green, int blue) {
        Color color = new Color(red, green, blue);
        int rgb = color.getRGB();
        return rgb;

    }


}
