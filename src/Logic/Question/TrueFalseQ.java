package Logic.Question;

import java.io.Serializable;

public class TrueFalseQ extends Question {

    private boolean answer;

    public TrueFalseQ() {
        this.setType(QuestionType.TRUE_FALSE);
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }
}
