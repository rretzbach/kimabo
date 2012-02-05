/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

/**
 *
 * @author rretzbach
 */
public class FileHelper {
    public static List<String> findImageFilesRecursive(String baseDirectory) {
        String[] sources = new File(baseDirectory).list(new AndFileFilter(FileFileFilter.FILE, FileFileFilter.FILE) );

        return Arrays.asList(sources);
    }
}
