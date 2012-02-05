/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import de.rretzbach.kimabo.TargetFinder;
import com.google.inject.Inject;
import de.rretzbach.kimabo.ImageReader;
import de.rretzbach.kimabo.ImageWriter;
import de.rretzbach.kimabo.ResizeTask;
import de.rretzbach.kimabo.Resizer;
import de.rretzbach.kimabo.SourceProvider;
import de.rretzbach.kimabo.ThreadHelper;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rretzbach
 */
public class CommandLineApp {
    private final SourceProvider sourceProvider;
    private final Resizer resizer;

    @Inject
    public CommandLineApp(SourceProvider sourceProvider, Resizer resizer) {
        this.sourceProvider = sourceProvider;
        this.resizer = resizer;
    }

    public void main(String[] args) {
        
        List<String> sources = sourceProvider.getSources();

        Collections.sort(sources, new CaseInsensitiveStringComparator());

        Set<ResizeTask> tasks = resizer.sliceResizeTasks(sources);

        ThreadHelper th = new ThreadHelper(tasks);
        th.setNumberOfThreads(8);
        th.execute();
    }
}
