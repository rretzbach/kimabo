package de.rretzbach.kimabo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.rretzbach.kimabo.file2file.CommandLineAppTest;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    private Resizer resizer;

    @Override
    protected void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TestResizerModule("MockSeries"));
        resizer = injector.getInstance(Resizer.class);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(AppTest.class);
        suite.addTest(new CommandLineAppTest());
        return suite;
    }

    public void testShouldSliceOneSmallBook() {
        List<String> sources = Arrays.asList(new String[]{"1", "2"});
        Set<ResizeTask> tasks = resizer.sliceResizeTasks(sources);

        for (ResizeTask resizeTask : tasks) {
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
        List<String> sources = Arrays.asList(new String[]{"1", "2", "3"});
        Set<ResizeTask> tasks = resizer.sliceResizeTasks(sources);

        for (ResizeTask resizeTask : tasks) {
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
        List<String> sources = Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7"});
        Set<ResizeTask> tasks = resizer.sliceResizeTasks(sources);

        for (ResizeTask resizeTask : tasks) {
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
