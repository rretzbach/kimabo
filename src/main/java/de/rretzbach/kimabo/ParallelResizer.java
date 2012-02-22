/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import com.google.inject.Inject;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Benchmark
 * =========
 * Input:            1.716 files [818MB]
 * Output:           1.651 files [418MB]
 * <p/>
 * 8 Threads (similar to 16 threads)
 * <p/>
 * Total Time:       125s
 * Time per image:   0,076s
 * Image per second: 13,208
 * <p/>
 * 4 Threads
 * <p/>
 * Total Time:       179s
 * Time per image:   0,108s
 * Image per second: 9,223
 *
 * @author rretzbach
 */
public class ParallelResizer<S, T> implements Resizer<S, T> {

    private Set<ResizeTask<S, T>> tasks = new HashSet<ResizeTask<S, T>>();
    private ImageReader<S> imageReader;
    private ImageWriter<T> imageWriter;
    private int preferredWidth;
    private int preferredHeight;

    @Inject
    public ParallelResizer(ImageReader<S> imageReader, ImageWriter<T> imageWriter, @PreferredWidth int preferredWidth, @PreferredHeight int preferredHeight) {
        this.imageReader = imageReader;
        this.imageWriter = imageWriter;
        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;
    }

    @Override
    public void addAll(Collection<ResizeTask<S, T>> tasks) {
        this.tasks.addAll(tasks);
    }

    @Override
    public void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (final ResizeTask<S, T> task : tasks) {
            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    execute(task);
                }
            };
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }

    private void execute(ResizeTask<S, T> task) {
        try {
            ResizableImage image = new ResizableImage(imageReader.read(task.getSource()));
            image.resize(preferredWidth, preferredHeight).desaturate();
            imageWriter.write(image.getImage(), task.getTarget());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while executing task " + task);
        }
    }
}
