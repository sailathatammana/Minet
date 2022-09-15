package utils;

import java.util.Random;

public class GenerateReceiptNumber {
    public static int generateReceiptNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999999);
        return Integer.parseInt(String.format("%07d", number));
    }
}
