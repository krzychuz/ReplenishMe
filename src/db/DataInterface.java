package db;

import calculation.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import master.Product;
import org.apache.log4j.Logger;
import simulation.GlobalParameters;
import simulation.InventoryData;
import simulation.OrderData;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Krzysiek on 02.12.2017.
 */
public class DataInterface {

    public Statement stmt;
    public Connection con;
    public ResultSet rs;
    public static Logger logger;

    public DataInterface() throws SQLException {
        SQLServerDataSource ds = Server.getServer();
        con = ds.getConnection();
        stmt = con.createStatement();
        logger = Logger.getLogger("sqllog");
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
            String SqlQuery = "TRUNCATE TABLE " + TableName;
            LogToFile(SqlQuery);
            stmt.execute(SqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertForecastIntoDb(Forecast f) {

        try {
            String SqlQuery = "INSERT INTO FORECAST (location, product, quantity, date1, fcstdate, fcstid) " +
                    "VALUES (" + f.getLocation() + ", " +
                    f.getProduct() + ", " +
                    f.getQuantity() + ", '" +
                    f.getDate() + "', '" +
                    f.getForecastDate() + "', " +
                    f.getForecastId() + ")";

            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

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

    public void InsertPurchaseOrderIntoDb(PurchaseOrder p) {

        try {
            String SqlQuery = "INSERT INTO PURCHASEO (locationfrom, locationto, ponumber, loadingdate, loadingtime, " +
                    "unloadingdate, unloadingtime, product,  quantity, ordparty) " +
                    "VALUES (" + p.getLocationFrom() + ", " +
                    p.getLocationTo() + ", " +
                    p.getPoNumber() + ", '" +
                    p.getLoadingDate() + "', '" +
                    p.getLoadingTime() + "', '" +
                    p.getUnloadingDate() + "', '" +
                    p.getUnloadingTime() + "', " +
                    p.getProduct() + ", " +
                    p.getQuantity() + ", '" +
                    p.getOrdParty() + "')";

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

    public void InsertIdocRefIntoDb (int IdocNumber, int IdocReference) {
        try {
            String SqlQuery = "INSERT INTO IDOCREF (idocnum, refnum) " +
                    "VALUES (" + IdocNumber + ", " + IdocReference + ")";

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

    public void DeleteReplenishmentInFromDb (ReplenishmentIn ri) {
        int ReplenishmentInNumber = ri.getPlannedOrderNumber();
        try {
            String SqlQuery = "DELETE FROM REPLENISHIN WHERE plordnumber = " + ReplenishmentInNumber;
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

    public void DeleteProcessOrderFromDb (ProcessOrder po) {
        int ProcessOrderNumber = po.getProcessOrderNumber();
        try {
            String SqlQuery = "DELETE FROM PROCESSO WHERE prcordnumber = " + ProcessOrderNumber;
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteShipmentFromDb (Shipment s) {
        int ShipmentNumber = s.getShipmentNumber();
        try {
            String SqlQuery = "DELETE FROM SHIPMENTS WHERE shipntnumber = " + ShipmentNumber;
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeletePurchaseOrderFromDb (PurchaseOrder po) {
        int PurchaseOrderNumber = po.getPoNumber();
        try {
            String SqlQuery = "DELETE FROM PURCHASEO WHERE ponumber = " + PurchaseOrderNumber;
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteDeliveryFromDb (Delivery d) {
        int DeliveryNumber = d.getDeliveryNumber();
        try {
            String SqlQuery = "DELETE FROM DELIVERIES WHERE dlvnumber = " + DeliveryNumber;
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteQualityLotFromDb (QualityLot qmlot) {
        try {
            String SqlQuery = "DELETE FROM QUALITYLOT WHERE qmlotnumber = " + qmlot.getQualityLotNumber();
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteForecastFromDb (Forecast f) {
        try {
            String SqlQuery = "DELETE FROM FORECAST WHERE fcstid = " + f.getForecastId();
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteExistingForecastFromStatistics(Forecast f) {
        try {
            String SqlQuery = "DELETE FROM STAT_ORDERS WHERE type = 'Forecast' AND location = " + f.getLocation() +
                    " AND product = " + f.getProduct() + " AND date = '" + f.getDate() + "'";
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteOrderFromDb (Order o) {
        try {
            String SqlQuery = "DELETE FROM ORDERS WHERE ordernumber = " + o.getOrderNumber();
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void DeleteIdocReference (int IdocNumber) {
        try {
            String SqlQuery = "DELETE FROM IDOCREF WHERE idocnum = " + IdocNumber;
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void UpdateStockInDb (Stock s) {
        try {
            String SqlQuery = "UPDATE STOCK SET quantity = " + s.getQuantity() + " WHERE location = " + s.getLocation()
                    + "AND product = " + s.getProduct();
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void UpdateForecastInDb (Forecast f) {
        try {
            String SqlQuery = "UPDATE FORECAST SET quantity = " + f.getQuantity() + " WHERE fcstid = " + f.getForecastId();
            LogToFile(SqlQuery);
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

            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertOrderIntoDb (Order o) {
        try {
            String SQLquery = "INSERT INTO ORDERS (ordernumber, location, product, loadingdate, loadingtime, " +
                    "customer, quantity) VALUES (" + o.getOrderNumber() + ", " +
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

    public void InsertProcessOrderIntoDb (ProcessOrder po) {
        try {
            String SQLquery = "INSERT INTO PROCESSO (prcordnumber, location, product, startdate, starttime, " +
                    "enddate, endtime, quantity) VALUES (" +
                    po.getProcessOrderNumber() + ", " +
                    po.getLocation() + ", " +
                    po.getProduct() + ", '" +
                    po.getStartDate() + "' , '" +
                    po.getStartTime() + "', '" +
                    po.getEndDate() + "' , '" +
                    po.getEndTime() + "', " +
                    po.getQuantity() + ")";

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertQmLotIntoDb (QualityLot qmlot) {
        try {
            String SQLquery = "INSERT INTO QUALITYLOT (qmlotnumber, location, product, releasedate, releasetime, " +
                    "quantity) VALUES (" +
                    qmlot.getQualityLotNumber() + ", " +
                    qmlot.getLocation() + ", " +
                    qmlot.getProduct() + ", '" +
                    qmlot.getReleaseDate() + "' , '" +
                    qmlot.getReleaseTime() + "', " +
                    qmlot.getQuantity() + ")";

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateProcessOrderInDb (ProcessOrder po) {
        try {
            String SqlQuery = "UPDATE PROCESSO SET quantity = " + po.getQuantity() +
                    " WHERE prcordnumber = " + po.getProcessOrderNumber();
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void UpdateQmLotInDb (QualityLot qmlot) {
        try {
            String SqlQuery = "UPDATE QUALITYLOT SET quantity = " + qmlot.getQuantity() +
                    " WHERE qmlotnumber = " + qmlot.getQualityLotNumber();
            LogToFile(SqlQuery);
            stmt.executeUpdate(SqlQuery);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void TruncateStatisticTables () {
        truncateTable("STAT_ORDERS");
        truncateTable("STAT_INV");
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

    public void InsertOrderStatistic (OrderData od) {
        try {
            String SQLquery = "INSERT INTO STAT_ORDERS (type, date, location, product,  ordernumber, customer, " +
                    "quantity) VALUES ('" +
                    od.getOrderType() + "', '" +
                    od.getDate() + "', " +
                    od.getLocation() + ", " +
                    od.getProduct() + ", " +
                    od.getOrderNumber() + ", '" +
                    od.getCustomer() + "', " +
                    od.getQuantity() + ")";

            if( GlobalParameters.LoggingLevel > 2) LogToFile(SQLquery);
            stmt.executeUpdate(SQLquery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertInventoryStatistic (InventoryData id) {
        try {
            String SQLquery = "INSERT INTO STAT_INV (type, date, location, product, quantity) VALUES ('" +
                    id.getStockType() + "', '" +
                    id.getDate() + "', " +
                    id.getLocation() + ", " +
                    id.getProduct() + ", " +
                    id.getQuantity() + ")";

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
