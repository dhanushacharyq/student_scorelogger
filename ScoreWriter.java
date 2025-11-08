package student;

import java.io.FileWriter;
import java.io.IOException;

public class ScoreWriter {
    private final String fileName;

    public ScoreWriter(String fileName) { 
        this.fileName = fileName;
    }

    public synchronized void writeScore(Student s) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(s.toCSV() + "\n");
            System.out.println(s.toCSV());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
