/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

/**
 * @author rretzbach
 */
public class ResizeTask<S, T> {
    private final S source;
    private final T target;

    public ResizeTask(S source, T target) {
        this.source = source;
        this.target = target;
    }

    public S getSource() {
        return this.source;
    }

    public T getTarget() {
        return this.target;
    }

    public ImageResizeTask read(ImageReader<S> reader) {
        return new ImageResizeTask(reader.read(source), target);
    }

    @Override
    public String toString() {
        return String.format("[ResizeTask: source=%s,target=%s]", source.toString(), target.toString());
    }


}
