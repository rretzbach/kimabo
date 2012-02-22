package de.rretzbach.kimabo;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SkipBookRunner.class)
public class TaskFactorySkipTest {

    @Inject
    TaskFactory<String, String> taskFactory;

    @Test
    public void shouldSkipBooksIfRequested() {
        List<String> sources = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);
        for (ResizeTask<String, String> resizeTask : tasks) {
            if (resizeTask.getSource().equals("7")) {
                assertEquals("b3p1_7", resizeTask.getTarget());
            } else {
                fail("Contained unexpected element " + resizeTask);
            }
        }
    }
}
