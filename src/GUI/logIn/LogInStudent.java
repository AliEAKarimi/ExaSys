package GUI.logIn;

import Database.DBUtils;
import Database.ExaSysDB;
import Database.StudentColl;
import GUI.Utils.OnlineClasses;
import GUI.mainPage.MainPage;
import GUI.studentPanel.StudentPanel;
import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInStudent implements Initializable {
    @FXML
    private Text hintPasswordT;
    @FXML
    private Text hintUsernameT;
    @FXML
    private AnchorPane changeableAP;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    private MongoCollection studentColl;
    private String passwordOfEnteredUsername;

    public void logIn() {
        init();
        if (isValidInformation()) {
            StudentPanel.setUsername(usernameTF.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../studentPanel/studentPanel.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
            OnlineClasses.setStudentPanelController(loader.getController());
        }
    }

    private boolean isValidInformation() {
        return (checkUsername() && checkPassword());
    }

    private void init() {
        studentColl = ExaSysDB.getStudentColl();
    }

    private boolean checkUsername() {
        String username = usernameTF.getText();
        if (username == null || username.length() == 0) {
            hintUsernameT.setText("نام کاربری خود را وارد کنید.");
            return false;
        }

        if (!DBUtils.isDocumentExist(studentColl, "username", username)) {
            hintUsernameT.setText("نام کاربری اشتباه است.");
            return false;
        }
        hintUsernameT.setText(null);

        //so this username exists in the registered student(100% exist a student in database).
        passwordOfEnteredUsername = StudentColl.getStudentObject(username).getPassword();
        return true;
    }

    private boolean checkPassword() {
        String password = passwordTF.getText();
        if (password == null || password.length() == 0) {
            hintPasswordT.setText("رمز عبور را وارد کنید.");
            return false;
        }
        if (password.length() < 8) {
            hintPasswordT.setText("رمز عبور باید حداقل شامل 8 کاراکتر باشد.");
            return false;
        }
        if (!password.equals(passwordOfEnteredUsername)) {
            hintPasswordT.setText("رمز ورود اشتباه است.");
            return false;
        }
        hintPasswordT.setText(null);
        return true;
    }

    public void signUp() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../signUp/signUpStudent.fxml"));
        MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


