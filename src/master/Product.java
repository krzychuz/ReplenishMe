package master;

import enums.Procurement;
import enums.SafetyStrategy;
import enums.Type;
import enums.UoM;

public class Product {
    private int location;
    private int GCAS;
    private String description;
    private UoM unit;
    private Type type;
    private Procurement procurement;
    private SafetyStrategy strategy;
    private int target;
    private int roundingValue;

    public Product (int _location, int _gcas, String _description, UoM _unit, Type _type, Procurement _procurement,
                                 SafetyStrategy _strategy, int _target, int _roundingValue){
        location = _location;
        GCAS = _gcas;
        description = _description;
        unit = _unit;
        type = _type;
        procurement = _procurement;
        strategy = _strategy;
        target = _target;
        roundingValue = _roundingValue;
    }

    @Override
    public String toString(){

        String s = "Product : "
                + " Location [" + location + "]"
                + " GCAS [" + GCAS + "]"
                + " Description [" + description + "]"
                + " UoM [" + unit + "]"
                + " Type [" + type + "]"
                + " Procurement [" + procurement + "]"
                + " Safety Strategy [" + strategy + "]"
                + " Safety Target [" + target + "]"
                + " Rounding Value [" + roundingValue + "]";
        return s;

    }

    public int getGCAS (){
        return GCAS;
    }
}
