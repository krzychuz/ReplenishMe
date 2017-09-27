package calculation;

import java.util.HashMap;
import java.util.Map;

public class Forecast {
    private int location;
    private int GCAS;
    private Map <String, Integer> forecastMap = new HashMap<>();
    public Forecast (int _location, int _GCAS, Map _forecastMap){
        location = _location;
        GCAS = _GCAS;
        forecastMap = _forecastMap;
    }

    @Override
    public String toString(){

        String s1 = "Forecast : "
                + " Location [" + location + "]"
                + " GCAS [" + GCAS + "]";
        String s2 = "";

        for (String s: forecastMap.keySet()){
            String key =s;
            String value = forecastMap.get(s).toString();
            s2+= "[" + key + " " + value + "]";
        }

        return s1 + s2;

    }

}
