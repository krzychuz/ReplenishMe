package calculation;

/**
 * Created by Krzysiek on 04.11.2017.
 */
public class Stock {
    private int Location;
    private int Product;
    private int Quantity;

    public Stock (int location, int product, int quantity){
        this.Location = location;
        this.Product = product;
        this.Quantity = quantity;
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
