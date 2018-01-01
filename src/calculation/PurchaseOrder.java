package calculation;

/**
 * Created by Krzysiek on 30.12.2017.
 */
public class PurchaseOrder {
    private int LocationFrom = 0;
    private int LocationTo = 0;
    private int PoNumber = 0;
    private String LoadingTime = null;
    private String UnloadingTime = null;
    private String LoadingDate = null;
    private String UnloadingDate = null;
    private int Product = 0;
    private int quantity = 0;
    private String OrdParty = "";

    public PurchaseOrder(int locationFrom, int locationTo, int poNumber, String loadingDate, String loadingTime,
                         String unloadingDate, String unloadingTime, int product, int quantity, String ordParty){
        this.LocationFrom = locationFrom;
        this.LocationTo = locationTo;
        this.PoNumber = poNumber;
        this.LoadingTime = loadingTime;
        this.LoadingDate = loadingDate;
        this.UnloadingTime = unloadingTime;
        this.UnloadingDate = unloadingDate;
        this.Product = product;
        this.quantity = quantity;
        this.OrdParty = ordParty;
    }

    public PurchaseOrder() {
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

    public int getPoNumber() {
        return PoNumber;
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

    public String getOrdParty() {
        return OrdParty;
    }

    public void setLocationFrom(int locationFrom) {
        LocationFrom = locationFrom;
    }

    public void setLocationTo(int locationTo) {
        LocationTo = locationTo;
    }

    public void setPoNumber(int poNumber) {
        PoNumber = poNumber;
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
        this.quantity = quantity;
    }

    public void setOrdParty(String ordParty) {
        OrdParty = ordParty;
    }
}
