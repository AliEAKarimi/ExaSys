package Files;

import Logic.Exam.Exam;
import Utils.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.*;

public class ExamFiles {
    private ExamFiles() {
    }

    public static synchronized String createExamID(ExamState state) {
        StringBuilder id = new StringBuilder(15);
        int length = Utils.randomInteger(10, 15);
        for (int i = 0; i < length; i++) {
            char c = (char) Utils.randomInteger(65, 90);
            id.append(c);
        }
        if (examExistsFile(id.toString(), state))
            return createExamID(state);
        else return id.toString();
    }

    private static synchronized boolean examExistsFile(String id, ExamState state) {
        File file = new File(state.getAddress());
        if (file.listFiles().length != 0) {
            for (File subFile : file.listFiles())
                if (subFile.getName().endsWith(id + ".txt")) return true;
        }
        return false;
    }

    public static synchronized boolean addExaminationFile(Exam examination, ExamState state) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            String fileName = examination.getDesigner().getUsername() + "!" + examination.getId() + ".txt";
            File file = new File(state.getAddress() + fileName);
            os = new FileOutputStream(file);
            oos = new ObjectOutputStream(os);
            oos.writeObject(examination);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (os != null) {
                try {
                    assert oos != null;
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static synchronized Exam getExamination(String fileName, ExamState state) {
        InputStream is = null;
        ObjectInputStream ois = null;
        try {
            File file = new File(state.getAddress() + fileName);
            is = new FileInputStream(file);
            ois = new ObjectInputStream(is);
            Exam examination = (Exam) ois.readObject();
            return examination;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
