package GUI.managerPanel.createExam.questionsTypes;

import GUI.Utils.OnlineClasses;
import Logic.Question.QuestionType;
import Logic.Question.TrueFalseQ;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TrueFalseQuestionController implements Initializable {

    public JFXButton confirmBtn;
    public JFXButton cancelBtn;
    public ScrollPane scrollPane;
    public JFXTextArea questionTextTA;
    public JFXButton endEditBtn;
    public JFXButton editBtn;
    public JFXButton deleteBtn;
    public Text hintT;
    public AnchorPane anchorPane;
    public TextField questionTimeTF;
    public TextField gradeTF;

    private TrueFalseQ trueFalseQ;

    public void cancelBtnAction() {
        ((Stage) scrollPane.getScene().getWindow()).close();
        OnlineClasses.chooseQType.cancelBtnAction();
    }
    public void confirmBtnAction() {
        if (isValidQuestionText() && isValidGradeAndTimeQuestion()) {
            editBtn.setVisible(true);
            deleteBtn.setVisible(true);
            questionTextTA.setDisable(true);
            questionTimeTF.setDisable(true);
            gradeTF.setDisable(true);

            trueFalseQ = new TrueFalseQ();
            OnlineClasses.chooseQType.addQuestion();
            updateQuestion();
            OnlineClasses.examDefining.getExam().getQuestions().add(trueFalseQ);

            addQuestion(anchorPane);
            cancelBtnAction();
        }
    }
    private void updateQuestion() {
        trueFalseQ.setGrade(Double.parseDouble(gradeTF.getText()));
        trueFalseQ.setLimited(!OnlineClasses.examDefining.getValueOutright());
        trueFalseQ.setText(questionTextTA.getText());
        if (!questionTimeTF.isDisable())trueFalseQ.setTimeLimit(Double.parseDouble(questionTimeTF.getText()));
        trueFalseQ.setType(QuestionType.TRUE_FALSE);
        trueFalseQ.setNumber(OnlineClasses.examDefining.getExam().getQuestions().indexOf(trueFalseQ));
    }
    public boolean isValidQuestionText() {
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
    public void editBtnAction() {
        endEditBtn.setVisible(true);
        questionTextTA.setDisable(false);
        if (!OnlineClasses.examDefining.getExam().isOutright())
            questionTimeTF.setDisable(false);
        gradeTF.setDisable(false);
    }

    public void deleteBtnAction() {
        QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().remove(anchorPane);
        OnlineClasses.examDefining.getExam().getQuestions().remove(trueFalseQ);
        OnlineClasses.chooseQType.deleteQuestion();
    }
    public void endEditBtnAction() {
        if (isValidQuestionText()) {
            questionTextTA.setDisable(true);
            questionTimeTF.setDisable(true);
            gradeTF.setDisable(true);
            endEditBtn.setVisible(false);
            updateQuestion();
        }
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
        if (!questionTimeTF.isDisable() && (questionTimeTF.getText() == null|| questionTimeTF.getText().equals(""))) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (OnlineClasses.examDefining.getExam().isOutright())
            questionTimeTF.setDisable(true);
        deleteBtn.setVisible(false);
        editBtn.setVisible(false);
        endEditBtn.setVisible(false);
    }
    private void addQuestion(AnchorPane anchorPane) {
        cancelBtn.setVisible(false);
        confirmBtn.setVisible(false);
        QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().add(anchorPane);
        cancelBtnAction();
    }
}
