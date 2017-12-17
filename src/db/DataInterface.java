package db;

import calculation.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import master.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

/**
 * Created by Krzysiek on 02.12.2017.
 */
public class DataInterface {

    public Statement stmt;
    public Connection con;
    public ResultSet rs;

    public DataInterface() throws SQLException {
        SQLServerDataSource ds = Server.getServer();
        con = ds.getConnection();
        stmt = con.createStatement();
    }

    public Statement getConnection() throws SQLException {
        return stmt;
    }

    public int incrementAndGetDocumentNumber(String docName) {

        rs = null;
        int lastDocument = 0;
        try {
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
            System.out.println("TRUNCATE TABLE " + TableName);
            stmt.execute("TRUNCATE TABLE " + TableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertForecastIntoDb(Forecast f) {

        try {
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

    public void InsertReplenishmentInIntoDb (ReplenishmentIn ri) {
        try {
            String SQLquery = "INSERT INTO REPLENISHIN (locationfrom, locationto, plordnumber, date, product,  quantity) " +
                    "VALUES (" + ri.getLocationFrom() + ", " +
                    ri.getLocationTo() + ", " +
                    ri.getPlannedOrderNumber() + ", '" +
                    ri.getDate() + "', " +
                    ri.getProduct() + ", " +
                    ri.getQuantity() + ")";

            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertReplenishmentOutIntoDb (ReplenishmentOut ro) {
        try {
            String SQLquery = "INSERT INTO REPLENISHOUT (locationfrom, locationto, plorelnumber, date, product,  quantity) " +
                    "VALUES (" + ro.getLocationFrom() + ", " +
                    ro.getLocationTo() + ", " +
                    ro.getPlannedOrderNumber() + ", '" +
                    ro.getDate() + "', " +
                    ro.getProduct() + ", " +
                    ro.getQuantity() + ")";

            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteReplenishmentInFromDb (int location, int product) {
        try {
            String SQLQuery = "DELETE FROM REPLENISHIN WHERE locationto = " + location + "AND product = " + product;
            System.out.println(SQLQuery);
            stmt.executeUpdate(SQLQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DeleteReplenishmentOutFromDb (int location, int product) {
        try {
            String SQLQuery = "DELETE FROM REPLENISHOUT WHERE locationto = " + location + "AND product = " + product;
            System.out.println(SQLQuery);
            stmt.executeUpdate(SQLQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void InsertStockIntoDb (Stock s) {
        try {
            String SQLquery = "INSERT INTO STOCK (location, product, quantity) " +
                    "VALUES (" + s.getLocation() + ", " +
                    s.getProduct() + ", " +
                    s.getQuantity() + ")";

            System.out.println(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
