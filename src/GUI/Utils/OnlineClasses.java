package GUI.Utils;

import GUI.mainPage.MainPage;
import GUI.managerPanel.ManagerPanel;
import GUI.managerPanel.createExam.ExamDefining;
import GUI.managerPanel.createExam.ExamManagingController;
import GUI.managerPanel.createExam.questionsTypes.ChooseQType;
import GUI.managerPanel.createExam.questionsTypes.QuestionDesigning;
import GUI.studentPanel.StudentPanel;

public class OnlineClasses {

    public static ExamDefining examDefining;
    public static QuestionDesigning questionDesigning;
    public static ChooseQType chooseQType;
    public static ManagerPanel managerPanel;
    public static ExamManagingController examManagingController;
    public static StudentPanel studentPanelController;
    public static MainPage mainPageController;

    public static void setChooseQType(ChooseQType chooseQType) {
        OnlineClasses.chooseQType = chooseQType;
    }

    public static void setExamDefining(ExamDefining examDefining) {
        OnlineClasses.examDefining = examDefining;
    }

    public static void setQuestionDesigning(QuestionDesigning questionDesigning) {
        OnlineClasses.questionDesigning = questionDesigning;
    }

    public static void setManagerPanel(ManagerPanel managerPanel) {
        OnlineClasses.managerPanel = managerPanel;
    }

    public static void setExamManagingController(ExamManagingController examManagingController) {
        OnlineClasses.examManagingController = examManagingController;
    }

    public static void setStudentPanelController(StudentPanel studentPanelController) {
        OnlineClasses.studentPanelController = studentPanelController;
    }

    public static void setMainPageController(MainPage mainPageController) {
        OnlineClasses.mainPageController = mainPageController;
    }
}
