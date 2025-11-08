package student;

import static org.junit.Assert.assertTrue;

import java.io.*;

import org.junit.Test;

public class ScoreWriterTest {

    @Test
    public void testFileWrite() {
        String testFile = "test_scores.csv";
        ScoreWriter writer = new ScoreWriter(testFile);
        Student s = new Student("TestStudent", 999, 100);
        writer.writeScore(s);

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("TestStudent")) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue("File should contain written student record", found);
    }

    @Test
    public void testThreadCompletion() {
        String testFile = "thread_test.csv";
        ScoreWriter writer = new ScoreWriter(testFile);
        Student s1 = new Student("A", 1, 80);
        Student s2 = new Student("B", 2, 90);

        Thread t1 = new StudentThread(writer, s1);
        Thread t2 = new StudentThread(writer, s2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue("Threads should finish execution", !t1.isAlive() && !t2.isAlive());
    }
}
