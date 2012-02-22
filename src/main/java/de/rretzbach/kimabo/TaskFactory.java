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
    private String seriesName;
    private final int pagesPerBook;
    private int beginBookIndex = 0;

    @Inject
    public TaskFactory(ImageReader<S> imageReader, ImageWriter<T> imageWriter, TargetFinder<S, T> targetFinder, @SeriesName String seriesName, @PagesPerBook int pagesPerBook, @BeginBookIndex int beginBookIndex) {
        this.targetFinder = targetFinder;
        this.seriesName = seriesName;
        this.pagesPerBook = pagesPerBook;
        this.beginBookIndex = beginBookIndex;
    }

    public Set<ResizeTask<S, T>> sliceResizeTasks(List<S> sources) {
        return sliceResizeTasks(sources, beginBookIndex);
    }

    public Set<ResizeTask<S, T>> sliceResizeTasks(List<S> sources, int beginIndex) {
        Set<ResizeTask<S, T>> tasks = new HashSet<ResizeTask<S, T>>();

        int book = beginIndex;
        int page = 0;
        for (S source : sources.subList(pagesPerBook * beginIndex, sources.size())) {
            if (page == pagesPerBook) {
                ++book;
                page = 0;
            }
            tasks.add(new ResizeTask<S, T>(source, targetFinder.find(source, seriesName, book, page++)));
        }

        return tasks;
    }
}
