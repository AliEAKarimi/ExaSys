package Database;

import Logic.User.Student;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;

public class StudentColl {
    private StudentColl() {
    }

    public static Document getStudentDoc(Student student) {
        Document studentDoc = new Document("firstName", student.getFirstName())
                .append("lastName", student.getLastName())
                .append("id", student.getId())
                .append("username", student.getUsername())
                .append("password", student.getPassword())
                .append("currentExams", "");
        return studentDoc;
    }

    //this method get the value of a username and then search in database and then return related student if it exist.
    public static Student getStudentObject(String value) {
        Document document = (Document) ExaSysDB.getStudentColl().find(Filters.eq("username", value)).first();
        if (document == null)
            return null;
        //so there is a Student with this username.
        Student student = new Student((String) document.get("firstName"),
                (String) document.get("lastName"), (String) document.get("id"),
                value, (String) document.get("password"));
        if (document.get("currentExams") != null)
            student.setCurrentExam(((String) document.get("currentExams")).split(","));
        return student;
    }
}
