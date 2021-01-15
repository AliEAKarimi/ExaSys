package GUI.managerPanel;

import Database.ManagerColl;
import Files.ImageFiles;
import Files.ImageState;
import GUI.Utils.OnlineClasses;
import GUI.mainPage.MainPage;
import Logic.User.Manager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerPanel implements Initializable {

    @FXML
    private BorderPane borderPane;
    private static BorderPane borderPaneAuxiliary;
    @FXML
    private ImageView userIconIV;
    @FXML
    private Text managerNameT;
    @FXML
    private static String username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Manager manager = ManagerColl.getManagerObject(username);
        managerNameT.setText(manager.getFirstName() + " " + manager.getLastName());
        borderPaneAuxiliary = borderPane;
        Image image = ImageFiles.getImageProfile(ImageState.MANAGER_PROFILE_IMAGE, username);
        if (image != null) {
            userIconIV.setImage(image);
            userIconIV.setFitWidth(100);
            userIconIV.setFitHeight(100);
        } else {
            userIconIV.setImage(new Image("resource\\user_icon.png"));
        }
    }

    public static void setUsername(String username) {
        ManagerPanel.username = username;
    }

    public static String getUsername() {
        return ManagerPanel.username;
    }

    public void createExamBtnAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createExam/examDefining.fxml"));
        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.borderPane.setCenter(borderPane);
        OnlineClasses.setExamDefining(loader.getController());
    }

    public void examManagingBtnAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createExam/examManaging.fxml"));
        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.borderPane.setCenter(borderPane);
        OnlineClasses.setExamManagingController(loader.getController());
    }

    public static BorderPane getBorderPaneAuxiliary() {
        return borderPaneAuxiliary;
    }

    public synchronized BorderPane getBorderPane() {
        return borderPane;
    }

    public void exitBtnAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../logIn/logInManager.fxml"));
        try {
            AnchorPane anchorPane = loader.load();
            MainPage.getBorderPaneAuxiliary().setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imageViewAction() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("jpg images", "*.jpg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(userIconIV.getScene().getWindow());
        ImageFiles.addImageProfile(ImageState.MANAGER_PROFILE_IMAGE, file, username);
        userIconIV.setImage(new Image(file.toURI().toString()));
        userIconIV.setFitHeight(100);
        userIconIV.setFitWidth(100);
    }
}

