package de.rretzbach.kimabo;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockTestRunner.class)
public class TaskFactoryTest {

    @Inject
    TaskFactory<String, String> taskFactory;

    @Test
    public void shouldSliceOneSmallBook() {
        List<String> sources = Arrays.asList("1", "2");
        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);

        for (ResizeTask<String, String> resizeTask : tasks) {
            if (resizeTask.getSource().equals("1")) {
                assertEquals("b1p1_1", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("2")) {
                assertEquals("b1p2_2", resizeTask.getTarget());
            } else {
                fail("Contained unexpected element " + resizeTask);
            }
        }
    }

    @Test
    public void shouldSliceOneFullBook() {
        List<String> sources = Arrays.asList("1", "2", "3");
        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);

        for (ResizeTask<String, String> resizeTask : tasks) {
            if (resizeTask.getSource().equals("1")) {
                assertEquals("b1p1_1", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("2")) {
                assertEquals("b1p2_2", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("3")) {
                assertEquals("b1p3_3", resizeTask.getTarget());
            } else {
                fail("Contained unexpected element " + resizeTask);
            }
        }
    }

    @Test
    public void shouldSliceManyBooks() {
        List<String> sources = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        Set<ResizeTask<String, String>> tasks = taskFactory.sliceResizeTasks(sources);

        for (ResizeTask<String, String> resizeTask : tasks) {
            if (resizeTask.getSource().equals("1")) {
                assertEquals("b1p1_1", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("2")) {
                assertEquals("b1p2_2", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("3")) {
                assertEquals("b1p3_3", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("4")) {
                assertEquals("b2p1_4", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("5")) {
                assertEquals("b2p2_5", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("6")) {
                assertEquals("b2p3_6", resizeTask.getTarget());
            } else if (resizeTask.getSource().equals("7")) {
                assertEquals("b3p1_7", resizeTask.getTarget());
            } else {
                fail("Contained unexpected element " + resizeTask);
            }
        }
    }
}
