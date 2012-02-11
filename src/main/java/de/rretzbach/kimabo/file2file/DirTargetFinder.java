package de.rretzbach.kimabo.file2file;

import de.rretzbach.kimabo.TargetFinder;

/**
 * Targets are file paths including Book directories
 * User: rretzbach
 * Date: 07.02.12
 * Time: 21:02
 */
public class DirTargetFinder implements TargetFinder<String, String> {
    @Override
    public String find(String source, String seriesName, int book, int page) {
        return String.format("kindle/%s %02d/%05d.%s", seriesName, book + 1, page + 1, "png");
    }
}
