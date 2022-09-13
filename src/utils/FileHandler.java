package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static List<User> users = new ArrayList<>();
    private final static String fileName = "users.txt";

    public void writeToFile(List<User> user) {
        try {
            String lineSeparator = System.getProperty("line.separator");
            FileOutputStream file = new FileOutputStream(fileName);

            for (User user1 : user) {
                String tempUser = user1.toString();
                byte[] mybytes = tempUser.getBytes();
                file.write(mybytes);
                file.write(lineSeparator.getBytes());
            }
            file.close();
        } catch (IOException e) {
            System.out.println("File doesn't found" + e);
        }
    }

    public static List<User> readFromFile() {
        String line;

        try {
            Files.isReadable(Paths.get("data.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName)));
            while ((line = br.readLine()) != null) {
                List<String> result = List.of(line.split(","));
                users.add(new User(result.get(0), result.get(1), result.get(2), result.get(3)));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
