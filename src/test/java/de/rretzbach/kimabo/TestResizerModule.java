/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import com.google.inject.AbstractModule;
import de.rretzbach.kimabo.file2file.*;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 *
 * @author rretzbach
 */
public class TestResizerModule extends AbstractModule {
    private final String seriesName;

    public TestResizerModule(String seriesName) {
        this.seriesName = seriesName;
    }

    @Override
    protected void configure() {
        bind(ImageReader.class).to(MockImageReader.class);
        bind(ImageWriter.class).to(MockImageWriter.class);
        bind(TargetFinder.class).to(EchoStringTargetFinder.class);
        bindConstant().annotatedWith(SeriesName.class).to("MockSeries");
        bindConstant().annotatedWith(PagesPerBook.class).to(3);
    }
}
