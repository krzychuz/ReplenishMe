package simulation;

import enums.StockType;

public class InventoryData {

    private StockType StockType;
    private String Date;
    private int Location;
    private int Product;
    private int Quantity;

    public InventoryData (StockType stockType, String date, int location, int product, int quantity) {
        StockType = stockType;
        Date = date;
        Location = location;
        Product = product;
        Quantity = quantity;
    }

    public InventoryData() {
    }

    public enums.StockType getStockType() {
        return StockType;
    }

    public void setStockType(enums.StockType stockType) {
        StockType = stockType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
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
