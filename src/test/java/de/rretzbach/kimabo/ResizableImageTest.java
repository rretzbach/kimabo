package de.rretzbach.kimabo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.image.BufferedImage;

@RunWith(MockTestRunner.class)
public class ResizableImageTest {

    protected ResizableImage resizableImage;
    protected double tolerance = 0.001d;

    @Before
    public void setUp() {
        resizableImage = new ResizableImage(new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB));
    }

    @Test
    public void shouldScaleDownSimple() {
        double resizeFactor = resizableImage.computeResizeFactor(50, 100);
        Assert.assertEquals(0.5d, resizeFactor, tolerance);
    }

    @Test
    public void shouldScaleUpSimple() {
        double resizeFactor = resizableImage.computeResizeFactor(200, 400);
        Assert.assertEquals(2d, resizeFactor, tolerance);
    }

    @Test
    public void shouldScaleDownEvenIfAspectRatioDiffers() {
        double resizeFactor = resizableImage.computeResizeFactor(50, 80);
        Assert.assertEquals(0.4d, resizeFactor, tolerance);
    }


    @Test
    public void shouldScaleDownAndSwitchOrientationIfNeeded() {
        double resizeFactor = resizableImage.computeResizeFactor(80, 50);
        Assert.assertEquals(0.25d, resizeFactor, tolerance);
    }

    @Test
    public void shouldScaleDownWidthIfLandscape() {
        ResizableImage resizableImage = new ResizableImage(new BufferedImage(2400, 1200, BufferedImage.TYPE_INT_RGB));
        double resizeFactor = resizableImage.computeResizeFactor(600, 800);
        Assert.assertEquals(0.25d, resizeFactor, tolerance);
    }
}
