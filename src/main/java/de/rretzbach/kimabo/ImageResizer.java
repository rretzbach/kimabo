/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import sun.awt.image.BufferedImageGraphicsConfig;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rretzbach
 */
public class ImageResizer {

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public static BufferedImage resizeTrick(BufferedImage image, int width, int height) {
        image = createCompatibleImage(image);

        // TODO: rotate and scale in one step
        if (image.getWidth() > image.getHeight()) {
            // rotate anti clock wise 90 degrees
            // this is most comfortable for righties
            //AffineTransform trans = new AffineTransform();
            //trans.rotate(Math.toRadians(270), image.getWidth() / 2, image.getHeight() / 2);
            //AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BICUBIC);
            //image = op.filter(image, new BufferedImage(image.getHeight(), image.getWidth(),
            //        image.getType()));
            // TODO: when rotated the image is always widthxwidth, but should be heightxwidth
            BufferedImage rotated = new AffineTransformOp(
                    AffineTransform.getQuadrantRotateInstance(
                            3, image.getWidth() / 2, image.getHeight() / 2),
                    AffineTransformOp.TYPE_BILINEAR).filter(image, null);
            System.out.println(rotated.getHeight());
            return rotated;
        }

        double resizeFactor = shrink(image.getWidth(), image.getHeight(), 600, 800);
        return resize(image, (int)(image.getWidth() * resizeFactor), (int)(image.getHeight() * resizeFactor));
    }

    private static double shrink(int width, int height, int preferredWidth, int preferredHeight) {
        double resizeFactor = ((double) preferredHeight) / ((double) height);
        // if source image format is wider than 600x800
        if (resizeFactor * width > preferredWidth) {
            // then change factor so, that width won't exceed 600px even if height will be smaller than 800px
            resizeFactor = ((float) preferredWidth) / ((float) width);
        }
        return resizeFactor;
    }

    private static BufferedImage blurImage(BufferedImage image) {
        float ninth = 1.0f / 9.0f;
        float[] blurKernel = {
                ninth, ninth, ninth,
                ninth, ninth, ninth,
                ninth, ninth, ninth
        };

        Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints hints = new RenderingHints(map);
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
        return op.filter(image, null);
    }

    private static BufferedImage createCompatibleImage(BufferedImage image) {
        GraphicsConfiguration gc = BufferedImageGraphicsConfig.getConfig(image);
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = gc.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        Graphics2D g2 = result.createGraphics();
        g2.drawRenderedImage(image, null);
        g2.dispose();
        return result;
    }
}
