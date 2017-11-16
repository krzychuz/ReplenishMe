package calculation;

/**
 * Created by Krzysiek on 16.11.2017.
 */
public class Order {

    private int Location;
    private int Product;
    private int OrderNumber;
    private String LoadingDate;
    private String LoadingTime;
    private String Customer;
    private int Quantity;

    public Order(int location, int product, int orderNumber, String loadingDate, String loadingTime, String customer,
                 int quantity){
        this.Location = location;
        this.Product = product;
        this.OrderNumber = orderNumber;
        this.LoadingDate = loadingDate;
        this.LoadingTime = loadingTime;
        this.Customer = customer;
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

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getLoadingDate() {
        return LoadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        LoadingDate = loadingDate;
    }

    public String getLoadingTime() {
        return LoadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        LoadingTime = loadingTime;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
