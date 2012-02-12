/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.awt.image.BufferedImage;
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
    private final ImageHelper imageHelper;

    public SequencialResizer(ImageReader<S> imageReader, ImageWriter<T> imageWriter) {
        this.imageReader = imageReader;
        this.imageWriter = imageWriter;
        this.imageHelper = new ImageHelper();
    }
    // start 1 file reader thread
    // start n image imageHelper threads
    // start 1 file writer thread (only starts if reader is finished)

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
            BufferedImage image = imageReader.read(task.getSource());
            image = imageHelper.resize(image);
            image = imageHelper.desaturate(image);
            imageWriter.write(image, task.getTarget());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while executing task " + task);
        }
    }
}
