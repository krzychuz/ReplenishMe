package calculation;


import java.util.Date;

/**
 * Created by Krzysiek on 02.11.2017.
 */
public class Shipment {

    private int LocationFrom = 0;
    private int LocationTo = 0;
    private int ShipmentNumber = 0;
    private String LoadingTime = null;
    private String UnloadingTime = null;
    private String LoadingDate = null;
    private String UnloadingDate = null;
    private int Product = 0;
    private int quantity = 0;
    private String ShipParty = "";

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
        this.quantity = quantity;
        this.ShipParty = shipParty;
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
        return quantity;
    }

    public String getShipParty() {
        return ShipParty;
    }
}
