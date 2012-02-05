/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import com.google.inject.Inject;
import java.util.*;

/**
 *
 * @author rretzbach
 */
public class Resizer {

    private final TargetFinder<String, String> targetFinder;
    private final int pagesPerBook;

    @Inject
    public Resizer(ImageReader imageReader, ImageWriter imageWriter, TargetFinder targetFinder, @SeriesName String seriesName, @PagesPerBook int pagesPerBook) {
        this.targetFinder = targetFinder;
        this.pagesPerBook = pagesPerBook;
    }

    public Set<ResizeTask> sliceResizeTasks(List<String> sources) {
        Set<ResizeTask> tasks = new HashSet<ResizeTask>();

        int book = 0;
        int page = 0;
        for (String source : sources) {
            if (page == pagesPerBook) {
                ++book;
                page = 0;
            }
            tasks.add(new ResizeTask(source, targetFinder.find(source, book, page++)));
        }

        return tasks;
    }
}
