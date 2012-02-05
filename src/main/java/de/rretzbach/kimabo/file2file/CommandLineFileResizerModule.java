/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.AbstractModule;

/**
 *
 * @author rretzbach
 */
public class CommandLineFileResizerModule extends AbstractModule {
    private final String seriesName;
    private final String baseDirectory;

    public CommandLineFileResizerModule(String[] args) {
        seriesName = args[0];
        baseDirectory = args[1];
    }
    
    @Override
    protected void configure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
