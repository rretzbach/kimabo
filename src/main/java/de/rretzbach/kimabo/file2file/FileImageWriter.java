/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import de.rretzbach.kimabo.ImageWriter;
import java.awt.image.BufferedImage;

/**
 *
 * @author rretzbach
 */
public class FileImageWriter implements ImageWriter<String> {

    public FileImageWriter() {
    }

    public void write(BufferedImage image, String dest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
