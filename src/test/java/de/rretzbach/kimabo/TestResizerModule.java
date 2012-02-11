/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.rretzbach.kimabo.file2file.BaseInputDir;

/**
 * @author rretzbach
 */
public class TestResizerModule extends AbstractModule {
    private final String seriesName;

    public TestResizerModule(String seriesName) {
        this.seriesName = seriesName;
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<ImageReader<String>>() {
        }).to(MockImageReader.class);
        bind(new TypeLiteral<ImageWriter<String>>() {
        }).to(MockImageWriter.class);
        bind(new TypeLiteral<TargetFinder<String, String>>() {
        }).to(EchoStringTargetFinder.class);
        bind(new TypeLiteral<TaskFactory<String, String>>() {
        });

        bindConstant().annotatedWith(SeriesName.class).to(seriesName);
        bindConstant().annotatedWith(PagesPerBook.class).to(3);
        bindConstant().annotatedWith(BaseInputDir.class).to("");
    }
}
