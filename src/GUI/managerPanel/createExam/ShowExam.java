package GUI.managerPanel.createExam;

import Logic.Exam.Exam;
import Logic.User.Student;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShowExam {

    @FXML private Text examTitleT;
    @FXML private JFXButton examSettingBtn;
    @FXML private JFXButton questionsBtn;
    @FXML private JFXButton studentsBtn;

    @FXML private  Exam exam;
    @FXML private TableView<Student> tableView;

    public void studentBtnAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showStudents.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShowStudentsController showStudentsController = loader.getController();
        showStudentsController.setExam(exam);

        tableView = new TableView<>();
        TableColumn firstNameCol = new TableColumn("نام");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setStyle("-fx-alignment: CENTER");
        TableColumn lastNameCol = new TableColumn("نام خانوادگی");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setStyle("-fx-alignment: CENTER");
        TableColumn idCol = new TableColumn("شماره دانشجویی");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setStyle("-fx-alignment: CENTER");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(firstNameCol, lastNameCol, idCol);
        tableView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ((BorderPane)anchorPane.getChildren().get(0)).setCenter(tableView);
        showStudentsController.setTableView(tableView);
        for (Student student : exam.getStudents()) {
            tableView.getItems().add(student);
        }

        Scene popUpScene = new Scene(anchorPane);
        Stage popUpStage = new Stage();
        popUpStage.setScene(popUpScene);
        popUpStage.setAlwaysOnTop(true);
        popUpStage.initStyle(StageStyle.TRANSPARENT);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.showAndWait();
    }
    public void questionsBtnAction() {

    }
    public void settingBtnAction() {

    }
    public JFXButton getExamSettingBtn() {
        return examSettingBtn;
    }

    public JFXButton getQuestionsBtn() {
        return questionsBtn;
    }

    public JFXButton getStudentsBtn() {
        return studentsBtn;
    }

    public Text getExamTitleT() {
        return examTitleT;
    }

    @FXML public void setExam(Exam exam) {
        this.exam = exam;
    }
}
