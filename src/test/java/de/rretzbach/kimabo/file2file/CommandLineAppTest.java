/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import de.rretzbach.kimabo.ResizeTask;
import de.rretzbach.kimabo.TaskFactory;
import de.rretzbach.kimabo.TestResizerModule;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author rretzbach
 */
public class CommandLineAppTest extends TestCase {

    private TaskFactory<String, String> taskFactory;

    @Override
    protected void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestResizerModule("MockSeries"));
        taskFactory = injector.getInstance(Key.get(new TypeLiteral<TaskFactory<String, String>>() {
        }));
    }

    public void testShouldSortSourcesCaseInsensitive() {
        List<String> sources = Arrays.asList("Mock_c01", "mock_C02", "MOCk_C03");
        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);
        for (ResizeTask<String, String> resizeTask : tasks) {
            if (resizeTask.getSource().equals("Mock_c01")) {
                assertEquals("b1p1_Mock_c01", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("mock_C02")) {
                assertEquals("b1p2_mock_C02", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("MOCk_C03")) {
                assertEquals("b1p3_MOCk_C03", resizeTask.getTarget());
            } else {
                fail("Contained unexpected element " + resizeTask);
            }
        }

    }
}
