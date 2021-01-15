package GUI.managerPanel.createExam;

import Database.ManagerColl;
import Files.ExamFiles;
import Files.ExamState;
import GUI.Utils.OnlineClasses;
import GUI.managerPanel.createExam.questionsTypes.QuestionDesigning;
import Logic.Exam.Exam;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ExamDefining implements Initializable {
    public JFXButton nextStepBtn;
    public JFXButton cancelBtn;
    public JFXButton pastStepBtn;
    public TextField examTitleTF;
    public JFXCheckBox outrightExamCB;
    public JFXCheckBox randomExamCB;
    public JFXCheckBox seeQuAAnCB;
    public DatePicker startDateDP;
    public JFXTimePicker startTimeTP;
    public DatePicker endDateDP;
    public JFXTimePicker endTimeTP;
    public Text examTimeText;
    public Text hintExamTitleText;
    public Text hintDegreeOfEducationT;
    public Text hintFieldOfStudyT;
    public Text hintStartTimeT;
    public Text hintStartDateT;
    public Text hintEndDateT;
    public Text hintEndTimeT;
    public JFXRadioButton questionDesignRB;
    public JFXButton confirmBtn;
    public Text hintQuestionCheck;
    @FXML
    private JFXRadioButton examDefineRB;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXComboBox<String> degreeOfEducationCB;
    @FXML
    private JFXComboBox<String> fieldOfStudyCB;

    private ScrollPane examDefiningPageSP;
    private BorderPane questionDesigningPageBP;
    private Step createExamStep;
    private Exam exam;

    private String examTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> degrees =
                FXCollections.observableArrayList(
                        "کارشناسی",
                        "ارشد",
                        "دکترا"
                );
        degreeOfEducationCB.setItems(degrees);

        ObservableList<String> fields =
                FXCollections.observableArrayList(
                        "مهندسی کامپیوتر",
                        "مهندسی برق",
                        "علوم کامپیوتر"
                );
        fieldOfStudyCB.setItems(fields);

        startTimeTP.set24HourView(true);
        endTimeTP.set24HourView(true);

        examDefineRB.setDisable(true);
        pastStepBtn.setDisable(true);
        questionDesignRB.setDisable(true);
        confirmBtn.setVisible(false);

        createExamStep = Step.EXAM_DEFINING;
    }

    public void nextStepBtnAction() throws IOException {
        if (createExamStep == Step.EXAM_DEFINING) {
            //we are in defining exam.
            if (isValidExamDefiningPart()) {
                examDefiningPageSP = (ScrollPane) this.borderPane.getCenter();
                examDefineRB.setSelected(true);
                pastStepBtn.setDisable(false);
                nextStepBtn.setDisable(true);
                confirmBtn.setVisible(true);

                if (questionDesigningPageBP != null) {
                    this.borderPane.setCenter(questionDesigningPageBP);
                    System.out.println("first");
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("questionsTypes/questionDesigning.fxml"));
                    BorderPane borderPane = loader.load();
                    OnlineClasses.setQuestionDesigning(loader.getController());
                    questionDesigningPageBP = borderPane;
                    this.borderPane.setCenter(borderPane);
                    System.out.println("second");
                }
                createExamObject();
                createExamStep = Step.QUESTION_DESIGNING;
            }
        }
    }
    public void cancelBtnAction() {
        OnlineClasses.managerPanel.createExamBtnAction();
    }
    private void createExamObject() {
        exam = new Exam();
        exam.setDesigner(ManagerColl.getManagerObject(OnlineClasses.managerPanel.getUsername()));
        exam.setDegreeEducation(degreeOfEducationCB.getValue());
        exam.setFieldOfStudy(fieldOfStudyCB.getValue());
        exam.setTitle(examTitleTF.getText());
        exam.setStartTime(startTimeTP.getValue());
        exam.setStartDate(startDateDP.getValue());
        exam.setEndTime(endTimeTP.getValue());
        exam.setEndDate(endDateDP.getValue());
        exam.setOutright(outrightExamCB.isSelected());
        exam.setRandom(randomExamCB.isSelected());
        exam.setSee(seeQuAAnCB.isSelected());
        exam.setId(ExamFiles.createExamID(ExamState.MANAGING_EXAMS));
        exam.setExamTime(examTime);
    }

    public void pastStepBtnAction() {
        if (createExamStep == Step.QUESTION_DESIGNING) {
            questionDesigningPageBP = (BorderPane) this.borderPane.getCenter();
            this.borderPane.setCenter(examDefiningPageSP);
            pastStepBtn.setDisable(true);
            nextStepBtn.setDisable(false);
            confirmBtn.setVisible(false);
            examDefineRB.setSelected(false);
            createExamStep = Step.EXAM_DEFINING;
        }
    }

    private boolean isValidExamDefiningPart() {
        if (isValidExamTitle() & isValidDegreeOfEducation() & isValidFieldOfStudyCB()
                & isValidExamStartDate() & isValidExamEndDate() & isValidExamStartTime()
                & isValidExamEndTime()) {
            return checkDateAndTime();
        }
        return false;
    }

    private boolean isValidExamTitle() {
        String examTitle = examTitleTF.getText();
        if (examTitle == null || examTitle.isEmpty()) {
            hintExamTitleText.setText("عنوان آزمون را وارد کنید.");
            return false;
        }
        if (examTitle.startsWith(" ")) {
            hintExamTitleText.setText("عنوان آزمون نمی تواند با فاصله شروع شود.");
            return false;
        }
        hintExamTitleText.setText(null);
        return true;
    }

    public boolean isValidDegreeOfEducation() {
        if (degreeOfEducationCB.getSelectionModel().getSelectedItem() == null) {
            hintDegreeOfEducationT.setText("مدرک تحصیلی را انتخاب کنید.");
            return false;
        }
        hintDegreeOfEducationT.setText(null);
        return true;
    }

    private boolean isValidFieldOfStudyCB() {
        if (fieldOfStudyCB.getSelectionModel().getSelectedItem() == null) {
            hintFieldOfStudyT.setText("رشته تحصیلی را انتخاب کنید.");
            return false;
        }
        hintFieldOfStudyT.setText(null);
        return true;
    }

    private boolean isValidExamStartDate() {
        if (startDateDP.getValue() == null) {
            hintStartDateT.setText("تاریخ آغاز آزمون را وارد کنید.");
            return false;
        }
        if (startDateDP.getValue().isBefore(LocalDate.now())) {
            hintStartDateT.setText("تاریخ آغاز آزمون اشتباه است.");
            return false;
        }
        hintStartDateT.setText(null);
        return true;
    }

    private boolean isValidExamEndDate() {
        if (endDateDP.getValue() == null) {
            hintEndDateT.setText("تاریخ اتمام آزمون را وارد کنید.");
            return false;
        }
        hintEndDateT.setText(null);
        return true;
    }

    private boolean isValidExamStartTime() {
        if (startTimeTP.getValue() == null) {
            hintStartTimeT.setText("زمان آغاز آزمون را وارد کنید.");
            return false;
        }
        if (startDateDP.getValue().equals(LocalDate.now())) {
            if (startTimeTP.getValue().isBefore(LocalTime.now())) {
                hintStartTimeT.setText("زمان آغاز آزمون اشتباه است.");
                return false;
            }
        }
        hintStartTimeT.setText(null);
        return true;
    }

    private boolean isValidExamEndTime() {
        if (endTimeTP.getValue() == null) {
            hintEndTimeT.setText("زمان اتمام آزمون را وارد کنید.");
            return false;
        }
        hintEndTimeT.setText(null);
        return true;
    }

    private boolean checkDateAndTime() {
        if (endDateDP.getValue().isBefore(startDateDP.getValue())) {
            hintEndDateT.setText("تاریخ اتمام آزمون اشتباه است.");
            return false;
        }
        if (endDateDP.getValue().equals(startDateDP.getValue())) {
            if (endTimeTP.getValue().isBefore(startTimeTP.getValue())) {
                hintEndTimeT.setText("زمان اتمام آزمون اشتباه است.");
                return false;
            }
        }
        hintEndTimeT.setText(null);
        return true;
    }

    public void computeExamTime() throws ParseException {
        long milliSecondStartTime, milliSecondEndTime;
        if (startDateDP.getValue() != null && endDateDP.getValue() != null
                && startTimeTP.getValue() != null && endTimeTP.getValue() != null) {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = startDateDP.getValue().toString();
            String endDate = endDateDP.getValue().toString();
            Date firstDate = myFormat.parse(startDate);
            Date secondDate = myFormat.parse(endDate);
            milliSecondStartTime = startTimeTP.getValue().toSecondOfDay() * 1000;
            milliSecondEndTime = endTimeTP.getValue().toSecondOfDay() * 1000;
            long differenceMilliSecond = (secondDate.getTime() + milliSecondEndTime) - (firstDate.getTime() + milliSecondStartTime);
            if (differenceMilliSecond <= 0) {
                examTimeText.setText("00:00");
                examTime = "0";
            } else {
                long days = TimeUnit.MILLISECONDS.toDays(differenceMilliSecond);
                long hour = TimeUnit.MILLISECONDS.toHours(differenceMilliSecond) - TimeUnit.DAYS.toHours(days);
                long minute = TimeUnit.MILLISECONDS.toMinutes(differenceMilliSecond) - TimeUnit.HOURS.toMinutes(hour) - TimeUnit.DAYS.toMinutes(days);
                examTimeText.setText(days + " روز و " + hour + " ساعت و " + minute + " دقیقه ");
                examTime = days + " روز و " + hour + " ساعت و " + minute + " دقیقه ";
            }
        }
    }

    public void confirmBtnAction() {
        if (!QuestionDesigning.getQuestionsSpacesVBoxAuxiliary().getChildren().iterator().hasNext()) {
            hintQuestionCheck.setText("برای ثبت آزمون حداقل یک سوال تعریف کنید.");
            new Timer(1000, new ActionListener() {
                int counter = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    counter++;
                    if (counter >= 2) {
                        hintQuestionCheck.setText(null);
                        ((Timer) e.getSource()).stop();
                    }
                }
            }).start();
        } else {
            ExamFiles.addExaminationFile(exam, ExamState.MANAGING_EXAMS);
            OnlineClasses.managerPanel.getBorderPane().setCenter(null);
/*
            hintQuestionCheck.setText("آزمون شما در قسمت مدیریت آزمون قرار گرفت.");
            new Timer(1000, new ActionListener() {
                int counter = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    counter++;
                    if (counter >= 2) {
                        hintQuestionCheck.setText(null);
                        ((Timer) e.getSource()).stop();
                    }
                }
            }).start();
*/
        }
    }

    public Exam getExam() {
        return exam;
    }

    public boolean getValueOutright() {
        return outrightExamCB.isSelected();
    }

}

enum Step {
    EXAM_DEFINING, QUESTION_DESIGNING
}