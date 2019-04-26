package SecondScenario;

import ServiceClasses.MyProperty;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSearch {
    private static final String fileName = "\\regExpResult";
    public static void begin() {
        StringBuilder file = readLogFile();
        StringBuilder result = logParse(file);
        writeLog(result);
    }
    private static StringBuilder readLogFile() {
        String fileName = MyProperty.getStartFileName();
        StringBuilder file = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                file.append(line).append("\n");
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        return file;
    }
    private static StringBuilder logParse(StringBuilder file) {
        String regEx = MyProperty.getRegEx() + ".*";
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(file);

        while (m.find()) {
            result.append(m.group()).append("\n");
        }
        return result;
    }
    private static void writeLog(StringBuilder result) {
        String resultFilePath = MyProperty.getResultFilePath();
        byte[] byteArray = result.toString().getBytes();

        try(OutputStream outputStream = new FileOutputStream(resultFilePath + fileName)) {
            outputStream.write(byteArray);
            outputStream.flush();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
