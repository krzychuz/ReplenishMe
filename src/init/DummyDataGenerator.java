package init;

import calculation.Delivery;
import calculation.Forecast;
import calculation.Shipment;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import db.Server;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Krzysiek on 02.11.2017.
 */
public class DummyDataGenerator {

    private static String getRandomDate() {
        Random r = new Random();
        int year = ThreadLocalRandom.current().nextInt(2015, 2016 + 1); // losowy rok
        int day = ThreadLocalRandom.current().nextInt(1, 364 + 1); // losowy dzień roku
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(calendar.getTime());
        s = s.replace("-","");
        return s;
    }

    private static String getRandomTime() {
        final Random random = new Random();
        final int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        return time.toString();
    }

    public static void GenerateDummyShipments() {

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Shipment> shipmentList = new ArrayList<>();
        for (int i=0; i<10; i++){
            int LocationFrom = 5053;
            int LocationTo = 2751;
            int ShipmentNumber = 2856315;
            String LoadingDate = getRandomDate();
            String UnloadingDate = getRandomDate();
            String LoadingTime = getRandomTime();
            String UnloadingTime = getRandomTime();
            int Product = 83732531;
            int quantity = 140;
            String ShipParty = "Procter & Gamble";

            Shipment shipment = new Shipment(LocationFrom, LocationTo, ShipmentNumber, LoadingDate, LoadingTime,
                    UnloadingDate, UnloadingTime, Product, quantity, ShipParty);

            shipmentList.add(shipment);
        }





        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE SHIPMENTS"); //wyczyszczenie tabeli przed wrzuceniem danych

            for (int i=0; i<10; i++){
                String SQLquery = "INSERT INTO SHIPMENTS (locationfrom, locationto, shipntnumber, loadingdate, loadingtime, " +
                        "unloadingdate, unloadingtime, product,  quantity, shipparty) " +
                        "VALUES (" + shipmentList.get(i).getLocationFrom() + ", " +
                        shipmentList.get(i).getLocationTo() + ", " +
                        shipmentList.get(i).getShipmentNumber() + ", '" +
                        shipmentList.get(i).getLoadingDate() + "', '" +
                        shipmentList.get(i).getLoadingTime() + "', '" +
                        shipmentList.get(i).getUnloadingDate() + "', '" +
                        shipmentList.get(i).getUnloadingTime() + "', " +
                        shipmentList.get(i).getProduct() + ", " +
                        shipmentList.get(i).getQuantity() + ", '" +
                        shipmentList.get(i).getShipParty() + "')";

                System.out.println(SQLquery);
                stmt.executeUpdate(SQLquery);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GenerateDummyDeliveries() {

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Delivery> deliveriesList = new ArrayList<>();
        for (int i=0; i<10; i++){
            int LocationFrom = 4853;
            int LocationTo = 2751;
            int DeliveryNumber = 3784137;
            String LoadingDate = getRandomDate();
            String UnloadingDate = getRandomDate();
            String LoadingTime = getRandomTime();
            String UnloadingTime = getRandomTime();
            int Product = 83732531;
            int quantity = 132;
            String DlvParty = "Procter & Gamble";

            Delivery delivery = new Delivery(LocationFrom, LocationTo, DeliveryNumber, LoadingDate, LoadingTime,
                    UnloadingDate, UnloadingTime, Product, quantity, DlvParty);

            deliveriesList.add(delivery);
        }


        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE DELIVERIES"); //wyczyszczenie tabeli przed wrzuceniem danych

            for (int i=0; i<10; i++){
                String SQLquery = "INSERT INTO DELIVERIES (locationfrom, locationto, dlvnumber, loadingdate, loadingtime, " +
                        "unloadingdate, unloadingtime, product,  quantity, dlvparty) " +
                        "VALUES (" + deliveriesList.get(i).getLocationFrom() + ", " +
                        deliveriesList.get(i).getLocationTo() + ", " +
                        deliveriesList.get(i).getDeliveryNumber() + ", '" +
                        deliveriesList.get(i).getLoadingDate() + "', '" +
                        deliveriesList.get(i).getLoadingTime() + "', '" +
                        deliveriesList.get(i).getUnloadingDate() + "', '" +
                        deliveriesList.get(i).getUnloadingTime() + "', " +
                        deliveriesList.get(i).getProduct() + ", " +
                        deliveriesList.get(i).getQuantity() + ", '" +
                        deliveriesList.get(i).getDlvParty() + "')";

                System.out.println(SQLquery);
                stmt.executeUpdate(SQLquery);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GenerateDummyForecast() {

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Forecast> forecastList = new ArrayList<>();

        for (int i=0; i<10; i++){
            int Location = 2751;
            int Product = 83732531;
            int Quantity = 500;
            String Date = getRandomDate();
            String ForecastDate = "20170411";

            Forecast forecast = new Forecast(Location, Product, Quantity, Date, ForecastDate);

            forecastList.add(forecast);
        }

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE FORECAST"); //wyczyszczenie tabeli przed wrzuceniem danych

            for (int i=0; i<10; i++){
                String SQLquery = "INSERT INTO FORECAST (location, product, quantity, date1, fcstdate) " +
                        "VALUES (" + forecastList.get(i).getLocation() + ", " +
                        forecastList.get(i).getProduct() + ", " +
                        forecastList.get(i).getQuantity() + ", '" +
                        forecastList.get(i).getDate() + "', '" +
                        forecastList.get(i).getForecastDate() + "')";

                System.out.println(SQLquery);
                stmt.executeUpdate(SQLquery);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}