package GUI.managerPanel.createExam.questionsTypes;

import GUI.Utils.OnlineClasses;
import Logic.Question.QuestionType;
import Logic.Question.TestQ;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class testyQuestionController implements Initializable {

    public ScrollPane scrollPane;
    public JFXButton confirmBtn;
    public JFXButton cancelBtn;
    public AnchorPane anchorPane;
    public Text hintT;
    public JFXButton endEditBtn;
    public JFXButton editBtn;
    public JFXButton deleteBtn;
    public JFXTextArea questionTextTA;
    public TextArea switch1TA;
    public TextArea switch2TA;
    public TextArea switch3TA;
    public TextArea switch4TA;
    public TextField questionTimeTF;
    public TextField gradeTF;

    private TestQ testyQ;

    public void cancelBtnAction() {
        ((Stage) scrollPane.getScene().getWindow()).close();
        OnlineClasses.chooseQType.cancelBtnAction();
    }

    public void confirmBtnAction() {
        if (isValidQuestionText() && isValidSwitches() && isValidGradeAndTimeQuestion()) {
            editBtn.setVisible(true);
            deleteBtn.setVisible(true);
            questionTextTA.setDisable(true);
            switch1TA.setDisable(true);
            switch2TA.setDisable(true);
            switch3TA.setDisable(true);
            switch4TA.setDisable(true);
            questionTimeTF.setDisable(true);
            gradeTF.setDisable(true);

            testyQ = new TestQ();
            updateQuestion();
            OnlineClasses.examDefining.getExam().getQuestions().add(testyQ);

            addQuestion(anchorPane);
            cancelBtnAction();
        }
    }

    private void updateQuestion() {
        testyQ.setGrade(Integer.parseInt(gradeTF.getText()));
        testyQ.setText(questionTextTA.getText());
        testyQ.setLimited(!OnlineClasses.examDefining.getValueOutright());
        testyQ.setNumber(OnlineClasses.chooseQType.getNumber());
        if (!questionTimeTF.isDisable()) testyQ.setTimeLimit(Double.parseDouble(questionTimeTF.getText()));
        testyQ.setType(QuestionType.TESTY);
        testyQ.setChoice1(switch1TA.getText());
        testyQ.setChoice2(switch2TA.getText());
        testyQ.setChoice3(switch3TA.getText());
        testyQ.setChoice4(switch4TA.getText());
    }

    private boolean isValidQuestionText() {
        if (questionTextTA.getText() == null || questionTextTA.getText().equals("")) {
            hintT.setText("متن سوال را وارد کنید.");
            return false;
        }
        if (questionTextTA.getText().startsWith(" ")) {
            hintT.setText("متن سوال نمی تواند با فاصله شروع شود.");
            return false;
        }
        hintT.setText(null);
        return true;
    }

    private boolean isValidSwitches() {
        if (switch1TA.getText() == null || switch1TA.getText().equals("")
                || switch2TA.getText() == null || switch2TA.getText().equals("")
                || switch3TA.getText() == null || switch3TA.getText().equals("")
                || switch4TA.getText() == null || switch4TA.getText().equals("")) {
            hintT.setText("گزینه ها را کامل وارد کنید.");
            return false;
        }
        if (switch1TA.getText().startsWith(" ") || switch2TA.getText().startsWith(" ")
                || switch3TA.getText().startsWith(" ") || switch4TA.getText().startsWith(" ")) {
            hintT.setText("متن گزینه ها نمی توانند با فاصله شروع شوند.");
            return false;
        }
        hintT.setText(null);
        return true;
    }

    public void editBtnAction() {
        endEditBtn.setVisible(true);
        questionTextTA.setDisable(false);
        switch1TA.setDisable(false);
        switch2TA.setDisable(false);
        switch3TA.setDisable(false);
        switch4TA.setDisable(false);
        if (!OnlineClasses.examDefining.getExam().isOutright())
            questionTimeTF.setDisable(false);
        gradeTF.setDisable(false);
    }

    public void deleteBtnAction() {
        QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().remove(anchorPane);
        OnlineClasses.examDefining.getExam().getQuestions().remove(testyQ);
        OnlineClasses.chooseQType.deleteQuestion();
    }

    public void endEditBtnAction() {
        if (isValidQuestionText() && isValidSwitches()) {
            questionTextTA.setDisable(true);
            endEditBtn.setVisible(false);
            switch1TA.setDisable(true);
            switch2TA.setDisable(true);
            switch3TA.setDisable(true);
            switch4TA.setDisable(true);
            questionTimeTF.setDisable(true);
            gradeTF.setDisable(true);
            updateQuestion();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (OnlineClasses.examDefining.getExam().isOutright())
            questionTimeTF.setDisable(true);
        deleteBtn.setVisible(false);
        editBtn.setVisible(false);
        endEditBtn.setVisible(false);
    }

    private boolean isValidGradeAndTimeQuestion() {
        if (gradeTF.getText() == null || gradeTF.getText().equals("")) {
            hintT.setText("نمره سوال را وارد کنید.");
            return false;
        }
        if (gradeTF.getText().startsWith(" ")) {
            hintT.setText("نمره سوال را بدون فاصله وارد کنید.");
            return false;
        }
        if (!questionTimeTF.isDisable() && (questionTimeTF.getText() == null || questionTimeTF.getText().equals(""))) {
            hintT.setText("زمان سوال را مشخص کنید.");
            return false;
        }
        if (!questionTimeTF.isDisable() && questionTimeTF.getText().startsWith(" ")) {
            hintT.setText("زمان سوال را بدون فاصله وارد کنید.");
            return false;
        }
        hintT.setText(null);
        return true;
    }

    private void addQuestion(AnchorPane anchorPane) {
        cancelBtn.setVisible(false);
        confirmBtn.setVisible(false);
        QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().add(anchorPane);
        cancelBtnAction();
    }
}
