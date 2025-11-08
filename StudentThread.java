package student;

public class StudentThread extends Thread {
    private final ScoreWriter writer;
    private final Student student;

    public StudentThread(ScoreWriter writer, Student student) {
        this.writer = writer;
        this.student = student;
    }

    @Override
    public void run() {
        System.out.println("Student " + student.getName() + " submitting marks...");
        writer.writeScore(student);
    }
}
 