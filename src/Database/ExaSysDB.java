package Database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ExaSysDB {
    private static MongoCollection studentColl;
    private static MongoCollection managerColl;
    private static MongoCollection temporary;
    private static MongoClient mongoClient;
    private static MongoDatabase db;

    private ExaSysDB(){
    }

    private static void init() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("exaSys");
    }

    public static MongoCollection getStudentColl() {
        init();
        return db.getCollection("student");
    }


    public static MongoCollection getManagerColl() {
        init();
        return db.getCollection("manager");
    }

    public static MongoCollection getTemporaryColl() {
        init();
        return db.getCollection("temporary");
    }
    public static MongoCollection getExamCreatedColl() {
        init();
        return db.getCollection("examCreated");
    }
}
