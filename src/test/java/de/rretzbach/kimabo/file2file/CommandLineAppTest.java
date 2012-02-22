/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.Inject;
import de.rretzbach.kimabo.MockTestRunner;
import de.rretzbach.kimabo.ResizeTask;
import de.rretzbach.kimabo.TaskFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockTestRunner.class)
public class CommandLineAppTest {

    @Inject
    TaskFactory<String, String> taskFactory;

    @Test
    public void shouldSortSourcesCaseInsensitive() {
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
