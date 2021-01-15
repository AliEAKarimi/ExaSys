package GUI.mainPage;

import GUI.Utils.OnlineClasses;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage extends Application implements Initializable {
    @FXML
    private ScrollPane exaSysScrollPane;
    @FXML
    private BorderPane exaSysBorderPane;
    private static BorderPane borderPaneAuxiliary;
    @FXML
    private OctIconView plusIcon;
    @FXML
    private Tab googleTab;
    @FXML
    private Tab facebookTab;
    @FXML
    private Tab youtubeTab;
    @FXML
    private Tab exaSysTab;
    @FXML
    private TabPane tabPane;
    @FXML
    private ImageView backgroundExaSysIV;
    @FXML
    private AnchorPane exaSysAP;
    @FXML
    private OctIconView searchIc;
    @FXML
    private WebView googleWebView;
    @FXML
    private WebView facebookWebView;
    @FXML
    private WebView youtubeWebView;
    @FXML
    private TextField addressTF;

    private AnchorPane root;
    private Scene scene;
    private Stage primaryStage;

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OnlineClasses.setMainPageController(loader.getController());
        scene = new Scene(root);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("https://www.exaSys.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String urlWeb = "http://google.com";
        addressTF.setText(urlWeb);
        searchAddress(googleWebView);
        //backgroundExaSysIV.fitWidthProperty().bind(exaSysAP.widthProperty());
        borderPaneAuxiliary = exaSysBorderPane;
    }

    public void facebookWebPage() {
        if (facebookTab.isSelected()) {
            tabPane.getSelectionModel().clearSelection();
            tabPane.getSelectionModel().select(1);
            String urlWeb = "http://facebook.com";
            addressTF.setText(urlWeb);
            searchAddress(facebookWebView);
        }
    }

    public void youtubeWebPage() {
        if (youtubeTab.isSelected()) {
            tabPane.getSelectionModel().clearSelection();
            tabPane.getSelectionModel().select(2);
            String urlWeb = "http://youtube.com";
            addressTF.setText(urlWeb);
            searchAddress(youtubeWebView);
        }
    }

    public void googleWebPage() {
        if (googleTab.isSelected()) {
            tabPane.getSelectionModel().clearSelection();
            tabPane.getSelectionModel().select(0);
            String urlWeb = "http://google.com";
            addressTF.setText(urlWeb);
            searchAddress(googleWebView);
        }
    }

    public void exaSysPage() {
        addressTF.setText("http://exaSys.com");
    }


    public void searchAddress(WebView webView) {
        String address = addressTF.getText();
        if (address != null && !address.isEmpty()) {
            if (address.equals("http://"))
                return;
            final WebEngine webEngine = webView.getEngine();
            if (!address.startsWith("http://")) {
                address = "http://" + address;
                addressTF.setText(address);
            }
            webEngine.load(address);
        }
    }

    public void searchBtnAction() {
        String address = addressTF.getText();
        if (address.equals("google.com") || address.equals("http://google.com") ||
                address.equals("www.google.com") || address.equals("http://www.google.com")) {
            tabPane.getSelectionModel().select(googleTab);
            googleWebPage();
        } else if (address.equals("facebook.com") || address.equals("http://facebook.com") ||
                address.equals("www.facebook.com") || address.equals("http://www.facebook.com")) {
            tabPane.getSelectionModel().select(facebookTab);
            facebookWebPage();
        } else if (address.equals("youtube.com") || address.equals("http://youtube.com") ||
                address.equals("www.youtube.com") || address.equals("http://www.youtube.com")) {
            tabPane.getSelectionModel().select(youtubeTab);
            youtubeWebPage();
        } else if (address == ("exaSys.com") || address.equals("http://exaSys.com") ||
                address.equals("www.exaSys.com") || address.equals("http://www.exaSys.com")) {
            tabPane.getSelectionModel().select(exaSysTab);
            exaSysPage();
        } else {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            searchAddress((WebView) (((AnchorPane) selectedTab.getContent()).getChildren().get(0)));
            selectedTab.setText(address);
        }
    }

    public void studentPanel() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../logIn/logInStudent.fxml"));
        exaSysBorderPane.setCenter(anchorPane);
    }

    public void managerPanel() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(("../logIn/logInManager.fxml")));
        exaSysBorderPane.setCenter(anchorPane);
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void plusBtnAction() {
        AnchorPane anchorPane = new AnchorPane();
        Tab newTab = new Tab("new tab", anchorPane);
        WebView newWebView = new WebView();

        AnchorPane.setLeftAnchor(newWebView, 0D);
        AnchorPane.setRightAnchor(newWebView, 0D);
        AnchorPane.setTopAnchor(newWebView, 0D);
        AnchorPane.setBottomAnchor(newWebView, 0D);

        anchorPane.getChildren().add(newWebView);

        tabPane.getTabs().add(newTab);

        addressTF.setText("http://");

        tabPane.getSelectionModel().select(newTab);
    }

    public static BorderPane getBorderPaneAuxiliary() {
        return borderPaneAuxiliary;
    }

    public void mainPageMenuAction() {
        exaSysBorderPane.setCenter(exaSysScrollPane);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
