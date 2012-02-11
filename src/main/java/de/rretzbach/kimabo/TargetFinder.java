/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

/**
 * @author rretzbach
 */
public interface TargetFinder<S, T> {
    T find(S source, String seriesName, int book, int page);
}
