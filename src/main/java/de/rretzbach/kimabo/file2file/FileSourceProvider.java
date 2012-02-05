/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Provider;
import de.rretzbach.kimabo.SourceProvider;
import java.util.List;

/**
 *
 * @author rretzbach
 */
public class FileSourceProvider implements SourceProvider {

    private final String baseDirectory;

    public FileSourceProvider(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public List<String> getSources() {
        return FileHelper.findImageFilesRecursive(baseDirectory);
    }
}
