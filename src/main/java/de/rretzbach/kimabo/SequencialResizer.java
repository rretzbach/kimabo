/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Benchmark
 * =========
 * Input:            1.716 files [818MB]
 * Output:           1.651 files [418MB]
 * Start:            Sat Feb 11 09:38:18 CET 2012
 * End:              Sat Feb 11 09:47:00 CET 2012
 * Total Time:       522s
 * Time per image:   0,316s
 * Image per second: 3,16
 *
 * @author rretzbach
 */
public class SequencialResizer<S, T> implements Resizer<S, T> {

    private Set<ResizeTask<S, T>> tasks = new HashSet<ResizeTask<S, T>>();
    private ImageReader<S> imageReader;
    private ImageWriter<T> imageWriter;
    private int preferredWidth;
    private int preferredHeight;

    public SequencialResizer(ImageReader<S> imageReader, ImageWriter<T> imageWriter, @PreferredWidth int preferredWidth, @PreferredHeight int preferredHeight) {
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
        for (ResizeTask<S, T> task : tasks) {
            execute(task);
        }
    }

    private void execute(ResizeTask<S, T> task) {
        try {
            ResizableImage image = new ResizableImage(imageReader.read(task.getSource()));
            image.resize(preferredWidth, preferredHeight);
            image.desaturate();
            imageWriter.write(image.getImage(), task.getTarget());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while executing task " + task);
        }
    }
}
