package de.rretzbach.kimabo;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.rretzbach.kimabo.file2file.BaseInputDir;
import de.rretzbach.kimabo.guice.GuiceTestRunner;
import de.rretzbach.kimabo.mock.EchoStringTargetFinder;
import de.rretzbach.kimabo.mock.MockImageReader;
import de.rretzbach.kimabo.mock.MockImageWriter;
import org.junit.runners.model.InitializationError;

/**
 * Concrete test runner which will be used for the majority of tests
 * User: rretzbach
 * Date: 22.02.12
 * Time: 18:17
 */
public class SkipBookRunner extends GuiceTestRunner {
    public SkipBookRunner(Class<?> classToRun) throws InitializationError {
        super(classToRun, new AbstractModule() {
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

                bindConstant().annotatedWith(SeriesName.class).to("MockSeries");
                bindConstant().annotatedWith(PagesPerBook.class).to(3);
                bindConstant().annotatedWith(BaseInputDir.class).to("");
                bindConstant().annotatedWith(BeginBookIndex.class).to(2);
            }
        });
    }
}
