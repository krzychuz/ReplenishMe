package init;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import calculation.Forecast;
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
            stmt.execute("TRUNCATE TABLE PRODUCT"); //wyczyszczenie tabeli przed importen danych
            
            for (Product product : productList) {
                String SQLquery = "INSERT INTO PRODUCT(gcas, description, uom, type, roundval) " +
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

    public static void loadForecast(){

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
    }
}
