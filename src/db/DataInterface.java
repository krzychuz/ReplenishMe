package db;

import calculation.Delivery;
import calculation.Forecast;
import calculation.Shipment;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import master.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Krzysiek on 02.12.2017.
 */
public class DataInterface {

    public Statement getConnection() throws SQLException {
        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        SQLServerDataSource ds = Server.getServer();
        con = ds.getConnection();
        stmt = con.createStatement();

        return stmt;
    }

    public int incrementAndGetDocumentNumber(String docName) {

        ResultSet rs = null;
        int lastDocument = 0;
        try {
            Statement stmt = getConnection();
            rs = stmt.executeQuery("SELECT docnumber FROM LASTDOC WHERE docname = '" + docName + "'");
            while (rs.next()) {
                lastDocument = Integer.parseInt(rs.getString("docnumber")) + 1;
            }
            stmt.executeUpdate("UPDATE LASTDOC SET docnumber = '" + Integer.toString(lastDocument) +
                    "' WHERE docname = '" + docName + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastDocument;
    }

    public void truncateTable(String TableName) {

        try {
            Statement stmt = getConnection();
            System.out.println("TRUNCATE TABLE " + TableName);
            stmt.execute("TRUNCATE TABLE " + TableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertForecastIntoDb(Forecast f) {

        try {
            Statement stmt = getConnection();

            String SQLquery = "INSERT INTO FORECAST (location, product, quantity, date1, fcstdate, fcstid) " +
                    "VALUES (" + f.getLocation() + ", " +
                    f.getProduct() + ", " +
                    f.getQuantity() + ", '" +
                    f.getDate() + "', '" +
                    f.getForecastDate() + "', " +
                    f.getForecastId() + ")";

            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertProductIntoDb(Product p) {

        try {
            Statement stmt = getConnection();

            String SQLquery = "INSERT INTO PRODUCTS(location, locationfrom, gcas, description, uom, type, procurement, " +
                    "safetystrategy, target, roundval) VALUES (" +
                    p.getLocation() + ", " +
                    p.getLocationFrom() + ", " +
                    p.getGCAS() + ", '" +
                    p.getDescription() + "', '" +
                    p.getUnit() + "', '" +
                    p.getType() + "', '" +
                    p.getProcurement() + "', '" +
                    p.getSafetyStrategy() + "', " +
                    p.getTarget() + ", " +
                    p.getRoundingValue() + ")";
            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertShipmentIntoDb(Shipment s) {

        try {
            Statement stmt = getConnection();

            String SQLquery = "INSERT INTO SHIPMENTS (locationfrom, locationto, shipntnumber, loadingdate, loadingtime, " +
                    "unloadingdate, unloadingtime, product,  quantity, shipparty) " +
                    "VALUES (" + s.getLocationFrom() + ", " +
                    s.getLocationTo() + ", " +
                    s.getShipmentNumber() + ", '" +
                    s.getLoadingDate() + "', '" +
                    s.getLoadingTime() + "', '" +
                    s.getUnloadingDate() + "', '" +
                    s.getUnloadingTime() + "', " +
                    s.getProduct() + ", " +
                    s.getQuantity() + ", '" +
                    s.getShipParty() + "')";

            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertDeliveryIntoDb(Delivery d) {

        try {
            Statement stmt = getConnection();

            String SQLquery = "INSERT INTO DELIVERIES (locationfrom, locationto, dlvnumber, loadingdate, loadingtime, " +
                    "unloadingdate, unloadingtime, product,  quantity, dlvparty) " +
                    "VALUES (" + d.getLocationFrom() + ", " +
                    d.getLocationTo() + ", " +
                    d.getDeliveryNumber() + ", '" +
                    d.getLoadingDate() + "', '" +
                    d.getLoadingTime() + "', '" +
                    d.getUnloadingDate() + "', '" +
                    d.getUnloadingTime() + "', " +
                    d.getProduct() + ", " +
                    d.getQuantity() + ", '" +
                    d.getDlvParty() + "')";

            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
