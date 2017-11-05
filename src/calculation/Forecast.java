package calculation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Forecast {

    private int Location;
    private int Product;
    private int Quantity;
    private String Date;
    private String ForecastDate;
    private int ForecastId;

    public void setLocation(int location) {
        Location = location;
    }

    public void setProduct(int product) {
        Product = product;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setForecastDate(String forecastDate) {
        ForecastDate = forecastDate;
    }

    public Forecast (int location, int product, int quantity, String date, String forecastDate, int forecastId){
        this.Location = location;
        this.Product = product;
        this.Quantity = quantity;
        this.Date = date;
        this.ForecastDate= forecastDate;
        this.ForecastId = forecastId;
    }

    public int getLocation() {
        return Location;
    }

    public int getProduct() {
        return Product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getDate() {
        return Date;
    }

    public String getForecastDate() {
        return ForecastDate;
    }

    public int getForecastId() {
        return ForecastId;
    }

    public void setForecastId(int forecastId) {
        ForecastId = forecastId;
    }
}
