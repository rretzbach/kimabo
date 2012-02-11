/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

/**
 * @author rretzbach
 */
public class ImageHelper {

    public static final int PREFERRED_WIDTH = 600;
    public static final int PREFERRED_HEIGHT = 800;

    public BufferedImage desaturate(BufferedImage image) {
        BufferedImageOp grayscaleConv =
                new ColorConvertOp(image.getColorModel().getColorSpace(), ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        return grayscaleConv.filter(image, null);
    }

    public BufferedImage resize(BufferedImage image) {
        double resizeFactor = getResizeFactor(image.getWidth(), image.getHeight());

        AffineTransform transform = new AffineTransform();

        if (image.getWidth() > image.getHeight()) {
            transform.translate(0d, +image.getWidth() * resizeFactor);
            transform.rotate(Math.toRadians(270), 0d, 0d);
        }

        transform.scale(resizeFactor, resizeFactor);

        image = new AffineTransformOp(
                transform,
                AffineTransformOp.TYPE_BILINEAR).filter(image, null);

        return image;
    }

    private double getResizeFactor(int width, int height) {
        // image will be rotated, so compute the correct resize factor
        if (width > height) {
            return getResizeFactor(height, width);
        }

        double resizeFactor = (double) PREFERRED_HEIGHT / (double) height;
        // if source image orientation is landscape
        if (resizeFactor * width > PREFERRED_WIDTH) {
            // then change factor so that width won't exceed 600px even if height will be smaller than 800px
            resizeFactor = (double) PREFERRED_WIDTH / (double) width;
        }
        return resizeFactor;
    }

}
