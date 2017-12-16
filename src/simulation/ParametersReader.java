package simulation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ParametersReader {

    private Properties p;

    public ParametersReader (String fileName) {

        p = new Properties();

        try {
            p.load(new FileReader(fileName));
        } catch (FileNotFoundException e){
            System.out.println("Error while opening file: " + fileName);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static final String START_DATE = "start_date";

    public int getInt(String key) {
        return Integer.parseInt(p.getProperty(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(p.getProperty(key));
    }

    public String getString (String key) {
        return p.getProperty(key);
    }

}
