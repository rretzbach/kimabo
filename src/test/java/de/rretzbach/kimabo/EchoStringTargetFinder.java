/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

/**
 * @author rretzbach
 */
public class EchoStringTargetFinder implements TargetFinder<String, String> {

    public String find(String source, String seriesName, int book, int page) {
        return String.format("b%dp%d_%s", book + 1, page + 1, source);
    }
}
