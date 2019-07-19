package com;

import sun.misc.IOUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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


    public static void bufferedImageToPicture(BufferedImage img, String path) throws IOException {
        //bufferedimage 转换成 inputstream
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(img, "jpg", imOut);
        InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());

        OutputStream outStream = new FileOutputStream(path);
        inputStream.close();
        outStream.close();
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
