/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import java.util.Comparator;

/**
 *
 * @author rretzbach
 */
public class CaseInsensitiveStringComparator implements Comparator<String> {

    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
}
