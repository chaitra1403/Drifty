import backend.FileDownloader;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FileDownloader_notDone_cedfeed23a_Test {

    private FileDownloader fileDownloader;
    private StringProperty linkProperty, dirProperty, filenameProperty, downloadMessage;
    private IntegerProperty transferSpeedProperty;
    private DoubleProperty progressProperty;

    @Before
    public void setUp() {
        fileDownloader = new FileDownloader(new Job(), linkProperty, dirProperty, filenameProperty, downloadMessage, transferSpeedProperty, progressProperty);
    }

    @Test
    public void testNotDoneWhenDoneIsFalse() {
        fileDownloader.setDone(false);
        assertTrue(fileDownloader.notDone());
    }

    @Test
    public void testNotDoneWhenDoneIsTrue() {
        fileDownloader.setDone(true);
        assertFalse(fileDownloader.notDone());
    }

    @Test
    public void testNotDoneWhenDoneNotInitialized() {
        assertTrue(fileDownloader.notDone());
    }

    @Test
    public void testNotDoneInMultiThreadedEnvironment() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            fileDownloader.setDone(true);
            assertFalse(fileDownloader.notDone());
        });

        Thread thread2 = new Thread(() -> {
            fileDownloader.setDone(false);
            assertTrue(fileDownloader.notDone());
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    public void testNotDoneAfterChangingDoneValue() {
        fileDownloader.setDone(true);
        assertFalse(fileDownloader.notDone());

        fileDownloader.setDone(false);
        assertTrue(fileDownloader.notDone());
    }

    @Test
    public void testNotDoneWhenDoneValueIsChangedAlternately() {
        for (int i = 0; i < 10; i++) {
            fileDownloader.setDone(i % 2 == 0);
            assertEquals(i % 2 != 0, fileDownloader.notDone());
        }
    }

    @Test
    public void testNotDoneWhenCalledConcurrently() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                fileDownloader.setDone(i % 2 == 0);
                assertEquals(i % 2 != 0, fileDownloader.notDone());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                fileDownloader.setDone(i % 2 != 0);
                assertEquals(i % 2 == 0, fileDownloader.notDone());
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    public void testNotDoneWhenDoneIsSetToFalseAfterDelay() throws InterruptedException {
        fileDownloader.setDone(true);
        assertFalse(fileDownloader.notDone());

        Thread.sleep(1000);

        fileDownloader.setDone(false);
        assertTrue(fileDownloader.notDone());
    }
}
