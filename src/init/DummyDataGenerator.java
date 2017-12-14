package init;

import calculation.Delivery;
import calculation.Forecast;
import calculation.Shipment;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import db.DataInterface;
import db.Server;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Krzysiek on 02.11.2017.
 */
public class DummyDataGenerator extends DataInterface {

    public DummyDataGenerator() {
    }

    public void TruncateMrpTables() {
        truncateTable("SHIPMENTS");
        truncateTable("DELIVERIES");
        truncateTable("FORECAST");
        truncateTable("ORDERS");
        truncateTable("PROCESSO");
        truncateTable("PURCHASEO");
        truncateTable("QUALITYLOT");
        truncateTable("REPLENISHIN");
        truncateTable("REPLENISHOUT");
        truncateTable("RESERVATION");
    }

    private static String getRandomDate() {
        Random r = new Random();
        int year = ThreadLocalRandom.current().nextInt(2015, 2016 + 1); // losowy rok
        int day = ThreadLocalRandom.current().nextInt(1, 364 + 1); // losowy dzień roku
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(calendar.getTime());
        s = s.replace("-", "");
        return s;
    }

    private static String getRandomTime() {
        final Random random = new Random();
        final int millisInDay = 24 * 60 * 60 * 1000;
        Time time = new Time((long) random.nextInt(millisInDay));
        return time.toString();
    }

    public void GenerateDummyShipments(int locationFrom, int locationTo, int product, int records) {

        List<Shipment> shipmentList = new ArrayList<>();
        for (int i = 0; i < records; i++) {
            int LocationFrom = locationFrom;
            int LocationTo = locationTo;
            int ShipmentNumber = incrementAndGetDocumentNumber("SHIPNT");
            String LoadingDate = getRandomDate();
            String UnloadingDate = getRandomDate();
            String LoadingTime = getRandomTime();
            String UnloadingTime = getRandomTime();
            int Product = product;
            int quantity = 140;
            String ShipParty = "Procter & Gamble";

            Shipment shipment = new Shipment(LocationFrom, LocationTo, ShipmentNumber, LoadingDate, LoadingTime,
                    UnloadingDate, UnloadingTime, Product, quantity, ShipParty);

            shipmentList.add(shipment);
        }

        for (int i = 0; i < records; i++) {
            InsertShipmentIntoDb(shipmentList.get(i));
        }

    }

    public void GenerateDummyDeliveries(int locationFrom, int locationTo, int product, int records) {

        List<Delivery> deliveriesList = new ArrayList<>();
        for (int i = 0; i < records; i++) {
            int LocationFrom = locationFrom;
            int LocationTo = locationTo;
            int DeliveryNumber = incrementAndGetDocumentNumber("DELIV");
            String LoadingDate = getRandomDate();
            String UnloadingDate = getRandomDate();
            String LoadingTime = getRandomTime();
            String UnloadingTime = getRandomTime();
            int Product = product;
            int quantity = 132;
            String DlvParty = "Procter & Gamble";

            Delivery delivery = new Delivery(LocationFrom, LocationTo, DeliveryNumber, LoadingDate, LoadingTime,
                    UnloadingDate, UnloadingTime, Product, quantity, DlvParty);

            deliveriesList.add(delivery);
        }

        for (int i = 0; i < records; i++) {
            InsertDeliveryIntoDb(deliveriesList.get(i));
        }

    }

    public void GenerateDummyForecast(int location, int product, int records) {

        List<Forecast> forecastList = new ArrayList<>();

        for (int i = 0; i < records; i++) {
            int Location = location;
            int Product = product;
            int Quantity = -500;
            String Date = getRandomDate();
            String ForecastDate = "20171412";
            int ForecastId = incrementAndGetDocumentNumber("INDREQ");

            Forecast forecast = new Forecast(Location, Product, Quantity, Date, ForecastDate, ForecastId);

            forecastList.add(forecast);
        }

        for (int i = 0; i < records; i++) {
            InsertForecastIntoDb(forecastList.get(i));
        }

    }

}
