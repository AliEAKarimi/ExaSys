package GUI.studentPanel.questionsType;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class TrueFalseQTypeController implements Initializable {
    @FXML
    private
    TextArea questionTextTA;
    @FXML
    private RadioButton trueRB;
    @FXML
    private RadioButton falseRB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        trueRB.setToggleGroup(toggleGroup);
        falseRB.setToggleGroup(toggleGroup);
    }

    public TextArea getQuestionTextTA() {
        return questionTextTA;
    }
}
