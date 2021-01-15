package Database;

import Logic.User.Manager;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class ManagerColl {
    private ManagerColl() {
    }

    public static Document getManagerDoc(Manager manager) {
        Document studentDoc = new Document("firstName", manager.getFirstName())
                .append("lastName", manager.getLastName())
                .append("username", manager.getUsername())
                .append("password", manager.getPassword());
        return studentDoc;
    }

    //this method get the value of a username and then search in database and then return related manager if exist.
    public static Manager getManagerObject(String value) {
        Document document = (Document) ExaSysDB.getManagerColl().find(Filters.eq("username", value)).first();
        if (document == null)
            return null;
        //so there is a Student with this username.
        Manager manager = new Manager((String) document.get("firstName"),
                (String) document.get("lastName"), value,
                (String) document.get("password"));
        return manager;
    }

}
