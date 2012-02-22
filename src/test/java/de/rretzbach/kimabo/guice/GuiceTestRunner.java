package de.rretzbach.kimabo.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * Base class for running different test setups using Guice Modules
 * User: rretzbach
 * Date: 22.02.12
 * Time: 18:16
 * Courtesy: http://cowwoc.blogspot.com/2008/10/integrating-google-guice-into-junit4.html
 */
public abstract class GuiceTestRunner extends BlockJUnit4ClassRunner {
    Injector injector;

    public GuiceTestRunner(final Class<?> classToRun, Module... modules) throws InitializationError {
        super(classToRun);
        this.injector = Guice.createInjector(modules);
    }

    @Override
    public Object createTest() {
        return injector.getInstance(getTestClass().getJavaClass());
    }
}
