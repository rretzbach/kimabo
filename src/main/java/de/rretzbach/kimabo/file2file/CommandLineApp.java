/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Inject;
import de.rretzbach.kimabo.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author rretzbach
 */
public class CommandLineApp {
    private final SourceProvider<String> sourceProvider;
    private final TaskFactory<String, String> taskFactory;
    private ImageReader<String> imageReader;
    private ImageWriter<String> imageWriter;

    @Inject
    public CommandLineApp(SourceProvider<String> sourceProvider, TaskFactory<String, String> taskFactory, ImageReader<String> imageReader, ImageWriter<String> imageWriter) {
        this.sourceProvider = sourceProvider;
        this.taskFactory = taskFactory;
        this.imageReader = imageReader;
        this.imageWriter = imageWriter;
    }

    public void main() {
        List<String> sources = sourceProvider.getSources();
        Collections.sort(sources, new CaseInsensitiveStringComparator());

        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);

        Resizer<String, String> th = new Resizer<String, String>(imageReader, imageWriter);
        th.addAll(tasks);
        th.setNumberOfThreads(8);
        th.execute();
    }
}
