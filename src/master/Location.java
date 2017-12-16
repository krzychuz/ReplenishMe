package master;

import enums.Type;

public class Location {
    private int plantCode;
    private String description;
    private Type type;

    public Location (int plantCode, String description, Type type) {
        this.plantCode = plantCode;
        this.description = description;
        this.type = type;
    }

    public int getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(int plantCode) {
        this.plantCode = plantCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
