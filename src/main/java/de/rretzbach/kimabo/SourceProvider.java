/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import java.util.List;

/**
 * @author rretzbach
 */
public interface SourceProvider<S> {

    public List<S> getSources();

}
