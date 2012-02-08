/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import de.rretzbach.kimabo.ImageReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rretzbach
 */
public class FileImageReader implements ImageReader<String> {

    public FileImageReader() {
    }

    public BufferedImage read(String source) {
        System.out.println("source: " + source);
        try {
            return ImageIO.read(new File(source));
        } catch (IOException ex) {
            Logger.getLogger(FileImageReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
