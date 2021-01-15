package GUI.studentPanel;

import Database.StudentColl;
import Files.ImageFiles;
import Files.ImageState;
import GUI.mainPage.MainPage;
import Logic.User.Student;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import sun.plugin.javascript.navig.Anchor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentPanel implements Initializable {
    @FXML
    private
    BorderPane borderPane;
    @FXML
    private ImageView userIconIV;
    @FXML
    private Text studentNameT;
    @FXML
    private JFXButton homeBtn;

    private static String username;

    public static void setUsername(String username) {
        StudentPanel.username = username;
    }

    public static String getUsername() {
        return username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student student = StudentColl.getStudentObject(username);
        studentNameT.setText(student.getFirstName() + " " + student.getLastName());
        Image image = ImageFiles.getImageProfile(ImageState.STUDENT_PROFILE_IMAGE, username);
        if (image != null) {
            userIconIV.setImage(image);
            userIconIV.setFitWidth(100);
            userIconIV.setFitHeight(100);
        } else {
            userIconIV.setImage(new Image("resource\\user_icon.png"));
        }
    }

    public void homeBtnAction() {

    }

    public void futureExamsBtnAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("futureExams.fxml"));
        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.borderPane.setCenter(borderPane);
    }

    public void pastExamsBtnAction() {

    }

    public void exitBtnAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../logIn/logInStudent.fxml"));
        try {
            AnchorPane anchorPane = loader.load();
            MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void imageViewAction() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("jpg images", "*.jpg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(userIconIV.getScene().getWindow());
        ImageFiles.addImageProfile(ImageState.STUDENT_PROFILE_IMAGE, file, username);
        userIconIV.setImage(new Image(file.toURI().toString()));
        userIconIV.setFitHeight(100);
        userIconIV.setFitWidth(100);
    }

}
