package Logic.Exam;

import Logic.Question.Question;
import Logic.User.Manager;
import Logic.User.Student;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Exam implements Serializable {

    private Manager designer;
    private String title;
    private LocalTime startTime, endTime;
    private LocalDate startDate, endDate;
    private boolean isRandom, isOutright, isSee;
    private String degreeEducation, fieldOfStudy, id, examTime;
    private ArrayList<Student> students;
    private ArrayList<Question> questions;

    public Exam() {
        students = new ArrayList<>();
        questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public boolean isOutright() {
        return isOutright;
    }

    public void setOutright(boolean outright) {
        isOutright = outright;
    }

    public boolean isSee() {
        return isSee;
    }

    public void setSee(boolean see) {
        isSee = see;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getDegreeEducation() {
        return degreeEducation;
    }

    public void setDegreeEducation(String degreeEducation) {
        this.degreeEducation = degreeEducation;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setDesigner(Manager designer) {
        this.designer = designer;
    }

    public Manager getDesigner() {
        return designer;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamTime() {
        return examTime;
    }

}


