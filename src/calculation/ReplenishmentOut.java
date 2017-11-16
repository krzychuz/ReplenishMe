package calculation;

/**
 * Created by Krzysiek on 16.11.2017.
 */
public class ReplenishmentOut {

    private int LocationFrom;
    private int LocationTo;
    private int PlannedOrderNumber;
    private String Date;
    private int Product;
    private int Quantity;

    public ReplenishmentOut(int locationFrom, int locationTo, int plannedOrderNumber, String date, int product,
                           int quantity){
        this.LocationFrom = locationFrom;
        this.LocationTo = locationTo;
        this.PlannedOrderNumber = plannedOrderNumber;
        this.Date = date;
        this.Product = product;
        this.Quantity = quantity;
    }

    public int getLocationFrom() {
        return LocationFrom;
    }

    public void setLocationFrom(int locationFrom) {
        LocationFrom = locationFrom;
    }

    public int getLocationTo() {
        return LocationTo;
    }

    public void setLocationTo(int locationTo) {
        LocationTo = locationTo;
    }

    public int getPlannedOrderNumber() {
        return PlannedOrderNumber;
    }

    public void setPlannedOrderNumber(int plannedOrderNumber) {
        PlannedOrderNumber = plannedOrderNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getProduct() {
        return Product;
    }

    public void setProduct(int product) {
        Product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

}
