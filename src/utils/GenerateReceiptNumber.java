package utils;

import java.util.Random;

public class GenerateReceiptNumber {
    public static int generateReceiptNumber() {
        int receiptNumber = 0;
        Random rand = new Random();
        String number = "";
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(9);
            number += Integer.toString(n);
        }
        for (int i = 0; i < 6; i++) {
            receiptNumber = receiptNumber + number.charAt(i);
        }
        return receiptNumber;
    }
}
