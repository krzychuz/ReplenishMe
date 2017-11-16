package calculation;

/**
 * Created by Krzysiek on 16.11.2017.
 */
public class QualityLot {

    private int Location;
    private int QualityLotNumber;
    private String ReleaseDate;
    private String ReleaseTime;
    private int Product;
    private int Quantity;

    public QualityLot(int location, int qualityLotNumber, String releaseDate, String releaseTime, int product,
                      int quantity){
        this.Location = location;
        this.QualityLotNumber = qualityLotNumber;
        this.ReleaseDate = releaseDate;
        this.ReleaseTime = releaseTime;
        this.Product = product;
        this.Quantity = quantity;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
    }

    public int getQualityLotNumber() {
        return QualityLotNumber;
    }

    public void setQualityLotNumber(int qualityLotNumber) {
        QualityLotNumber = qualityLotNumber;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        ReleaseTime = releaseTime;
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
