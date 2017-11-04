package calculation;


import java.util.Date;

/**
 * Created by Krzysiek on 02.11.2017.
 */
public class Shipment {

    private int LocationFrom;
    private int LocationTo;
    private int ShipmentNumber;
    private String LoadingTime;
    private String UnloadingTime;
    private String LoadingDate;
    private String UnloadingDate;
    private int Product;
    private int Quantity;
    private String ShipParty;

    public Shipment (int locationFrom, int locationTo, int shipmentNumber, String loadingDate, String loadingTime,
                     String unloadingDate, String unloadingTime, int product, int quantity, String shipParty){
        this.LocationFrom = locationFrom;
        this.LocationTo = locationTo;
        this.ShipmentNumber = shipmentNumber;
        this.LoadingTime = loadingTime;
        this.LoadingDate = loadingDate;
        this.UnloadingTime = unloadingTime;
        this.UnloadingDate = unloadingDate;
        this.Product = product;
        this.Quantity = quantity;
        this.ShipParty = shipParty;
    }
    public void setLocationFrom(int locationFrom) {
        LocationFrom = locationFrom;
    }

    public void setLocationTo(int locationTo) {
        LocationTo = locationTo;
    }

    public void setShipmentNumber(int shipmentNumber) {
        ShipmentNumber = shipmentNumber;
    }

    public void setLoadingTime(String loadingTime) {
        LoadingTime = loadingTime;
    }

    public void setUnloadingTime(String unloadingTime) {
        UnloadingTime = unloadingTime;
    }

    public void setLoadingDate(String loadingDate) {
        LoadingDate = loadingDate;
    }

    public void setUnloadingDate(String unloadingDate) {
        UnloadingDate = unloadingDate;
    }

    public void setProduct(int product) {
        Product = product;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public void setShipParty(String shipParty) {
        ShipParty = shipParty;
    }

    public int getLocationFrom() {
        return LocationFrom;
    }

    public int getLocationTo() {
        return LocationTo;
    }

    public String getLoadingDate() {
        return LoadingDate;
    }

    public String getUnloadingDate() {
        return UnloadingDate;
    }

    public int getShipmentNumber() {
        return ShipmentNumber;
    }

    public String getLoadingTime() {
        return LoadingTime;
    }

    public String getUnloadingTime() {
        return UnloadingTime;
    }

    public int getProduct() {
        return Product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getShipParty() {
        return ShipParty;
    }
}
