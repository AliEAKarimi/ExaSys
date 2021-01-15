package GUI.studentPanel;

import GUI.Utils.OnlineClasses;
import GUI.studentPanel.questionsType.DescriptiveQTypeController;
import GUI.studentPanel.questionsType.TestyQTypeController;
import GUI.studentPanel.questionsType.TrueFalseQTypeController;
import Logic.Exam.Exam;
import Logic.Question.DescriptiveQ;
import Logic.Question.Question;
import Logic.Question.TestQ;
import Logic.Question.TrueFalseQ;
import Utils.Utils;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sun.applet.Main;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.util.ArrayList;

public class ShowExamsController {

    @FXML
    private Text hintT;
    @FXML
    private Text examTitleT;
    @FXML
    private TextArea examInformationTA;
    @FXML
    private JFXButton enteredToExamBtn;

    @FXML
    private Exam exam;

    public void enteredToExamBtnAction() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        AnchorPane.setTopAnchor(vBox, 8D);
        AnchorPane.setRightAnchor(vBox, 8D);
        AnchorPane.setLeftAnchor(vBox, 8D);
        AnchorPane.setBottomAnchor(vBox, 8D);
        AnchorPane anchorPane = new AnchorPane(vBox);
        ScrollPane scrollPane = new ScrollPane(anchorPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        AnchorPane anchorPane2 = new AnchorPane(scrollPane);
        AnchorPane.setBottomAnchor(scrollPane, 8D);
        AnchorPane.setLeftAnchor(scrollPane, 8D);
        AnchorPane.setRightAnchor(scrollPane, 8D);
        AnchorPane.setTopAnchor(scrollPane, 8D);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Tab newTab = new Tab(exam.getTitle(), anchorPane2);
        JFXButton endExamBtn = new JFXButton("اتمام آزمون");
        OnlineClasses.mainPageController.getTabPane().getTabs().add(newTab);
        OnlineClasses.mainPageController.getTabPane().getSelectionModel().select(newTab);
        ArrayList<Question> questions = new ArrayList<>(exam.getQuestions());
        for (int i = 0, counter = 0; i < questions.size(); i++, counter++) {
            Question question = null;
            int randomValue;
            if (exam.isRandom()) {
                randomValue = Utils.randomInteger(0, questions.size() - 1);
                question = questions.get(randomValue);
                questions.remove(randomValue);
                i--;
            } else {
                question = questions.get(i);
            }
            if (question instanceof DescriptiveQ) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("questionsType\\descriptiveQType.fxml"));
                try {
                    AnchorPane anchorPane1 = loader.load();
                    vBox.getChildren().add(anchorPane1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DescriptiveQTypeController descriptiveQTypeController = loader.getController();
                descriptiveQTypeController.getQuestionTextTA().setText((counter + 1) + " _ " + question.getText() + "(" + question.getGrade() + "نمره)");
            } else if (question instanceof TestQ) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("questionsType\\testyQType.fxml"));
                try {
                    AnchorPane anchorPane1 = loader.load();
                    vBox.getChildren().add(anchorPane1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TestyQTypeController testyQTypeController = loader.getController();
                testyQTypeController.getQuestionTextTA().setText((counter + 1) + " _ " + question.getText() + "(" + question.getGrade() + "نمره)");
                testyQTypeController.getChoice1RB().setText(((TestQ) question).getChoice1());
                testyQTypeController.getChoice2RB().setText(((TestQ) question).getChoice2());
                testyQTypeController.getChoice3RB().setText(((TestQ) question).getChoice3());
                testyQTypeController.getChoice4RB().setText(((TestQ) question).getChoice4());
            } else if (question instanceof TrueFalseQ) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("questionsType\\trueFalseQType.fxml"));
                try {
                    AnchorPane anchorPane1 = loader.load();
                    vBox.getChildren().add(anchorPane1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TrueFalseQTypeController trueFalseQTypeController = loader.getController();
                trueFalseQTypeController.getQuestionTextTA().setText((counter + 1) + " _ " + question.getText() + "(" + question.getGrade() + "نمره)");
            }
        }
        vBox.getChildren().add(endExamBtn);
        endExamBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OnlineClasses.mainPageController.getTabPane().getTabs().remove(newTab);
            }
        });
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Exam getExam() {
        return exam;
    }

    public Text getExamTitleT() {
        return examTitleT;
    }

    public JFXButton getEnteredToExamBtn() {
        return enteredToExamBtn;
    }

    public TextArea getExamInformationTA() {
        return examInformationTA;
    }

    public Text getHintT() {
        return hintT;
    }
}
