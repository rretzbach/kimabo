/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

/**
 *
 * @author rretzbach
 */
public class ResizeTask {
    private final String source;
    private final String target;



    public ResizeTask(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return this.source;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void execute() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        return String.format("[ResizeTask: source=%s,target=%s]", source, target);
    }
    
    
}
