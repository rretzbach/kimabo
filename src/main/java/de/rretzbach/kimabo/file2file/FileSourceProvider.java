/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Inject;
import de.rretzbach.kimabo.BaseInputDir;
import de.rretzbach.kimabo.SourceProvider;

import java.util.List;

/**
 * @author rretzbach
 */
public class FileSourceProvider implements SourceProvider<String> {

    private final String baseDirectory;

    @Inject
    public FileSourceProvider(@BaseInputDir String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public List<String> getSources() {
        List<String> sources = FileHelper.findImageFilesRecursive(baseDirectory);
        System.out.println("sources: " + sources);
        return sources;
    }
}
