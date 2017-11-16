package calculation;

import enums.SafetyStrategy;

/**
 * Created by Krzysiek on 16.11.2017.
 */
public class Safety {

    private int Location;
    private int Product;
    private SafetyStrategy Strategy;
    private int Quantity;

    public Safety(int location, int product, SafetyStrategy strategy, int quantity){
        this.Location = location;
        this.Product = product;
        this.Strategy = strategy;
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

    public SafetyStrategy getStrategy() {
        return Strategy;
    }

    public void setStrategy(SafetyStrategy strategy) {
        Strategy = strategy;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
