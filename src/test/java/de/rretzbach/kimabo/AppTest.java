package de.rretzbach.kimabo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import de.rretzbach.kimabo.file2file.CommandLineAppTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    private TaskFactory<String, String> taskFactory;

    @Override
    protected void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestResizerModule("MockSeries"));
        taskFactory = injector.getInstance(Key.get(new TypeLiteral<TaskFactory<String, String>>() {
        }));
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(new Class[]{AppTest.class, CommandLineAppTest.class});
    }

    public void testShouldSliceOneSmallBook() {
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

    public void testShouldSliceOneFullBook() {
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

    public void testShouldSliceManyBooks() {
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
