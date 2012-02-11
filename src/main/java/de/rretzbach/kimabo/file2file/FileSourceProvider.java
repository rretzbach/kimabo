/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Inject;
import de.rretzbach.kimabo.SourceProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
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
        return findImageFilesRecursive(baseDirectory);
    }

    public List<String> findImageFilesRecursive(String baseDirectory) {
        List<File> sources = new ArrayList<File>(FileUtils.listFiles(new File(baseDirectory), new RegexFileFilter("^.*\\.(?i)(jpg|png)$"), DirectoryFileFilter.DIRECTORY));

        List<String> stringSources = new LinkedList<String>();
        for (File source : sources) {
            stringSources.add(source.getAbsolutePath());
        }
        return stringSources;
    }
}
