package FirstScenario;

import ServiceClasses.MyProperty;
import java.io.*;

public class FileSeparator {
    private static final int fileSize = 20000;
    private static int countForName = 0;
    private static final String fileName = "\\result";
    public static void separateAndSave() {
        String fileName = MyProperty.getStartFileName();
        String resultFilePath = MyProperty.getResultFilePath();
        StringBuilder result = new StringBuilder();
        int counter = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
                counter++;
                if (counter == fileSize) {
                    saveInFile(result, resultFilePath);
                    result.setLength(0);
                    counter = 0;
                }
            }
            saveInFile(result, resultFilePath);
        }
        catch (IOException e) { e.printStackTrace(); }


    }
    private static void saveInFile(StringBuilder filePart, String resultFilePath) {
        String defaultName = resultFilePath + fileName + countForName;
        byte[] byteArray;
        byteArray = filePart.toString().getBytes();
        try(OutputStream outputStream = new FileOutputStream(defaultName)) {
            outputStream.write(byteArray);
            outputStream.flush();
            countForName++;
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
