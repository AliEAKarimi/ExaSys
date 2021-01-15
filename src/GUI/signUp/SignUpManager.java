package GUI.signUp;

import Database.DBUtils;
import Database.ExaSysDB;
import Database.ManagerColl;
import GUI.mainPage.MainPage;
import Logic.User.Manager;
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

public class SignUpManager implements Initializable {

    @FXML private AnchorPane changeableAP;
    @FXML private TextField usernameTF;
    @FXML private Text hintUsernameT;
    @FXML private TextField fNameTF;
    @FXML private Text hintFNameT;
    @FXML private TextField lNameTF;
    @FXML private Text hintLNameT;
    @FXML private PasswordField passwordTF;
    @FXML private Text hintPasswordT1;
    @FXML private PasswordField passwordConfirmTF;
    @FXML private Text hintPasswordT2;

    public void signUpManager() {
        if (isValidInformation()) {
            MongoCollection managerColl = ExaSysDB.getManagerColl();
            managerColl.insertOne(ManagerColl.getManagerDoc(new Manager(fNameTF.getText(), lNameTF.getText(), usernameTF.getText(), passwordTF.getText())));
            System.out.println("The manager was registered.");
            logIn();
        }
    }
    private boolean isValidInformation() {
        return (checkPassword() & checkUsername() & checkName());
    }
    private boolean checkUsername() {
        String username = usernameTF.getText();
        if (username == null || username.length() == 0) {
            hintUsernameT.setText("نام کاربری خود را وارد کنید.");
            return false;
        }
        if (username.contains(" ")) {
            hintUsernameT.setText("نام کاربری را بدون فاصله وارد کنید.");
            return false;
        }
        MongoCollection managerColl = ExaSysDB.getManagerColl();
        if (DBUtils.isDocumentExist(managerColl, "username", username)) {
            hintUsernameT.setText("این نام کاربری قبلا ثبت شده است.");
            return false;
        }
        hintUsernameT.setText(null);
        return true;
    }
    public boolean checkPassword() {
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
            anchorPane = FXMLLoader.load(getClass().getResource("../logIn/logInManager.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
