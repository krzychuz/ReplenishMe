package init;

import calculation.Forecast;
import calculation.Order;
import db.DataInterface;
import db.DateHandler;
import enums.*;
import master.Product;
import simulation.OrderData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Krzysiek on 02.12.2017.
 */
public class DataImporter extends DataInterface{


    private final int limit = 1000;
    private int limiter;
    private List<Order> ImportedOrderList;
    private List<Forecast> ImportedForecastList;
    private String CurrentForecastVersion;

    public DataImporter() throws SQLException {
        ImportedOrderList = new ArrayList<>();
        ImportedForecastList = new ArrayList<>();
    }

    public void GetFreshForecast() {
        truncateTable("FORECAST");
        try{
            CurrentForecastVersion = ImportedForecastList.get(0).getForecastDate();
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        List<Forecast> ForecastToRemove = new ArrayList<>();
        for(Forecast f : ImportedForecastList) {
            if(f.getForecastDate().equals(CurrentForecastVersion)) {
                DeleteExistingForecastFromStatistics(f);
                // By doing so only the latest forecast version will be stored in the database
                InsertForecastIntoDb(f);
                ForecastToRemove.add(f);

                OrderData od = new OrderData();
                od.setOrderType(OrderType.Forecast);
                od.setDate(f.getDate());
                od.setLocation(f.getLocation());
                od.setProduct(f.getProduct());
                od.setOrderNumber(f.getForecastId());
                od.setCustomer("All customers");
                od.setQuantity(Math.abs(f.getQuantity()));
                InsertOrderStatistic(od);
            } else {
                continue;
            }
        }
        ImportedForecastList.removeAll(ForecastToRemove);
    }

    public void loadForecast(String ForecastFilePath) {

        truncateTable("FORECAST");
        String masterDataFile = ForecastFilePath;
        BufferedReader br = null;
        String line = "";
        String CsvSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(masterDataFile));
            br.readLine();
            // Skip headers line
            while ((line = br.readLine()) != null) {
                String[] item = line.split(CsvSplitBy);

                int Location = Integer.parseInt(item[0]);
                int GCAS = Integer.parseInt(item[1]);
                String ForecastedDate = item[2];
                String Date1 = item[3];
                int Quantity = (int) Double.parseDouble(item[4].replace(",", "."));
                if (Quantity > 0) Quantity *= (-1);

                //String tmpDate = DateHandler.getRelativeDate(Date1,-1);
                String tmpDate = Date1;

                for (int i = 0; i < 7; i++) {
                    int ForecastId = incrementAndGetDocumentNumber("INDREQ");
                    Forecast f = new Forecast(Location, GCAS, Quantity/7, tmpDate, ForecastedDate, ForecastId);
                    ImportedForecastList.add(f);
                    tmpDate = DateHandler.getRelativeDate(tmpDate,-1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMaterialMaster() {

        // deklaracja zmiennych potrzebnych do wczytywania pliku CSV
        String masterDataFile = "import_data/master_data.csv";
        BufferedReader br = null;
        String line = "";
        String CsvSplitBy = ";";

        limiter = 0;

        //deklaracja lokalnie używanego produktu
        Product p;

        //wczytywanie pliksu CSV i tworzenie obiektu
        try {
            truncateTable("PRODUCTS");
            br = new BufferedReader(new FileReader(masterDataFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                limiter++;

                // wykorzystanie przecinka jako separatora
                String[] item = line.split(CsvSplitBy);

                //castowanie Stringów do odpowiednich typów
                int location = Integer.parseInt(item[0]);
                int locationfrom = Integer.parseInt(item[1]);
                int GCAS = Integer.parseInt(item[2]);
                String description = item[3].trim();
                UoM unit = UoM.valueOf(item[4]);
                Type type = Type.valueOf(item[5]);
                Procurement procurement = Procurement.valueOf(item[6]);
                SafetyStrategy strategy = SafetyStrategy.valueOf(item[7]);
                int target = Integer.parseInt(item[8]);
                int roundingValue = Integer.parseInt(item[9]);

                //tworzenie obiektu produktu
                p = new Product(location, locationfrom, GCAS, description, unit, type, procurement, strategy, target, roundingValue);

                InsertProductIntoDb(p);

                if (limiter > limit) break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadCustomerOrders(String OrderFilePath) {
        String masterDataFile = OrderFilePath;
        BufferedReader br = null;
        String line;
        String CsvSplitBy = ";";

        try {
            Order o;
            br = new BufferedReader(new FileReader(masterDataFile));
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] item = line.split(CsvSplitBy);
                int location = Integer.parseInt(item[0]);
                int product = Integer.parseInt(item[1]);
                int orderNumber = incrementAndGetDocumentNumber("ORDER");
                String loadingDate = item[2];
                String loadingTime = DateHandler.getRandomTime();
                String customer = item[3].trim();
                int quantity = (int)Double.parseDouble(item[4].replaceAll(",","."));
                if (quantity > 0) quantity *= (-1);
                o = new Order(location,product,orderNumber,loadingDate,loadingTime,customer,quantity);
                ImportedOrderList.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List <Order> GetOrdersPerLocation(int Location, int Product) {
        List <Order> OrdersPerLocation;
        OrdersPerLocation = ImportedOrderList.stream()
                .filter(item -> item.getLocation() == Location)
                .filter(item -> item.getProduct() == Product)
                .collect(Collectors.toList());
        return OrdersPerLocation;
    }

    public void RemoveInsertedOrder (Order o) {
        ImportedOrderList.remove(o);
    }
}

