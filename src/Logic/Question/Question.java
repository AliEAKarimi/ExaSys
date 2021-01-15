package Logic.Question;

import java.io.Serializable;

public class Question implements Serializable {

    private QuestionType type;
    private String text;
    private int number;
    private double grade;
    private boolean isLimited;
    private double timeLimit;

    public void setText(String text) {
        this.text = text;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }

    public double getGrade() {
        return grade;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public QuestionType getType() {
        return type;
    }

    public void setLimited(boolean limited) {
        isLimited = limited;
    }

    public boolean isLimited() {
        return isLimited;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

}

