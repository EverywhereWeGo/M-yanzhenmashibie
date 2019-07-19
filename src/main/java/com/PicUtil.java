package com;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class PicUtil {
    //将BufferedImage输出成文件,此方法图片不会失真
    public static void bufferedImageToPicture(BufferedImage bi, String path) throws IOException {
        Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter imageWriter = iterator.next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(1);
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageWriter.setOutput(imageOutput);
        IIOImage iio_image = new IIOImage(bi, null, null);
        imageWriter.write(null, iio_image, imageWriteParam);
        imageWriter.dispose();
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
