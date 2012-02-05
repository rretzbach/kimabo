/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.awt.image.BufferedImage;

/**
 *
 * @author rretzbach
 */
public interface ImageWriter<T> {
    void write(BufferedImage image, T dest);
}
