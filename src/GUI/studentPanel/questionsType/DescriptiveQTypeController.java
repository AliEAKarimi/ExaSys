package GUI.studentPanel.questionsType;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class DescriptiveQTypeController {
    @FXML
    private ImageView imageView;
    @FXML
    private TextArea questionTextTA;
    @FXML
    private TextArea answerTextTA;
    @FXML
    private JFXButton uploadImageBtn;


    public void uploadImageBtnAction() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("jpg images", "*.jpg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(uploadImageBtn.getScene().getWindow());
        imageView.setImage(new Image(file.toURI().toString()));
    }

    public TextArea getQuestionTextTA() {
        return questionTextTA;
    }

    public JFXButton getUploadImageBtn() {
        return uploadImageBtn;
    }

    public TextArea getAnswerTextTA() {
        return answerTextTA;
    }
}
