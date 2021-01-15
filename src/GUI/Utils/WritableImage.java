package GUI.Utils;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;

public class WritableImage implements Serializable {
    private byte[] data;

    public WritableImage(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayInputStream);
            data = byteArrayInputStream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Image getImage() {
        return new Image(new ByteArrayInputStream(data));
    }
}
