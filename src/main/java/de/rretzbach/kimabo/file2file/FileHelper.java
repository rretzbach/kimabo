/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rretzbach
 */
public class FileHelper {
    // TODO: exclude dot files
    // TODO: include only jpg and png files
    public static List<String> findImageFilesRecursive(String baseDirectory) {
        System.out.println("baseDirectory: " + baseDirectory);
        List<File> sources = new ArrayList<File>(FileUtils.listFiles(new File(baseDirectory), new AndFileFilter(FileFileFilter.FILE, FileFileFilter.FILE), DirectoryFileFilter.DIRECTORY));

        List<String> stringSources = new LinkedList<String>();
        for (File source : sources) {
            stringSources.add(source.getAbsolutePath());
        }
        return stringSources;
    }
}
