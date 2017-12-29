package simulation;

import org.apache.log4j.Logger;

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
    private static Logger logger;

    public ParametersReader (String fileName) {

        p = new Properties();
        logger = Logger.getLogger("iodata");

        try {
            p.load(new FileReader(fileName));
        } catch (FileNotFoundException e){
            LogToFile("Error while opening file: " + fileName);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void log (String key, Object output) {
        LogToFile("Parameter: " + key + " successfully loaded with value: " + output);
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

    public void LogToFile (String parameter){
        if(logger.isDebugEnabled()){
            logger.debug(parameter);
        }
    }


}
