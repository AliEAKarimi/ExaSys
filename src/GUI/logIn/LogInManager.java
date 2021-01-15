package GUI.logIn;

import Database.DBUtils;
import Database.ExaSysDB;
import Database.ManagerColl;
import GUI.Utils.OnlineClasses;
import GUI.mainPage.MainPage;
import GUI.managerPanel.ManagerPanel;
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

public class LogInManager implements Initializable {
    @FXML
    private TextField usernameTF;
    @FXML
    private Text hintUsernameT;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Text hintPasswordT;
    @FXML
    private AnchorPane changeableAnchorPane;

    private MongoCollection managerColl;
    private String passwordOfEnteredUsername;

    public void logIn() throws IOException {
        init();
        if (isValidInformation()) {
            ManagerPanel.setUsername(usernameTF.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../managerPanel/managerPanel.fxml"));
            AnchorPane anchorPane = loader.load();
            OnlineClasses.setManagerPanel(loader.getController());
            MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
        }
    }

    private boolean isValidInformation() {
        return (checkUsername() && checkPassword());
    }

    private void init() {
        managerColl = ExaSysDB.getManagerColl();
    }

    private boolean checkUsername() {
        String username = usernameTF.getText();
        if (username == null || username.length() == 0) {
            hintUsernameT.setText("نام کاربری خود را وارد کنید.");
            return false;
        }

        if (!DBUtils.isDocumentExist(managerColl, "username", username)) {
            hintUsernameT.setText("نام کاربری اشتباه است.");
            return false;
        }
        hintUsernameT.setText(null);
        //so the this username exists in the registered managers(100% exist a manager in database).
        passwordOfEnteredUsername = ManagerColl.getManagerObject(username).getPassword();
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

    public void sigUp() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../signUp/signUpManager.fxml"));
        MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
