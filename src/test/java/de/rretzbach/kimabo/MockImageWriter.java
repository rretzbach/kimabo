/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.awt.image.BufferedImage;

/**
 * @author rretzbach
 */
public class MockImageWriter implements ImageWriter<String> {

    public void write(BufferedImage image, String dest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
