/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Inject;
import de.rretzbach.kimabo.ResizeTask;
import de.rretzbach.kimabo.Resizer;
import de.rretzbach.kimabo.SourceProvider;
import de.rretzbach.kimabo.TaskFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This concrete app will read file paths recursively from a given base directory and resize and rename all found images and write them into a target directory
 *
 * @author rretzbach
 */
public class CommandLineApp {
    private final SourceProvider<String> sourceProvider;
    private final TaskFactory<String, String> taskFactory;
    private Resizer<String, String> resizer;

    @Inject
    public CommandLineApp(SourceProvider<String> sourceProvider, TaskFactory<String, String> taskFactory, Resizer<String, String> resizer) {
        this.sourceProvider = sourceProvider;
        this.taskFactory = taskFactory;
        this.resizer = resizer;
    }

    public void main() {
        List<String> sources = sourceProvider.getSources();
        Collections.sort(sources, new CaseInsensitiveStringComparator());

        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);

        resizer.addAll(tasks);
        resizer.execute();
    }
}
