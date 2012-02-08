/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.awt.image.BufferedImage;

/**
 * @author rretzbach
 */
public interface ImageReader<T> {
    BufferedImage read(T source);
}
