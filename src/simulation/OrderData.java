package simulation;

import enums.OrderType;

public class OrderData {

    private OrderType OrderType;
    private String Date;
    private int Location;
    private int Product;
    private int OrderNumber;
    private String Customer;
    private int Quantity;

    public OrderData(OrderType orderType, String date, int location, int product, int orderNumber,
                      String customer, int quantity) {
        OrderType = orderType;
        Date = date;
        Location = location;
        Product = product;
        OrderNumber = orderNumber;
        Customer = customer;
        Quantity = quantity;
    }

    public OrderData() {
    }

    public enums.OrderType getOrderType() {
        return OrderType;
    }

    public void setOrderType(enums.OrderType orderType) {
        OrderType = orderType;
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

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
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
