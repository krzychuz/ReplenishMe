package db;

import calculation.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import master.Product;
import org.apache.log4j.Logger;
import simulation.GlobalParameters;

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
    final static Logger logger = Logger.getLogger(DataLoader.class);

    public DataInterface() throws SQLException {
        SQLServerDataSource ds = Server.getServer();
        con = ds.getConnection();
        stmt = con.createStatement();
    }

    public int incrementAndGetDocumentNumber(String docName) {

        rs = null;
        int lastDocument = 0;
        try {
            String SqlQuery = "SELECT docnumber FROM LASTDOC WHERE docname = '" + docName + "'";
            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);
            while (rs.next()) {
                lastDocument = Integer.parseInt(rs.getString("docnumber")) + 1;
            }
            SqlQuery = "UPDATE LASTDOC SET docnumber = '" + Integer.toString(lastDocument) +
                    "' WHERE docname = '" + docName + "'";
            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

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
            String SqlQuery = "INSERT INTO PRODUCTS(location, locationfrom, gcas, description, uom, type, procurement, " +
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

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertShipmentIntoDb(Shipment s) {

        try {
            String SqlQuery = "INSERT INTO SHIPMENTS (locationfrom, locationto, shipntnumber, loadingdate, loadingtime, " +
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

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertDeliveryIntoDb(Delivery d) {

        try {
            String SqlQuery = "INSERT INTO DELIVERIES (locationfrom, locationto, dlvnumber, loadingdate, loadingtime, " +
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

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertReplenishmentInIntoDb (ReplenishmentIn ri) {
        try {
            String SqlQuery = "INSERT INTO REPLENISHIN (locationfrom, locationto, plordnumber, date, product,  quantity) " +
                    "VALUES (" + ri.getLocationFrom() + ", " +
                    ri.getLocationTo() + ", " +
                    ri.getPlannedOrderNumber() + ", '" +
                    ri.getDate() + "', " +
                    ri.getProduct() + ", " +
                    ri.getQuantity() + ")";

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertReplenishmentOutIntoDb (ReplenishmentOut ro) {
        try {
            String SqlQuery = "INSERT INTO REPLENISHOUT (locationfrom, locationto, plorelnumber, date, product,  quantity) " +
                    "VALUES (" + ro.getLocationFrom() + ", " +
                    ro.getLocationTo() + ", " +
                    ro.getPlannedOrderNumber() + ", '" +
                    ro.getDate() + "', " +
                    ro.getProduct() + ", " +
                    ro.getQuantity() + ")";

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteReplenishmentInFromDb (int location, int product) {
        try {
            String SqlQuery = "DELETE FROM REPLENISHIN WHERE locationto = " + location + "AND product = " + product;
            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteReplenishmentOutFromDb (int location, int product) {
        try {
            String SqlQuery = "DELETE FROM REPLENISHOUT WHERE locationfrom = " + location + "AND product = " + product;
            if( GlobalParameters.LoggingLevel > 2) LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void InsertStockIntoDb (Stock s) {
        try {
            String SqlQuery = "INSERT INTO STOCK (location, product, quantity) " +
                    "VALUES (" + s.getLocation() + ", " +
                    s.getProduct() + ", " +
                    s.getQuantity() + ")";

            System.out.println(SqlQuery);
            stmt.executeUpdate(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertOrderIntoDb (Order o) {
        try {
            String SQLquery = "INSERT INTO ORDERS (ordernumber, location, product, loadingdate, loadingtime, customer, quantity) " +
                    "VALUES (" + o.getOrderNumber() + ", " +
                    o.getLocation() + ", " +
                    o.getProduct() + ", '" +
                    o.getLoadingDate() + "' , '" +
                    o.getLoadingTime() + "', '" +
                    o.getCustomer() + "', " +
                    o.getQuantity() + ")";

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LogToFile (String SqlQuery){
        if(logger.isDebugEnabled()){
            logger.debug(SqlQuery);
        }
    }


}
