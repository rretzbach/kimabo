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
 * @author rretzbach
 */
public class Resizer<S, T> {

    private Set<ResizeTask<S, T>> tasks = new HashSet<ResizeTask<S, T>>();
    private int threadNumber;
    private ImageReader<S> imageReader;
    private ImageWriter<T> imageWriter;

    public Resizer(ImageReader<S> imageReader, ImageWriter<T> imageWriter) {
        this.imageReader = imageReader;
        this.imageWriter = imageWriter;
    }
    // start 1 file reader thread
    // start n image resizer threads
    // start 1 file writer thread (only starts if reader is finished)

    public void addAll(Collection<ResizeTask<S, T>> tasks) {
        this.tasks.addAll(tasks);
    }

    public void setNumberOfThreads(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void execute() {
        System.out.println("tasks: " + tasks);
        for (ResizeTask task : tasks) {
            execute(task);
        }
    }

    private void execute(ResizeTask<S, T> task) {
        System.out.println("task: " + task);

        try {
            BufferedImage image = imageReader.read(task.getSource());
            image = ImageResizer.resizeTrick(image, 600, 800);
            imageWriter.write(image, task.getTarget());
        } catch (Exception e) {
            System.err.println("Error while executing task " + task);
        }
    }
}
