package GUI.managerPanel.createExam.questionsTypes;

import GUI.Utils.OnlineClasses;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionDesigning implements Initializable {

    @FXML
    private VBox questionsSpacesVBox;
    private static VBox questionsSpacesVBoxAuxiliary;

    public void newQuestionBtnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseQType.fxml"));
        DialogPane dialogPane = loader.load();
        OnlineClasses.setChooseQType(loader.getController());
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.showAndWait();
    }

   /* private boolean isQuestionSpaceEmpty() {
        Iterator<Node> iterator = questionsSpacesVBox.getChildren().iterator();
        if (iterator.hasNext()) return true;
        System.out.println("most be define one question at least.");
    }*/
    //this method check now question that manager is writing it and must be completed to add another question.
    //in this method check last children of questionsSpacesVBox.
    private boolean isDescriptiveQuestionCompleted() {
        return true;
    }

    private boolean isTestyQuestionCompleted() {
        return true;
    }

    private boolean isTrueFalseQuestionCompleted() {
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionsSpacesVBoxAuxiliary = questionsSpacesVBox;
    }

    public static VBox getQuestionsSpacesVBoxAuxiliary() {
        return questionsSpacesVBoxAuxiliary;
    }
}
