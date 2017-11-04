package calculation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Forecast {

    private int Location = 0;
    private int Product = 0;
    private int Quantity = 0;
    private String Date = null;
    private String ForecastDate = null;

    public Forecast (int location, int product, int quantity, String date, String forecastDate){
        this.Location = location;
        this.Product = product;
        this.Quantity = quantity;
        this.Date = date;
        this.ForecastDate= forecastDate;
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
}
