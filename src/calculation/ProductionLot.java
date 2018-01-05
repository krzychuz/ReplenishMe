package calculation;

public class ProductionLot {

    private int Location;
    private int Product;
    private int MinimumQuantity;
    private int MaximumQuantity;

    public ProductionLot (int location, int product, int minimumQuantity, int maximumQuantity) {
        Location = location;
        Product = product;
        MinimumQuantity = minimumQuantity;
        MaximumQuantity = maximumQuantity;
    }

    public ProductionLot () {}

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

    public int getMinimumQuantity() {
        return MinimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        MinimumQuantity = minimumQuantity;
    }

    public int getMaximumQuantity() {
        return MaximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        MaximumQuantity = maximumQuantity;
    }
}
