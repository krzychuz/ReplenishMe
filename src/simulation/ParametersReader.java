package simulation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private void log (String key, Object output) {
        System.out.println("\nParameter: " + key + " successfully loaded with value: " + output);
    }

    public int getInt(String key) {
        int myInt = Integer.parseInt(p.getProperty(key));
        log(key,myInt);
        return myInt;
    }

    public double getDouble(String key) {
        double myDouble = Double.parseDouble(p.getProperty(key));
        log(key, myDouble);
        return myDouble;
    }

    public String getString (String key) {
        String myString = p.getProperty(key);
        log(key, myString);
        return myString;
    }

    public Date getDate (String key) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date myDate = null;

        try {
            myDate = dateFormat.parse(p.getProperty(key));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        log(key,myDate);

        return myDate;
    }

}
