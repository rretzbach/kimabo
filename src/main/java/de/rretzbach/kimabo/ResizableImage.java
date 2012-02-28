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

public class ResizableImage {

    private BufferedImage image;

    ResizableImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Reduces the colors in the underlying image to grayscale
     *
     * @return instance for fluent interface
     */
    public ResizableImage desaturate() {
        BufferedImageOp grayscaleConv =
                new ColorConvertOp(image.getColorModel().getColorSpace(), ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        grayscaleConv.filter(image, null);
        return this;
    }

    /**
     * Resizes the underlying BufferedImage to the new target dimensions. The aspect ratio will not change.
     * The image will be rotated if this allows the image to be larger.
     *
     * @param preferredWidth
     * @param preferredHeight
     * @return instance for fluent interface
     */
    public ResizableImage resize(int preferredWidth, int preferredHeight) {
        double resizeFactor = computeResizeFactor(preferredWidth, preferredHeight);

        AffineTransform transform = new AffineTransform();

        if (image.getWidth() > image.getHeight()) {
            transform.translate(0d, +image.getWidth() * resizeFactor);
            transform.rotate(Math.toRadians(270), 0d, 0d);
        }

        transform.scale(resizeFactor, resizeFactor);

        image = new AffineTransformOp(
                transform,
                AffineTransformOp.TYPE_BILINEAR).filter(image, null);

        return this;
    }

    /**
     * @param preferredWidth
     * @param preferredHeight
     * @return a factor which can be used to compute the optimal target dimensions
     */
    public double computeResizeFactor(int preferredWidth, int preferredHeight) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        double resizeFactor = (double) preferredHeight / (double) sourceHeight;

        // if source image orientation is landscape
        if (resizeFactor * sourceWidth > preferredWidth) {
            // then change factor so that width won't exceed 600px even if height will be smaller than 800px
            resizeFactor = (double) preferredWidth / (double) sourceWidth;
        }
        return resizeFactor;
    }

    /**
     * @return the underlying BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }
}
