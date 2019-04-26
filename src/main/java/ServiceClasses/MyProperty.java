package ServiceClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperty {
    private static final String confName = ".\\config.properties";
    private static String startFileName;
    private static String resultFilePath;
    private static String regEx;
    private static String delimiterType;

    public static void initF() {
        //noinspection unused
        Properties properties = commonProperties();
    }
    public static void initS() {
        Properties properties = commonProperties();
        regEx = properties.getProperty("regEx");
    }
    public static void initT() {
        Properties properties = commonProperties();
        delimiterType = properties.getProperty("delimiterType");
    }

    private static void load(Properties prop) {
        try(InputStream inputStream = new FileInputStream(confName)) {
            prop.load(inputStream);
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    private static Properties commonProperties() {
        Properties properties = new Properties();
        load(properties);
        startFileName = properties.getProperty("startFileName");
        resultFilePath = properties.getProperty("resultFilePath");
        return properties;
    }

    public static String getStartFileName() {
        return startFileName;
    }

    public static String getResultFilePath() {
        return resultFilePath;
    }

    public static String getRegEx() {
        return regEx;
    }

    public static String getDelimiterType() {
        return delimiterType;
    }
}
