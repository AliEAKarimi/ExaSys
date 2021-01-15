package GUI.studentPanel;

import Database.StudentColl;
import Files.ExamFiles;
import Files.ExamState;
import Logic.Exam.Exam;
import Logic.User.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class FutureExamsController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private FlowPane futureExamsFP;

    private void uploadExams() {
        Student student = StudentColl.getStudentObject(StudentPanel.getUsername());
        File file = new File("src\\Files\\files\\TakingExam");
        if (file.listFiles().length != 0) {
            for (File subFile : file.listFiles()) {
                for (String currentExamId : student.getCurrentExam()) {
                    if (subFile.getName().endsWith(currentExamId + ".txt")) {
                        Exam exam = ExamFiles.getExamination(subFile.getName(), ExamState.TAKING_EXAMS);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("showExams.fxml"));
                        AnchorPane anchorPane = null;
                        try {
                            anchorPane = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ShowExamsController showExamsController = loader.getController();
                        showExamsController.setExam(exam);
                        showExamsController.getExamTitleT().setText(exam.getTitle());
                        String random, outright, see;
                        if (exam.isRandom())
                            random = "-سوالات به صورت رندوم پخش خواهند شد.\n";
                        else
                            random = "-ترتیب سوالات برای دانشجویان یکسان است.\n";
                        if (exam.isOutright())
                            outright = "-شما در طول آزمون به همه سوالات دسترسی دارید و تمام سوالات به یکباره در دسترس شما خواهند بود.\n";
                        else
                            outright = "-سوالات تک به تک در دسترس شما قرار خواهد گرفت و بایستی در زمان مربوط به خود آن را پاسخ دهید.\n";
                        if (exam.isSee())
                            see = "-پس از آزمون شما می توانید آزمون خود را مرور کنید.\n";
                        else
                            see = "-شما قابلیت مرور آزمون پس از آزمون را نخواهید داشت.\n";
                        showExamsController.getExamInformationTA().setText(student.getFirstName() + " " + student.getLastName()
                                + " عزیز \n" + "ضمن عرض سلام و خسته نباشید خدمت شما، به استحضار می رسانم که آزمون در تاریخ " + exam.getStartDate() + " ساعت " + exam.getStartTime()
                                + " آغاز خواهد شد و سوالات در دسترس شما خواهند بود." + " شما تا تاریخ " + exam.getEndDate() + " ساعت " + exam.getEndTime() + " مهلت پاسخگویی دارید.\n" + "لازم به ذکر است که\n"
                                + random + outright + see + "زمان کل آزمون " + exam.getExamTime() + "\n موفق باشید." + "\nایجاد کننده : " + exam.getDesigner().getFirstName() + " " + exam.getDesigner().getLastName());

                        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String startDate = exam.getStartDate().toString();
                        String endDate = exam.getEndDate().toString();
                        String nowDate = LocalDate.now().toString();
                        Date firstDate = null, secondDate = null, thirdDate = null;
                        try {
                            firstDate = myFormat.parse(startDate);
                            secondDate = myFormat.parse(endDate);
                            thirdDate = myFormat.parse(nowDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long milliSecondStart = firstDate.getTime() + exam.getStartTime().toSecondOfDay() * 1000;
                        long milliSecondEnd = secondDate.getTime() + exam.getEndTime().toSecondOfDay() * 1000;
                        long milliSecondNow = thirdDate.getTime() +  LocalTime.now().toSecondOfDay() * 1000;
                        if (milliSecondNow >= milliSecondStart && milliSecondNow <= milliSecondEnd) {
                            showExamsController.getEnteredToExamBtn().setDisable(false);
                            showExamsController.getHintT().setText("می توانید در آزمون شرکت کنید.");
                        } else {
                            showExamsController.getEnteredToExamBtn().setDisable(true);
                            if (milliSecondNow < milliSecondStart) {
                                showExamsController.getHintT().setText("آزمون هنوز آغاز نشده است.");
                                showExamsController.getEnteredToExamBtn().setDisable(true);
                            } else if (milliSecondNow > milliSecondEnd) {
                                showExamsController.getHintT().setText("آزمون به پایان رسیده است.");
                                showExamsController.getEnteredToExamBtn().setDisable(true);
                            }
                        }
                        futureExamsFP.getChildren().add(anchorPane);
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uploadExams();
    }
}
