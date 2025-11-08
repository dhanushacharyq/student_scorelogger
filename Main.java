package student;

public class Main {
    public static void main(String[] args) {
    	String fileName = "student_scores.csv";

        ScoreWriter writer = new ScoreWriter(fileName);

        Student s1 = new Student("Arun", 101, 85);
        Student s2 = new Student("Meena", 102, 90);
        Student s3 = new Student("Raj", 103, 78);
 
        Thread t1 = new StudentThread(writer, s1);
        Thread t2 = new StudentThread(writer, s2);
        Thread t3 = new StudentThread(writer, s3);
     
        t1.start();
        t2.start();
        t3.start();

        try {

            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All students have submitted their scores!");
    }
}
