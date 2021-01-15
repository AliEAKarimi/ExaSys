package Logic.Question;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class DescriptiveQ extends Question {

    private String answer;

    private transient ArrayList<Image> images;

    public DescriptiveQ() {
        images = new ArrayList<>();
        this.setType(QuestionType.DESCRIPTIVE);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public String getAnswer() {
        return answer;
    }
}
