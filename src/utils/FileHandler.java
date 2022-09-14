package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler<T> {

    public void writeToFile(List<T> itemList, String fileName) {
        try {
            String lineSeparator = System.getProperty("line.separator");
            FileOutputStream file = new FileOutputStream(fileName);

            for (T item : itemList) {
                String tempUser = item.toString();
                byte[] mybytes = tempUser.getBytes();
                file.write(mybytes);
                file.write(lineSeparator.getBytes());
            }
            file.close();
        } catch (IOException e) {
            System.out.println("File doesn't found" + e);
        }
    }

    public List<List<String>> readFromFile(String fileName) {
        String line;
        List<List<String>> itemList = new ArrayList<>();
        try {
            Files.isReadable(Paths.get(fileName));
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName)));
            while ((line = br.readLine()) != null) {
                itemList.add(List.of(line.split(",")));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return itemList;
    }
}
