package Files;

import GUI.Utils.WritableImage;
import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.Files;

public class ImageFiles {
    public static Image getImageProfile(ImageState state, String username) {
        InputStream is;
        ObjectInputStream ois;
        try {
            File file = new File(state.getAddress() + username + ".txt");
            if (file.exists()) {
                is = new FileInputStream(file);
                ois = new ObjectInputStream(is);
                WritableImage writableImage = (WritableImage) ois.readObject();
                ois.close();
                is.close();
                return writableImage.getImage();
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean addImageProfile(ImageState state, File file, String username) {
        OutputStream os;
        ObjectOutputStream oos;
        WritableImage writableImage = new WritableImage(file);
        try {
            String fileName = username + ".txt";
            File file1 = new File(state.getAddress() + fileName);
            os = new FileOutputStream(file1);
            oos = new ObjectOutputStream(os);
            oos.writeObject(writableImage);
            oos.close();
            os.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
