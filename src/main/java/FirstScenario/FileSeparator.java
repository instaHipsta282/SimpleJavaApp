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
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine;
            while ((firstLine = reader.readLine()) != null) {
                StringBuilder res = separate(reader, firstLine);
                saveInFile(res, resultFilePath);
                res.setLength(0);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    private static StringBuilder separate(BufferedReader reader, String firstLine) {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        result.append(firstLine).append("\n");
        String s;
        try {
            while ((s = reader.readLine()) != null && counter != fileSize) {
                counter++;
                result.append(s).append("\n");
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return result;
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
