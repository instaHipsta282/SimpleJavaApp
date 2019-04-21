package ThirdScenario;

import ServiceClasses.MyProperty;
import java.io.*;

public class AddDelimiters {
    private static final String fileName = "\\delimitResult";
    public static void delimitAndSave() {
        String delimiterType = MyProperty.getDelimiterType();
        String startFileName = MyProperty.getStartFileName();
        StringBuilder result;
        result = delimiter(startFileName, delimiterType);
        saveInFile(result);
    }
    private static StringBuilder delimiter(String startFileName, String delimiterType) {
        StringBuilder result = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(startFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line.replaceAll(",", delimiterType)).append("\n");
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        return result;
    }
    private static void saveInFile(StringBuilder result) {
        String resultFilePath = MyProperty.getResultFilePath();
        byte[] byteArray = result.toString().getBytes();

        try(OutputStream outputStream = new FileOutputStream(resultFilePath + fileName)) {
            outputStream.write(byteArray);
            outputStream.flush();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
