package utils;

import java.util.Random;

public class RandomGenerator {
    public static int generateRandomNumber(int length) {
        Random rnd = new Random();
        int number = rnd.nextInt(length);
        return Integer.parseInt(String.format("%07d", number));
    }
}
