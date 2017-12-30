package init;

import calculation.Delivery;
import calculation.Forecast;
import calculation.Shipment;
import calculation.Stock;
import db.DataInterface;
import db.DataLoader;
import db.DateHandler;
import master.Product;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Krzysiek on 02.11.2017.
 */
public class DummyDataGenerator extends DataInterface {

    DataLoader dl;

    public DummyDataGenerator() throws SQLException {
        super();
        dl = new DataLoader();
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


    public void GenerateDummyShipments(int locationFrom, int locationTo, int product, int records) {

        List<Shipment> shipmentList = new ArrayList<>();
        for (int i = 0; i < records; i++) {
            int LocationFrom = locationFrom;
            int LocationTo = locationTo;
            int ShipmentNumber = incrementAndGetDocumentNumber("SHIPNT");
            String LoadingDate = DateHandler.getRandomDate();
            String UnloadingDate = DateHandler.getRandomDate();
            String LoadingTime = DateHandler.getRandomTime();
            String UnloadingTime = DateHandler.getRandomTime();
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
            String LoadingDate = DateHandler.getRandomDate();
            String UnloadingDate = DateHandler.getRandomDate();
            String LoadingTime = DateHandler.getRandomTime();
            String UnloadingTime = DateHandler.getRandomTime();
            int Product = product;
            int quantity = -132;
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
            String Date = DateHandler.getRandomDate();
            String ForecastDate = "20171412";
            int ForecastId = incrementAndGetDocumentNumber("INDREQ");

            Forecast forecast = new Forecast(Location, Product, Quantity, Date, ForecastDate, ForecastId);

            forecastList.add(forecast);
        }

        for (int i = 0; i < records; i++) {
            InsertForecastIntoDb(forecastList.get(i));
        }

    }

    public void GenerateDummyStocksForAllProducts() {
        truncateTable("STOCK");

        List <Integer> ProductListInteger = new ArrayList<>();
        List <Product> ProductList = new ArrayList<>();
        ProductListInteger = dl.getProductList();

        for (int i : ProductListInteger) {
            ProductList.add(dl.getProductMaster(i,2621));
        }

        for (Product p : ProductList) {
            int target = p.getTarget();
            int stock = ThreadLocalRandom.current().nextInt(0, target + 1);
            Stock s = new Stock(2621,p.getGCAS(),stock);
            InsertStockIntoDb(s);
        }
    }

}