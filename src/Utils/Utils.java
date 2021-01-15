package Utils;

import java.util.Random;

public class Utils {
    private static Random random;
    public static int randomInteger(int min, int max) {
        random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
