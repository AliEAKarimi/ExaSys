package GUI.managerPanel.createExam.questionsTypes;

import GUI.Utils.OnlineClasses;
import Logic.Question.DescriptiveQ;
import Logic.Question.QuestionType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DescriptiveQuestionController implements Initializable {

    public TextField questionTimeTF;
    public TextField gradeTF;
    @FXML private JFXButton endEditBtn;
    @FXML private JFXButton addImageBtn;
    @FXML private JFXButton editBtn;
    @FXML private JFXButton deleteBtn;
    @FXML private AnchorPane anchorPane;
    @FXML private JFXButton cancelBtn;
    @FXML private JFXButton confirmBtn;
    @FXML private ScrollPane scrollPane;
    @FXML private Text hintT;
    @FXML private FlowPane photosSpaceFP;
    @FXML private JFXTextArea questionTextTA;

    private FileChooser fileChooser;
    private DescriptiveQ descriptiveQ;
    private ArrayList<Image> images;

    public void addImageBtnAction() {
        File file = fileChooser.showOpenDialog(((Stage) questionTextTA.getScene().getWindow()));
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            photosSpaceFP.getChildren().add(imageView);
            images.add(image);
        }
    }
    public void cancelBtnAction() {
        ((Stage) scrollPane.getScene().getWindow()).close();
        OnlineClasses.chooseQType.cancelBtnAction();
    }
    public void confirmBtnAction() {
        if (isValidQuestionText() && isValidGradeAndTimeQuestion()) {
            editBtn.setVisible(true);
            deleteBtn.setVisible(true);
            questionTextTA.setDisable(true);
            addImageBtn.setDisable(true);
            questionTimeTF.setDisable(true);
            gradeTF.setDisable(true);

            descriptiveQ  = new DescriptiveQ();
            OnlineClasses.chooseQType.addQuestion();
            updateQuestion();
            OnlineClasses.examDefining.getExam().getQuestions().add(descriptiveQ);
            addQuestion(anchorPane);
            cancelBtnAction();
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
        addImageBtn.setDisable(false);
        if (!OnlineClasses.examDefining.getExam().isOutright())
            questionTimeTF.setDisable(false);
        gradeTF.setDisable(false);
    }
    public void deleteBtnAction() {
        QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().remove(anchorPane);
        OnlineClasses.examDefining.getExam().getQuestions().remove(descriptiveQ);
        OnlineClasses.chooseQType.deleteQuestion();
    }
    public void endEditBtnAction() {
        if (isValidQuestionText()) {
            questionTextTA.setDisable(true);
            addImageBtn.setDisable(true);
            endEditBtn.setVisible(false);
            questionTimeTF.setDisable(true);
            gradeTF.setDisable(true);
            updateQuestion();
        }
    }
    private void updateQuestion() {
        descriptiveQ.setGrade(Integer.parseInt(gradeTF.getText()));
        descriptiveQ.setText(questionTextTA.getText());
        if (!questionTimeTF.isDisable())descriptiveQ.setTimeLimit(Integer.parseInt(questionTimeTF.getText()));
        descriptiveQ.setType(QuestionType.DESCRIPTIVE);
        descriptiveQ.setLimited(!OnlineClasses.examDefining.getValueOutright());
        descriptiveQ.setNumber(OnlineClasses.examDefining.getExam().getQuestions().indexOf(descriptiveQ)+1);
        descriptiveQ.getImages().clear();
        descriptiveQ.getImages().addAll(this.images);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (OnlineClasses.examDefining.getExam().isOutright())
            questionTimeTF.setDisable(true);
        fileChooser = new FileChooser();
        deleteBtn.setVisible(false);
        editBtn.setVisible(false);
        endEditBtn.setVisible(false);
        images = new ArrayList<>();
    }
    private void addQuestion(AnchorPane anchorPane) {
        cancelBtn.setVisible(false);
        confirmBtn.setVisible(false);
        QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().add(anchorPane);
        cancelBtnAction();
    }
}
