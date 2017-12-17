package db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.sql.*;

import calculation.*;
import com.microsoft.sqlserver.jdbc.*;
import db.DataInterface;
import db.Server;
import enums.Procurement;
import enums.SafetyStrategy;
import enums.Type;
import enums.UoM;
import master.Product;
import master.TLane;

public class DataLoader extends DataInterface{

    public DataLoader() throws SQLException{
    }

    public ResultSet getMaterialMaster() {

        try {
            rs = stmt.executeQuery("SELECT * FROM PRODUCTS");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public List<Integer> getProductList() {

        List<Integer> productList = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT DISTINCT gcas FROM PRODUCTS ORDER BY gcas");
            while (rs.next()) {
                productList.add(rs.getInt("gcas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    public Product getProductMaster(int product, int location) {

        Product p = null;

        try {
            rs = stmt.executeQuery("SELECT * FROM PRODUCTS WHERE gcas='" + product
                    + "' AND location ='" + location + "'");

            while (rs.next()) {
                p = new Product(0,0,0,"",null,null,
                        null,null,0,0);
                p.setGCAS(rs.getInt("gcas"));
                p.setLocationFrom(rs.getInt("locationfrom"));
                p.setLocation(rs.getInt("location"));
                p.setDescription(rs.getString("description"));
                p.setUnit(UoM.valueOf(rs.getString("uom").trim()));
                p.setType(Type.valueOf(rs.getString("type").trim()));
                p.setProcurement(Procurement.valueOf(rs.getString("procurement").trim()));
                p.setSafetyStrategy(SafetyStrategy.valueOf(rs.getString("safetystrategy").trim()));
                p.setTarget(rs.getInt("target"));
                p.setRoundingValue(rs.getInt("roundval"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    public TLane getTLaneDetails(int startloc, int endloc){

        TLane t = null;

        try {
            rs = stmt.executeQuery("SELECT * FROM TLANES WHERE startloc='" + startloc +
                    "' AND endloc = '" + endloc + "'");

            while (rs.next()) {
                t = new TLane(0,0,0,0);
                t.setStartLocation(rs.getInt("startloc"));
                t.setEndLocation(rs.getInt("endloc"));
                t.setDuration(rs.getInt("duration"));
                t.setDistance(rs.getInt("distance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    public List<Integer> getPlantList() {

        List<Integer> plantList = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT plantcode FROM LOCATIONS");

            while (rs.next()) {
                plantList.add(rs.getInt("plantcode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantList;
    }

    public List<Forecast> getForecastsPerProductLocation(int product, int location){

        List <Forecast> forecastList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM FORECAST WHERE product = " + product + " AND location = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()) {
                Forecast f = new Forecast(0,0,0,"","",0);
                f.setLocation(rs.getInt("location"));
                f.setProduct(rs.getInt("product"));
                f.setQuantity(rs.getInt("quantity"));
                f.setDate(rs.getString("date1"));
                f.setForecastDate(rs.getString("fcstdate"));
                f.setForecastId(rs.getInt("fcstid"));
                forecastList.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return forecastList;
    }

    public List<Shipment> getShipmentPerProductLocation(int product, int location){

        List <Shipment> shipmentList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM SHIPMENTS WHERE product = " + product + " AND locationto = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()) {
                Shipment s = new Shipment(0,0,0,"","","","",0,0,"");
                s.setLocationFrom(rs.getInt("locationfrom"));
                s.setLocationTo(rs.getInt("locationto"));
                s.setShipmentNumber(rs.getInt("shipntnumber"));
                s.setLoadingDate(rs.getString("loadingdate"));
                s.setLoadingTime(rs.getString("loadingtime"));
                s.setUnloadingDate(rs.getString("unloadingdate"));
                s.setUnloadingTime(rs.getString("unloadingtime"));
                s.setProduct(rs.getInt("product"));
                s.setQuantity(rs.getInt("quantity"));
                s.setShipParty(rs.getString("shipparty"));
                shipmentList.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shipmentList;
    }

    public List<Delivery> getDeliveryPerProductLocation(int product, int location){

        ResultSet rs = null;
        List <Delivery> deliveryList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM DELIVERIES WHERE product = " + product + " AND locationto = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()) {
                Delivery d = new Delivery(0,0,0,"","","","",0,0,"");
                d.setLocationFrom(rs.getInt("locationfrom"));
                d.setLocationTo(rs.getInt("locationto"));
                d.setDeliveryNumber(rs.getInt("dlvnumber"));
                d.setLoadingDate(rs.getString("loadingdate"));
                d.setLoadingTime(rs.getString("loadingtime"));
                d.setUnloadingDate(rs.getString("unloadingdate"));
                d.setUnloadingTime(rs.getString("unloadingtime"));
                d.setProduct(rs.getInt("product"));
                d.setQuantity(rs.getInt("quantity"));
                d.setDlvParty(rs.getString("dlvparty"));
                deliveryList.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return deliveryList;
    }

    public Stock getStockPerProductLocation(int product, int location){

        Stock stock = null;

        try {
            String SqlQuery = "SELECT * FROM STOCK WHERE product = " + product + " AND location = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                stock = new Stock(0,0,0);
                stock.setProduct(rs.getInt("product"));
                stock.setQuantity(rs.getInt("quantity"));
                stock.setLocation(rs.getInt("location"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }

    public Safety getSafetyPerProductLocation(int product, int location){

        Safety safety = null;

        try {
            String SqlQuery = "SELECT * FROM SAFETIES WHERE product = " + product + " AND location = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                safety = new Safety(0,0,SafetyStrategy.SS,0);
                safety.setLocation(rs.getInt("location"));
                safety.setProduct(rs.getInt("product"));
                safety.setQuantity(rs.getInt("quantity"));
                String s = rs.getString("strategy").trim();
                SafetyStrategy strategy = SafetyStrategy.valueOf(s);
                safety.setStrategy(strategy);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return safety;
    }

    public List<Order> getOrderPerProductLocation(int product, int location){

        List <Order> orderList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM ORDERS WHERE product = " + product + " AND location = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                Order o = new Order(0,0,0,"","","",0);
                o.setLocation(rs.getInt("location"));
                o.setProduct(rs.getInt("product"));
                o.setOrderNumber(rs.getInt("ordernumber"));
                o.setLoadingDate(rs.getString("loadingdate"));
                o.setLoadingTime(rs.getString("loadingtime"));
                o.setCustomer(rs.getString("customer"));
                o.setQuantity(rs.getInt("quantity"));
                orderList.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public List<QualityLot> getQualityLotPerProductLocation(int product, int location){

        List <QualityLot> qualityLotList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM QUALITYLOT WHERE product = " + product + " AND location = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                QualityLot qmlot = new QualityLot(0,0,"","",0,0);
                qmlot.setLocation(rs.getInt("location"));
                qmlot.setProduct(rs.getInt("product"));
                qmlot.setQualityLotNumber(rs.getInt("qmlotnumber"));
                qmlot.setReleaseDate(rs.getString("releasedate"));
                qmlot.setReleaseTime(rs.getString("releasetime"));
                qmlot.setQuantity(rs.getInt("quantity"));
                qualityLotList.add(qmlot);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return qualityLotList;
    }

    public List<ReplenishmentIn> getReplenishmentInPerProductLocation(int product, int location){

        List<ReplenishmentIn> replenishmentInList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM REPLENISHIN WHERE product = " + product + " AND locationto = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                ReplenishmentIn ri = new ReplenishmentIn(0,0,0,"",0,0);
                ri.setLocationFrom(rs.getInt("locationfrom"));
                ri.setLocationTo(rs.getInt("locationto"));
                ri.setPlannedOrderNumber(rs.getInt("plordnumber"));
                ri.setDate(rs.getString("date"));
                ri.setProduct(rs.getInt("product"));
                ri.setQuantity(rs.getInt("quantity"));
                replenishmentInList.add(ri);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return replenishmentInList;
    }

    public List<ReplenishmentOut> getReplenishmentOutPerProductLocation(int product, int location){

        List<ReplenishmentOut> replenishmentOutList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM REPLENISHOUT WHERE product = " + product + " AND locationfrom = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                ReplenishmentOut ro = new ReplenishmentOut(0,0,0,"",0,0);
                ro.setLocationFrom(rs.getInt("locationfrom"));
                ro.setLocationTo(rs.getInt("locationto"));
                ro.setPlannedOrderNumber(rs.getInt("plorelnumber"));
                ro.setDate(rs.getString("date"));
                ro.setProduct(rs.getInt("product"));
                ro.setQuantity(rs.getInt("quantity"));
                replenishmentOutList.add(ro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return replenishmentOutList;
    }

    public List<Reservation> getReservationPerProductLocation(int product, int location){

        List<Reservation> reservationList = new ArrayList<>();

        try {
            String SqlQuery = "SELECT * FROM RESERVATION WHERE product = " + product + " AND location = " + location;
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

            while (rs.next()){
                Reservation r = new Reservation(0,0,0,"","",0,0);
                r.setLocation(rs.getInt("location"));
                r.setReservationNumber(rs.getInt("depreqnumber"));
                r.setUsage(rs.getInt("usage"));
                r.setUseDate(rs.getString("usedate"));
                r.setUseTime(rs.getString("usetime"));
                r.setProduct(rs.getInt("product"));
                r.setQuantity(rs.getInt("quantity"));
                reservationList.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservationList;
    }

    public List<MRPElement> getMrpElementsPerProductLocation(int product, int location){
        List<MRPElement> MrpElementsList = new ArrayList<>();
        List<Forecast> ForecastList;
        List<Shipment> ShipmentsList;
        List<Delivery> DeliveryList;
        List<Order> OrderList;
        List<QualityLot> QualityLotList;
        List<ReplenishmentIn> ReplenishmentInList;
        List<ReplenishmentOut> ReplenishmentOutList;
        List<Reservation> ReservationList;
        Stock Stock;
        Safety Safety;

        ForecastList = getForecastsPerProductLocation(product,location);
        ShipmentsList = getShipmentPerProductLocation(product,location);
        DeliveryList = getDeliveryPerProductLocation(product,location);
        OrderList = getOrderPerProductLocation(product,location);
        QualityLotList = getQualityLotPerProductLocation(product,location);
        ReplenishmentInList = getReplenishmentInPerProductLocation(product,location);
        ReplenishmentOutList = getReplenishmentOutPerProductLocation(product,location);
        ReservationList = getReservationPerProductLocation(product,location);
        Stock = getStockPerProductLocation(product,location);
        Safety = getSafetyPerProductLocation(product,location);

        for(Forecast f : ForecastList){
            MRPElement e = new MRPElement(f);
            MrpElementsList.add(e);
        }

        for(Shipment s : ShipmentsList){
            MRPElement e = new MRPElement(s);
            MrpElementsList.add(e);
        }

        for(Delivery d : DeliveryList){
            MRPElement e = new MRPElement(d);
            MrpElementsList.add(e);
        }

        for(Order o : OrderList){
            MRPElement e = new MRPElement(o);
            MrpElementsList.add(e);
        }

        for (QualityLot q : QualityLotList){
            MRPElement e = new MRPElement(q);
            MrpElementsList.add(e);
        }

        for (ReplenishmentIn ri : ReplenishmentInList){
            MRPElement e = new MRPElement(ri);
            MrpElementsList.add(e);
        }

        for (ReplenishmentOut ro : ReplenishmentOutList){
            MRPElement e = new MRPElement(ro);
            MrpElementsList.add(e);
        }

        for (Reservation r : ReservationList){
            MRPElement e = new MRPElement(r);
            MrpElementsList.add(e);
        }

        Collections.sort(MrpElementsList, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        {
            MRPElement e = new MRPElement(Stock);
            MrpElementsList.add(0,e);
            e = new MRPElement(Safety);
            MrpElementsList.add(1,e);
        }

        return MrpElementsList;
    }

}
