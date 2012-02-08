package de.rretzbach.kimabo.file2file;

import de.rretzbach.kimabo.TargetFinder;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: rretzbach
 * Date: 07.02.12
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class DirTargetFinder implements TargetFinder<String, String> {
    @Override
    public String find(String source, int book, int page) {
        String file = "/Users/rretzbach/Documents/workspace/kimabo/kindle/" + (new File(source).getName());

        return file;
    }
}
