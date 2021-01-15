/*
package GUI.managerPanel.createExam;

import Files.ExamFiles;
import Files.ExamState;
import GUI.Utils.OnlineClasses;
import GUI.managerPanel.ManagerPanel;
import Logic.Exam.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExamManagingController implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private FlowPane examManagingFP;
    @FXML private ArrayList<Exam> exams;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exams = new ArrayList<>();
        uploadExams();
    }
    private void uploadExams() {
        File file = new File("src\\Files\\files\\ManagingExams");
        if (file.listFiles().length != 0) {
            for (File subFile : file.listFiles()) {
                if (subFile.getName().startsWith(ManagerPanel.getUsername() + "!")) {
                    Exam exam = ExamFiles.getExamination(subFile.getName(), ExamState.MANAGING_EXAMS);
                    exams.add(exam);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("showExam.fxml"));
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ShowExam showExam = loader.getController();
                    showExam.setExam(exam);
                    showExam.getExamTitleT().setText(exam.getTitle());
                    examManagingFP.getChildren().add(anchorPane);
                }
            }
        }
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public FlowPane getExamManagingFP() {
        return examManagingFP;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }
}
*/
package GUI.managerPanel.createExam;

import Files.ExamFiles;
import Files.ExamState;
import GUI.managerPanel.ManagerPanel;
import Logic.Exam.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ExamManagingController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private FlowPane examManagingFP;
    @FXML
    private ArrayList<Exam> exams;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exams = new ArrayList<>();
        uploadExams();
    }

    private void uploadExams() {
        File file = new File("src\\Files\\files\\ManagingExams");
        if (file.listFiles().length != 0) {
            for (File subFile : file.listFiles()) {
                if (subFile.getName().startsWith(ManagerPanel.getUsername() + "!")) {
                    Exam exam = ExamFiles.getExamination(subFile.getName(), ExamState.MANAGING_EXAMS);
                    exams.add(exam);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("showExam.fxml"));
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    examManagingFP.getChildren().add(anchorPane);
                    ShowExam showExam = loader.getController();
                    showExam.setExam(exam);
                    showExam.getExamTitleT().setText(exam.getTitle());
                }
            }
        }
        /*Thread uploadExamThread = new UploadExamsThread(exams, examManagingFP);
        uploadExamThread.start();*/
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public FlowPane getExamManagingFP() {
        return examManagingFP;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }
}

class UploadExamsThread extends Thread {
    private  FlowPane flowPane;
    private  List<Exam> exams;

    public UploadExamsThread(List<Exam> exams, FlowPane flowPane) {
        this.flowPane = flowPane;
        this.exams = exams;
    }

    @Override
    public void run() {

    }
}