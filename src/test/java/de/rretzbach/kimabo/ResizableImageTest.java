package de.rretzbach.kimabo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.image.BufferedImage;

@RunWith(MockTestRunner.class)
public class ResizableImageTest {

    protected ResizableImage resizableImage;
    protected double tolerance = 0.01d;
    protected int preferredWidth = 600;
    protected int preferredHeigt = 800;

    @Before
    public void setUp() {
        resizableImage = new ResizableImage(new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB));
    }

    public void shouldScaleToPreferred(int sourceWidth, int sourceHeight, double expectedFactor) {
        ResizableImage resizableImage = new ResizableImage(new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_INT_RGB));
        double resizeFactor = resizableImage.computeResizeFactor(preferredWidth, preferredHeigt);
        Assert.assertEquals(expectedFactor, resizeFactor, tolerance);
    }

    @Test
    public void shouldScaleDownIfLandscapeAndWiderThanPreferred() {
        shouldScaleToPreferred(1180, 1099, 0.55d);
    }

    @Test
    public void shouldScaleDownIfLandscapeAndNarrowerThanPreferred() {
        shouldScaleToPreferred(1456, 1084, 0.55d);
    }

    @Test
    public void shouldScaleDownIfPortraitAndWiderThanPreferred() {
        shouldScaleToPreferred(987, 1084, 0.61d);
    }

    @Test
    public void shouldScaleDownIfPortraitAndNarrowerThanPreferred() {
        shouldScaleToPreferred(728, 1117, 0.72d);
    }

    @Test
    public void shouldScaleUpIfLandscapeAndWiderThanPreferred() {
        shouldScaleToPreferred(118, 109, 5.5d);
    }

    @Test
    public void shouldScaleUpIfLandscapeAndNarrowerThanPreferred() {
        shouldScaleToPreferred(145, 108, 5.52d);
    }

    @Test
    public void shouldScaleUpIfPortraitAndWiderThanPreferred() {
        shouldScaleToPreferred(98, 108, 6.12d);
    }

    @Test
    public void shouldScaleUpIfPortraitAndNarrowerThanPreferred() {
        shouldScaleToPreferred(72, 111, 7.2d);
    }
}
