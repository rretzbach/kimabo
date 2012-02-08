/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.rretzbach.kimabo.*;

/**
 * @author rretzbach
 */
public class CommandLineFileResizerModule extends AbstractModule {
    private final String seriesName;
    private final String baseDirectory;

    public CommandLineFileResizerModule(String[] args) {
        //seriesName = args[0];
        seriesName = "TestSeries";
        //baseDirectory = args[1];
        baseDirectory = "/Users/rretzbach/Documents/workspace/kimabo/source";
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<ImageReader<String>>() {
        }).to(FileImageReader.class);
        bind(new TypeLiteral<ImageWriter<String>>() {
        }).to(FileImageWriter.class);
        bind(new TypeLiteral<TargetFinder<String, String>>() {
        }).to(DirTargetFinder.class);
        bind(new TypeLiteral<SourceProvider<String>>() {
        }).to(FileSourceProvider.class);

        bindConstant().annotatedWith(SeriesName.class).to(seriesName);
        bindConstant().annotatedWith(PagesPerBook.class).to(3);
        bindConstant().annotatedWith(BaseInputDir.class).to(baseDirectory);
    }
}