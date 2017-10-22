package master;

import enums.Procurement;
import enums.SafetyStrategy;
import enums.Type;
import enums.UoM;

public class Product {
    private int location;
    private int GCAS; //in PRODUCT
    private String description; //in PRODUCT
    private UoM unit; //in PRODUCT
    private Type type; //in PRODUCT
    private Procurement procurement;
    private SafetyStrategy strategy;
    private int target;
    private int roundingValue; //in PRODUCT

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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setGCAS(int GCAS) {
        this.GCAS = GCAS;
    }

    public int getRoundingValue() {
        return roundingValue;
    }

    public void setRoundingValue(int roundingValue) {
        this.roundingValue = roundingValue;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UoM getUnit() {
        return unit;
    }

    public void setUnit(UoM unit) {
        this.unit = unit;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Procurement getProcurement() {
        return procurement;
    }

    public void setProcurement(Procurement procurement) {
        this.procurement = procurement;
    }

    public SafetyStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SafetyStrategy strategy) {
        this.strategy = strategy;
    }
}
