/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import com.google.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rretzbach
 */
public class TaskFactory<S, T> {

    private final TargetFinder<S, T> targetFinder;
    private final int pagesPerBook;

    @Inject
    public TaskFactory(ImageReader<S> imageReader, ImageWriter<T> imageWriter, TargetFinder<S, T> targetFinder, @SeriesName String seriesName, @PagesPerBook int pagesPerBook) {
        this.targetFinder = targetFinder;
        this.pagesPerBook = pagesPerBook;
    }

    public Set<ResizeTask<S, T>> sliceResizeTasks(List<S> sources) {
        Set<ResizeTask<S, T>> tasks = new HashSet<ResizeTask<S, T>>();

        int book = 0;
        int page = 0;
        for (S source : sources) {
            if (page == pagesPerBook) {
                ++book;
                page = 0;
            }
            tasks.add(new ResizeTask<S, T>(source, targetFinder.find(source, book, page++)));
        }

        System.out.println("tasks: " + tasks);

        return tasks;
    }
}
