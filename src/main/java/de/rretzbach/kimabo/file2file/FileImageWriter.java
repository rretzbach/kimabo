/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import de.rretzbach.kimabo.ImageWriter;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rretzbach
 */
public class FileImageWriter implements ImageWriter<String> {

    public FileImageWriter() {
    }

    public void write(BufferedImage image, String dest) {
        try {
            File file = new File(dest);
            // TODO: move this piece it doesn't have to be executed for EVERY file... but only once
            if (!file.getParentFile().exists()) {
                FileUtils.forceMkdir(file.getParentFile());
            }

            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            Logger.getLogger(FileImageWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
