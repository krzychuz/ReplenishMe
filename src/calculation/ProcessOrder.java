package calculation;

public class ProcessOrder {

    private int ProcessOrderNumber;
    private int Location;
    private int Product;
    private String StartDate;
    private String StartTime;
    private String EndDate;
    private String EndTime;
    private int Quantity;

    public ProcessOrder() {
    }

    public ProcessOrder(int processOrderNumber, int location, int product, String startDate, String startTime,
                        String endDate, String endTime, int quantity) {
        ProcessOrderNumber = processOrderNumber;
        Location = location;
        Product = product;
        StartDate = startDate;
        StartTime = startTime;
        EndDate = endDate;
        EndTime = endTime;
        Quantity = quantity;
    }

    public int getProcessOrderNumber() {
        return ProcessOrderNumber;
    }

    public void setProcessOrderNumber(int processOrderNumber) {
        ProcessOrderNumber = processOrderNumber;
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

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
