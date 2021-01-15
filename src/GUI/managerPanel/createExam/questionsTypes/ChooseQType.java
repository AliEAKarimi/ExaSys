package GUI.managerPanel.createExam.questionsTypes;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ChooseQType {

    private int number = 0;
    public DialogPane dialogPane;
    public JFXButton cancelBtn;
    public JFXButton trueFalseBtn;
    public JFXButton testBtn;
    public JFXButton descriptiveBtn;
    public JFXButton testyQBtn;

    public void cancelBtnAction() {
        ((Stage) dialogPane.getScene().getWindow()).close();
    }
    public void descriptiveBtnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("descriptiveQuestion.fxml"));
        ScrollPane scrollPane = loader.load();

        addNewStage(scrollPane);
    }
    public void testyBtnAction() throws IOException {
        ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("testyQuestion.fxml"));
        addNewStage(scrollPane);
    }
    public void trueFalseBtnAction() throws IOException {
        ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("trueFalseQuestion.fxml"));
        addNewStage(scrollPane);
    }
    private void addNewStage(ScrollPane scrollPane) {
        Scene popUpScene = new Scene(scrollPane);
        Stage popUpStage = new Stage();
        popUpStage.setScene(popUpScene);
        popUpStage.initStyle(StageStyle.TRANSPARENT);
        popUpStage.showAndWait();
    }
    public void addQuestion() {
        number++;
    }
    public void deleteQuestion() {
        number--;
    }
    public int getNumber() {
        return number;
    }
}
