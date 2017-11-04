package init;

import calculation.Shipment;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import db.Server;
import master.Product;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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

        // inicjalizacja zmiennych
        int LocationFrom = 0;
        int LocationTo = 0;
        int ShipmentNumber = 0;
        String LoadingDate = getRandomDate();
        String UnloadingDate = getRandomDate();
        String LoadingTime = getRandomTime();
        String UnloadingTime = getRandomTime();
        int Product = 0;
        int quantity = 0;
        String ShipParty = "Procter & Gamble";

        Shipment shipment = new Shipment(LocationFrom, LocationTo, ShipmentNumber, LoadingDate, LoadingTime,
                UnloadingDate, UnloadingTime, Product, quantity, ShipParty);


        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE SHIPMENTS"); //wyczyszczenie tabeli przed wrzuceniem danych

                String SQLquery = "INSERT INTO SHIPMENTS (locationfrom, locationto, shipntnumber, loadingdate, loadingtime, " +
                        "unloadingdate, unloadingtime, product,  quantity, shipparty) " +
                        "VALUES (" + shipment.getLocationFrom() + ", " +
                        shipment.getLocationTo() + ", " +
                        shipment.getShipmentNumber() + ", '" +
                        shipment.getLoadingDate() + "', '" +
                        shipment.getLoadingTime() + "', '" +
                        shipment.getUnloadingDate() + "', '" +
                        shipment.getUnloadingTime() + "', " +
                        shipment.getProduct() + ", " +
                        shipment.getQuantity() + ", '" +
                        shipment.getShipParty() + "')";

                System.out.println(SQLquery);
                stmt.executeUpdate(SQLquery);


            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
