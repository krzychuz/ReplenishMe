package master;

import enums.Procurement;
import enums.SafetyStrategy;
import enums.Type;
import enums.UoM;

public class Product {
    private int Location;
    private int LocationFrom;
    private int GCAS;
    private String Description;
    private UoM Unit;
    private Type Type;
    private Procurement Procurement;
    private SafetyStrategy SafetyStrategy;
    private int Target;
    private int RoundingValue;

    public Product (int location, int locationFrom, int gcas, String description, UoM unit, Type type, Procurement procurement,
                                 SafetyStrategy strategy, int target, int roundingValue){
        Location = location;
        LocationFrom = locationFrom;
        GCAS = gcas;
        Description = description;
        Unit = unit;
        Type = type;
        Procurement = procurement;
        SafetyStrategy = strategy;
        Target = target;
        RoundingValue = roundingValue;
    }

    public int getGCAS (){
        return GCAS;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        this.Location = location;
    }

    public void setGCAS(int GCAS) {
        this.GCAS = GCAS;
    }

    public int getRoundingValue() {
        return RoundingValue;
    }

    public void setRoundingValue(int roundingValue) {
        this.RoundingValue = roundingValue;
    }

    public int getTarget() {
        return Target;
    }

    public void setTarget(int target) {
        this.Target = target;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public UoM getUnit() {
        return Unit;
    }

    public void setUnit(UoM unit) {
        this.Unit = unit;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type type) {
        this.Type = type;
    }

    public Procurement getProcurement() {
        return Procurement;
    }

    public void setProcurement(Procurement procurement) {
        this.Procurement = procurement;
    }

    public SafetyStrategy getSafetyStrategy() {
        return SafetyStrategy;
    }

    public void setSafetyStrategy(SafetyStrategy safetyStrategy) {
        this.SafetyStrategy = safetyStrategy;
    }

    public int getLocationFrom() {
        return LocationFrom;
    }

    public void setLocationFrom(int locationFrom) {
        LocationFrom = locationFrom;
    }

    public boolean hasSafety() {
        boolean result = false;
        if (this.Target > 0) result = true;
        return result;
    }
}
