package ThirdScenario;

import ServiceClasses.MyProperty;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDelimiters {
    private static String delimiterType;
    private static final String fileName = "\\delimitResult";
    private static final int stringBuilderSize = 20000;
    public static void delimitAndSave() {
        delimiterType = MyProperty.getDelimiterType();
        String startFileName = MyProperty.getStartFileName();
        StringBuilder result;
        result = delimiter(startFileName);
        saveInFile(result);
    }
    private static StringBuilder delimiter(String startFileName) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        try(BufferedReader reader = new BufferedReader(new FileReader(startFileName), 4096)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\t")) ifTExist(result, line);
                else ifTNotExist(result, line);
                count++;
                if (count == stringBuilderSize) {
                    saveInFile(result);
                    result.setLength(0);
                    count = 1;
                }
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        return result;
    }
    private static void ifTExist(StringBuilder result, String line) {
        Pattern p = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4})" +
                "\\s(\\d{2}:\\d{2}:\\d{2}\\.\\d+\\b)" +
                "\\s(TRACE:|WARN :|INFO :|DEBUG:)" +
                "\\s(\\d\\.\\d\\.\\d{2}\\.\\d{2}:\\d{1,10}|[a-zA-Z]+)" +
                "(.*)");
        Matcher m = p.matcher(line);
        while (m.find()) result.append(m.group(1))
                .append(delimiterType)
                .append(m.group(2))
                .append(delimiterType)
                .append(m.group(3))
                .append(delimiterType)
                .append(m.group(4))
                .append(delimiterType)
                .append(m.group(5).trim())
                .append("\n");
    }
    private static void ifTNotExist(StringBuilder result, String line) {
        Pattern p = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4})" +
                "\\s(\\d{2}:\\d{2}:\\d{2}\\.\\d+\\b)" +
                "\\s(TRACE:|WARN :|INFO :|DEBUG:)" +
                "(.*)"
        );
        Matcher m = p.matcher(line);
        while (m.find()) result.append(m.group(1))
                .append(delimiterType)
                .append(m.group(2))
                .append(delimiterType)
                .append(m.group(3))
                .append(delimiterType)
                .append(m.group(4).trim())
                .append("\n");

    }
    private static void saveInFile(StringBuilder result) {
        String resultFilePath = MyProperty.getResultFilePath();
        byte[] byteArray = result.toString().getBytes();

        try(OutputStream outputStream = new FileOutputStream(resultFilePath + fileName,true)) {
            outputStream.write(byteArray);
            outputStream.flush();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
