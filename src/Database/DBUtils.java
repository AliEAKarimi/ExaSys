package Database;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


public class DBUtils {
    public static boolean isDocumentExist(MongoCollection collection, String key, String value) {
        if (collection.find(eq(key, value)).first() != null)
            return true;
        return false;
    }
}
