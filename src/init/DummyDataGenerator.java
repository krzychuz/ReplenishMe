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

    public static void truncateTables(){
        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE SHIPMENTS");
            stmt.execute("TRUNCATE TABLE DELIVERIES");
            stmt.execute("TRUNCATE TABLE FORECAST");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int incrementAndGetDocumentNumber(String docName){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        int lastDocument = 0;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT docnumber FROM LASTDOC WHERE docname = '"+ docName + "'");
            while (rs.next()){
                lastDocument = Integer.parseInt(rs.getString("docnumber"))+1;
            }
            stmt.executeUpdate("UPDATE LASTDOC SET docnumber = '"+ Integer.toString(lastDocument) +
                    "' WHERE docname = '"+ docName + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastDocument;
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
        s = s.replace("-","");
        return s;
    }

    private static String getRandomTime() {
        final Random random = new Random();
        final int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        return time.toString();
    }

    public static void GenerateDummyShipments(int locationFrom, int locationTo,int product, int records) {

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Shipment> shipmentList = new ArrayList<>();
        for (int i=0; i<records; i++){
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


        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();

            for (int i=0; i<records; i++){
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

    public static void GenerateDummyDeliveries(int locationFrom, int locationTo,int product, int records) {

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Delivery> deliveriesList = new ArrayList<>();
        for (int i=0; i<records; i++){
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


        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();

            for (int i=0; i<records; i++){
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

    public static void GenerateDummyForecast(int location, int product, int records) {

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        List <Forecast> forecastList = new ArrayList<>();

        for (int i=0; i<records; i++){
            int Location = location;
            int Product = product;
            int Quantity = 500;
            String Date = getRandomDate();
            String ForecastDate = "20170411";
            int ForecastId = incrementAndGetDocumentNumber("INDREQ");

            Forecast forecast = new Forecast(Location, Product, Quantity, Date, ForecastDate, ForecastId);

            forecastList.add(forecast);
        }

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();

            for (int i=0; i<records; i++){
                String SQLquery = "INSERT INTO FORECAST (location, product, quantity, date1, fcstdate, fcstid) " +
                        "VALUES (" + forecastList.get(i).getLocation() + ", " +
                        forecastList.get(i).getProduct() + ", " +
                        forecastList.get(i).getQuantity() + ", '" +
                        forecastList.get(i).getDate() + "', '" +
                        forecastList.get(i).getForecastDate() + "', " +
                        forecastList.get(i).getForecastId() + ")";

                System.out.println(SQLquery);
                stmt.executeUpdate(SQLquery);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
