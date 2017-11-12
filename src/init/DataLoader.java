package init;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.sql.*;

import calculation.*;
import com.microsoft.sqlserver.jdbc.*;
import db.Server;
import enums.Procurement;
import enums.SafetyStrategy;
import enums.Type;
import enums.UoM;
import master.Product;

public class DataLoader {
    public static void loadMaterialMaster(){

        // deklaracja zmiennych potrzebnych do wczytywania pliku CSV
        String masterDataFile = "master_data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        // deklaracja zmiennych potrzebnych do obsługi JDBC
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        //deklaracja logalnie używanego produktu
        Product p = null;
        List<Product> productList = new ArrayList<>();

        //wczytywanie pliksu CSV i tworzenie obiektu
        try {

            br = new BufferedReader(new FileReader(masterDataFile));
            while ((line = br.readLine()) != null) {

                // wykorzystanie przecinka jako separatora
                String[] item = line.split(cvsSplitBy);

                //castowanie Stringów do odpowiednich typów
                int location = Integer.parseInt(item[0]);
                int GCAS = Integer.parseInt(item[1]);
                String description = item [2];
                UoM unit = UoM.valueOf(item[3]);
                Type type = Type.valueOf(item[4]);
                Procurement procurement = Procurement.valueOf(item[5]);
                SafetyStrategy strategy = SafetyStrategy.valueOf(item[6]);
                int target = Integer.parseInt(item[7]);
                int roundingValue = Integer.parseInt(item[8]);

                //tworzenie obiektu produktu
                p = new Product (location, GCAS, description, unit, type, procurement, strategy, target, roundingValue);
                productList.add(p);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // zapisywanie stworzonego obiektu do bazy danych
        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE PRODUCTS"); //wyczyszczenie tabeli przed importen danych
            
            for (Product product : productList) {
                String SQLquery = "INSERT INTO PRODUCTS(gcas, description, uom, type, roundval) " +
                        "VALUES (" + product.getGCAS() + ", '" + product.getDescription() + "', '" + product.getUnit() + "', '" +
                        product.getType() + "', " + product.getRoundingValue() + ")";
                System.out.println(SQLquery);
                stmt.executeUpdate(SQLquery);
            }

            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getMaterialMaster(){

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM PRODUCTS");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public List<Integer> getProductList() {
        List<Integer> productList = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT gcas FROM PRODUCTS");

            while (rs.next()) {
                productList.add(rs.getInt("gcas"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<Integer> getPlantList() {
        List<Integer> plantList = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT plantcode FROM LOCATIONS");

            while (rs.next()) {
                plantList.add(rs.getInt("plantcode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantList;
    }

    public ResultSet getMrpList(int product, int location){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
            String SqlQuery = "SELECT loadingdate, dlvnumber, quantity " +
            "FROM DELIVERIES " +
            "WHERE locationto = " + location + " AND product = " + product + " " +
            "UNION " +
            "SELECT date1, fcstid, quantity " +
            "FROM FORECAST " +
            "WHERE location = "+ location + " AND product = " + product + " " +
            "UNION " +
            "SELECT unloadingdate, shipntnumber, quantity " +
            "FROM SHIPMENTS " +
            "WHERE locationto =" + location + " AND product = " + product + " " +
            "ORDER BY loadingdate";
            System.out.println(SqlQuery);
            rs = stmt.executeQuery(SqlQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;

    }

    public List<Forecast> getForecastsPerProductLocation(int product, int location){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List <Forecast> forecastList = new ArrayList<>();

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
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
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List <Shipment> shipmentList = new ArrayList<>();

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
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
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List <Delivery> deliveryList = new ArrayList<>();

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
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
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Stock stock = null;

        try {
            SQLServerDataSource ds = Server.getServer();
            con = ds.getConnection();
            stmt = con.createStatement();
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

    public List<MRPElement> getMrpElementsPerProductLocation(int product, int location){
        List<MRPElement> MrpElementsList = new ArrayList<>();
        List<Forecast> ForecastList;
        List<Shipment> ShipmentsList;
        List<Delivery> DeliveryList;
        Stock Stock;

        ForecastList = getForecastsPerProductLocation(product,location);
        ShipmentsList = getShipmentPerProductLocation(product,location);
        DeliveryList = getDeliveryPerProductLocation(product,location);
        Stock = getStockPerProductLocation(product,location);

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

        Collections.sort(MrpElementsList, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        {
            MRPElement e = new MRPElement(Stock);
            MrpElementsList.add(0,e);
        }

        return MrpElementsList;
    }

    /*public static void loadForecast(){

        String masterDataFile = "forecast.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(masterDataFile));
            while ((line = br.readLine()) != null) {

                // wykorzystanie przecinka jako separatora
                String[] item = line.split(cvsSplitBy);

                //castowanie Stringów do odpowiednich typów
                int location = Integer.parseInt(item[0]);
                int GCAS = Integer.parseInt(item[1]);

                //tworzenie mapy forecastu
                Map<String, Integer> forecastMap = new HashMap<>();
                for (int i=1; i<53; i++){
                    forecastMap.put("W"+i,Integer.parseInt(item[i+1]));
                }

                Forecast f = new Forecast(location,GCAS,forecastMap);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
