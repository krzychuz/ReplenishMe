package calculation;

/**
 * Created by Krzysiek on 16.11.2017.
 */
public class Reservation {

    private int Location;
    private int ReservationNumber;
    private int Usage;
    private String UseDate;
    private String UseTime;
    private int Product;
    private int Quantity;

    public Reservation(int location, int reservationNumber, int usage, String useDate, String useTime, int product,
                       int quantity){
        this.Location = location;
        this.ReservationNumber = reservationNumber;
        this.Usage = usage;
        this.UseDate = useDate;
        this.UseTime = useTime;
        this.Product = product;
        this.Quantity = quantity;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
    }

    public int getReservationNumber() {
        return ReservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        ReservationNumber = reservationNumber;
    }

    public int getUsage() {
        return Usage;
    }

    public void setUsage(int usage) {
        Usage = usage;
    }

    public String getUseDate() {
        return UseDate;
    }

    public void setUseDate(String useDate) {
        UseDate = useDate;
    }

    public String getUseTime() {
        return UseTime;
    }

    public void setUseTime(String useTime) {
        UseTime = useTime;
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
