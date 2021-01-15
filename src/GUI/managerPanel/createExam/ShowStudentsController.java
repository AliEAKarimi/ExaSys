package GUI.managerPanel.createExam;

import Database.DBUtils;
import Database.ExaSysDB;
import Files.ExamFiles;
import Files.ExamState;
import Logic.Exam.Exam;
import Logic.User.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.model.Filters;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ShowStudentsController implements Initializable {

    @FXML
    private JFXButton finalConfirmBtn;
    @FXML
    private Text hintT;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton fromExcelBtn;
    @FXML
    private JFXButton manualBtn;
    @FXML
    private JFXButton backBtn;


    @FXML
    private Exam exam;
    @FXML
    private TableView<Student> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void backBtnAction() {
        ((Stage) backBtn.getScene().getWindow()).close();
    }

    public void fromExcelBtnAction() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(fromExcelBtn.getScene().getWindow());
        if (file != null && file.getName().endsWith(".xlsx")) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    double id = 0;
                    String firstName = null, lastName = null;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getColumnIndex()) {
                            case 0:
                                firstName = cell.getStringCellValue();
                                break;
                            case 1:
                                lastName = cell.getStringCellValue();
                                break;
                            case 2:
                                id = cell.getNumericCellValue();
                                break;
                        }
                    }
                    if (!signUpStudent(String.valueOf((long) id), firstName, lastName)) {
                        System.out.println("this student already signUpped.");
                    } else {
                        System.out.println("this student signUpped now.");
                    }
                    addStudentToTable(String.valueOf((long) id), firstName, lastName);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean signUpStudent(String id, String firstName, String lastName) {
        if (DBUtils.isDocumentExist(ExaSysDB.getStudentColl(), "id", id)) {
            return false;
        }
        Document document = new Document("firstName", firstName)
                .append("lastName", lastName)
                .append("id", id)
                .append("username", id)
                .append("password", id)
                .append("currentExams", "");
        ExaSysDB.getStudentColl().insertOne(document);
        return true;
    }

    private void addStudentToTable(String id, String firstName, String lastName) {
        Student student = new Student(firstName, lastName, id);
        if (tableView.getItems().contains(student))
            return;
        if (!exam.getStudents().contains(student))
            exam.getStudents().add(student);
        //100percent we have a student with this id and its information exist in database.
        Document document = (Document) ExaSysDB.getStudentColl().find(Filters.eq("id", id)).first();
        String studentExams = (String) document.get("currentExams");
        if (studentExams == null) studentExams = "";
        ExaSysDB.getStudentColl().updateOne(document, new Document("$set", new Document("currentExams", studentExams + exam.getId() + ",")));
        ExamFiles.addExaminationFile(exam, ExamState.MANAGING_EXAMS);
        tableView.getItems().add(student);
    }


    public void manualBtnAction() {
        JFXTextField firstNameTF = new JFXTextField();
        firstNameTF.setPromptText("نام");
        firstNameTF.setAlignment(Pos.CENTER_RIGHT);
        JFXTextField lastNameTF = new JFXTextField();
        lastNameTF.setPromptText("نام خاموادگی");
        lastNameTF.setAlignment(Pos.CENTER_RIGHT);
        JFXTextField idTF = new JFXTextField();
        idTF.setPromptText("شماره دانشجویی");
        idTF.setAlignment(Pos.CENTER_RIGHT);
        Text hintT = new Text();
        JFXButton addBtn = new JFXButton("اضافه کردن");
        addBtn.setOnAction(event -> {
            String id = idTF.getText();
            String firstName = firstNameTF.getText();
            String lastName = lastNameTF.getText();
            if (id == null || id.equals("") || firstName == null || firstName.equals("") || lastName == null || lastName.equals("")) {
                hintT.setText("اطلاعات را کامل وارد کنید.");
                timer(hintT);
                return;
            }
            if (id.startsWith(" ") || firstName.startsWith(" ") || lastName.startsWith(" ")) {
                hintT.setText("اطلاعات باید بدون فاصله شروع شوند.");
                timer(hintT);
                return;
            }
            if (exam.getStudents().contains(new Student(firstNameTF.getText(), lastNameTF.getText(), idTF.getText()))) {
                hintT.setText("دانشجو قبلا اضافه شده است.");
                timer(hintT);
            } else {
                signUpStudent(id, firstName, lastName);
                addStudentToTable(idTF.getText(), firstNameTF.getText(), lastNameTF.getText());
                ((Stage) addBtn.getScene().getWindow()).close();
            }
        });
        JFXButton backBtn = new JFXButton("برگشت");
        backBtn.setOnAction(event -> ((Stage) backBtn.getScene().getWindow()).close());
        HBox hBox = new HBox(addBtn, backBtn);
        VBox vBox = new VBox(firstNameTF, lastNameTF, idTF, hBox, hintT);
        AnchorPane.setBottomAnchor(vBox, 8D);
        AnchorPane.setLeftAnchor(vBox, 8D);
        AnchorPane.setRightAnchor(vBox, 8D);
        AnchorPane.setTopAnchor(vBox, 8D);
        AnchorPane anchorPane = new AnchorPane(vBox);
        anchorPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        anchorPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        vBox.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        hBox.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        Scene popUpScene = new Scene(anchorPane);
        Stage popUpStage = new Stage();
        popUpStage.setScene(popUpScene);
        popUpStage.initStyle(StageStyle.TRANSPARENT);
        popUpStage.setAlwaysOnTop(true);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.showAndWait();
    }

    private void timer(Text hintT) {
        new Timer(1000, new ActionListener() {
            int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                if (counter >= 2) {
                    hintT.setText(null);
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }

    public void finalConfirmBtnAction() {
        if (exam.getStudents().isEmpty()) {
            hintT.setText("دانشجویی برای آزمون ثبت نشده است.");
            timer();
        } else {
            File file = new File("src\\Files\\files\\ManagingExams\\" + exam.getDesigner().getUsername() + "!" + exam.getId() + ".txt");
            //file.delete();
            ExamFiles.addExaminationFile(exam, ExamState.TAKING_EXAMS);
            hintT.setText("آزمون برای دانشجوها فرستاده شد.");
            finalConfirmBtn.setDisable(true);
            manualBtn.setDisable(true);
            fromExcelBtn.setDisable(true);
            timer();
        }
    }

    @FXML
    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @FXML
    public void setTableView(TableView<Student> tableView) {
        this.tableView = tableView;
    }

    private void timer() {
        new Timer(1000, new ActionListener() {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if (count >= 2) {
                    hintT.setText(null);
                    ((Timer)e.getSource()).stop();
                }
            }
        }).start();
    }
}
