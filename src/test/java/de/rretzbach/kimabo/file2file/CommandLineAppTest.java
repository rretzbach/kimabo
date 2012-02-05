/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.rretzbach.kimabo.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author rretzbach
 */
public class CommandLineAppTest extends TestCase {

    private Resizer resizer;

    @Override
    protected void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestResizerModule("MockSeries"));
        resizer = injector.getInstance(Resizer.class);
    }

    public void testShouldSortSourcesCaseInsensitive() {
        List<String> sources = Arrays.asList(new String[]{"Mock_c01", "mock_C02", "MOCk_C03"});
        Set<ResizeTask> tasks = resizer.sliceResizeTasks(sources);
        for (ResizeTask resizeTask : tasks) {
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
