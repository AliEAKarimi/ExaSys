package GUI.studentPanel.questionsType;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TestyQTypeController implements Initializable {
    @FXML private
    TextArea questionTextTA;
    @FXML private RadioButton choice1RB;
    @FXML private RadioButton choice2RB;
    @FXML private RadioButton choice3RB;
    @FXML private RadioButton choice4RB;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        choice1RB.setToggleGroup(toggleGroup);
        choice2RB.setToggleGroup(toggleGroup);
        choice3RB.setToggleGroup(toggleGroup);
        choice4RB.setToggleGroup(toggleGroup);
    }

    public TextArea getQuestionTextTA() {
        return questionTextTA;
    }

    public RadioButton getChoice1RB() {
        return choice1RB;
    }

    public RadioButton getChoice2RB() {
        return choice2RB;
    }

    public RadioButton getChoice3RB() {
        return choice3RB;
    }

    public RadioButton getChoice4RB() {
        return choice4RB;
    }
}
