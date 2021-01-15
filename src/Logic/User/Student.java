package Logic.User;

import Logic.Exam.Exam;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends User {
    private String id;
    private ArrayList<Exam> exams;
    private String[] currentExam;

    public Student(String firstName, String lastName, String id) {
        super(firstName, lastName);
        setId(id);
    }

    public Student(String firstName, String lastName, String id, String username, String password) {
        super(firstName, lastName, username, password);
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{firstName:" + getFirstName() + ",lastName:" + getLastName() + ",id:" + id + ",username:" + getUsername() + ",password:" + getPassword() + "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setCurrentExam(String[] currentExam) {
        this.currentExam = currentExam;
    }

    public String[] getCurrentExam() {
        return currentExam;
    }

}
