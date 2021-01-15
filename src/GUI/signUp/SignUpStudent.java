package GUI.signUp;

import Database.DBUtils;
import Database.ExaSysDB;
import Database.StudentColl;
import GUI.mainPage.MainPage;
import Logic.User.Student;
import com.mongodb.*;
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


public class SignUpStudent implements Initializable {

    @FXML private AnchorPane changeableAP;
    @FXML private TextField fNameTF;
    @FXML private Text hintFNameT;
    @FXML private TextField lNameTF;
    @FXML private Text hintLNameT;
    @FXML private TextField studentIdTF;
    @FXML private Text hintStudentNumberT;
    @FXML private TextField userNameTF;
    @FXML private Text hintUserNameT;
    @FXML private PasswordField passwordTF;
    @FXML private Text hintPasswordT1;
    @FXML private PasswordField passwordConfirmTF;
    @FXML private Text hintPasswordT2;

    public void singUpStudent() {
        if (isValidInformation()) {
            MongoCollection studentColl = ExaSysDB.getStudentColl();
            studentColl.insertOne(StudentColl.getStudentDoc(new Student(fNameTF.getText(), lNameTF.getText(), studentIdTF.getText(), userNameTF.getText(), passwordTF.getText())));
            logIn();
            System.out.println("The student was registered.");
        }
    }

    private boolean isValidInformation() {
        return (checkStudentId() & checkPassword() & checkUsername() & checkName());
    }

    private boolean checkStudentId() {
        String id = studentIdTF.getText();
        if (id == null || id.length() == 0) {
            hintStudentNumberT.setText("شماره دانشجویی خود را وارد کنید.");
            return false;
        }
        if (id.contains(" ")) {
            hintStudentNumberT.setText("شماره دانشجویی خود را بدون فاصله وارد کنید.");
            return false;
        }
        if (id.length() < 10) {
            hintStudentNumberT.setText("شماره دانشجویی باید 10 رقم باشد.");
            return false;
        }
        hintStudentNumberT.setText(null);
        return true;
    }

    private boolean checkUsername() {
        //check other filter for valid username.
        String username = userNameTF.getText();
        if (username == null || username.length() == 0) {
            hintUserNameT.setText("نام کاربری را وارد کنید.");
            return false;
        }
        if (username.contains(" ")) {
            hintUserNameT.setText("نام کاربری را بدون فاصله وارد کنید.");
            return false;
        }
        MongoCollection studentColl = ExaSysDB.getStudentColl();
        if (DBUtils.isDocumentExist(studentColl,"username", username)) {
            hintUserNameT.setText("این نام کاربری قبلا ثبت شده است.");
            return false;
        }
        hintUserNameT.setText(null);
        return true;
    }

    private boolean checkPassword() {
        //check other filter for valid password.
        String password1 = passwordTF.getText();
        String password2 = passwordConfirmTF.getText();
        if (password1 == null || password1.length() == 0) {
            hintPasswordT1.setText("رمز عبور را وارد کنید.");
            return false;
        }
        if (password1.length() < 8) {
            hintPasswordT1.setText("رمز عبور باید حداقل شامل 8 کاراکتر باشد.");
            return false;
        }
        hintPasswordT1.setText(null);
        if (password2.length() == 0) {
            hintPasswordT2.setText("تکرار رمز عبور را وارد کنید.");
            return false;
        }
        if (!password2.equals(password1)) {
            hintPasswordT2.setText("رمز عبور و تکرار آن تطابق ندارد.");
            return false;
        }
        hintPasswordT2.setText(null);
        return true;
    }

    private boolean checkName() {
        return (checkFName() & checkLName());
    }
    private boolean checkFName() {
        String fName = fNameTF.getText();
        if (fName == null || fName.length() == 0) {
            hintFNameT.setText("نام خود را وارد کنید.");
            return false;
        }
        if (fName.startsWith(" ")) {
            hintFNameT.setText("نام نمی تواند با فاصله شروع شود.");
            return false;
        }
        hintFNameT.setText(null);
        return true;
    }
    private boolean checkLName() {
        String lName = lNameTF.getText();
        if (lName == null || lName.length() == 0) {
            hintLNameT.setText("نام خانوادگی خود را وارد کنید.");
            return false;
        }
        if (lName.startsWith(" ")) {
            hintFNameT.setText("نام خانوادگی نمی تواند با فاصله شروع شود.");
            return false;
        }
        hintLNameT.setText(null);
        return true;
    }
    public void logIn(){
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("../logIn/logInStudent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
