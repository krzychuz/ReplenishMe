package init;

import calculation.Forecast;
import db.DataInterface;
import enums.Procurement;
import enums.SafetyStrategy;
import enums.Type;
import enums.UoM;
import master.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Krzysiek on 02.12.2017.
 */
public class DataImporter extends DataInterface {


    private final int limit = 1000;
    private int limiter;

    public void loadForecast() {

        truncateTable("FORECAST");
        String masterDataFile = "import_data/forecast.csv";
        BufferedReader br = null;
        String line = "";
        String CsvSplitBy = ";";

        limiter = 0;

        try {

            br = new BufferedReader(new FileReader(masterDataFile));
            br.readLine(); //omijanie lini z nagłówkami
            while ((line = br.readLine()) != null) {

                limiter++;

                // wykorzystanie przecinka jako separatora
                String[] item = line.split(CsvSplitBy);

                //castowanie Stringów do odpowiednich typów
                int Location = Integer.parseInt(item[0]);
                int GCAS = Integer.parseInt(item[1]);
                String ForecastedDate = item[2];
                String Date1 = item[3];
                int Quantity = (int) Double.parseDouble(item[4].replace(",", "."));
                if (Quantity > 0) Quantity *= (-1);
                int ForecastId = incrementAndGetDocumentNumber("INDREQ");

                Forecast f = new Forecast(Location, GCAS, Quantity, Date1, ForecastedDate, ForecastId);

                InsertForecastIntoDb(f);

                if (limiter > limit) break;

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

    public void loadMaterialMaster() {

        // deklaracja zmiennych potrzebnych do wczytywania pliku CSV
        String masterDataFile = "import_data/master_data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

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
                String[] item = line.split(cvsSplitBy);

                //castowanie Stringów do odpowiednich typów
                int location = Integer.parseInt(item[0]);
                int GCAS = Integer.parseInt(item[1]);
                String description = item[2].trim();
                UoM unit = UoM.valueOf(item[3]);
                Type type = Type.valueOf(item[4]);
                Procurement procurement = Procurement.valueOf(item[5]);
                SafetyStrategy strategy = SafetyStrategy.valueOf(item[6]);
                int target = Integer.parseInt(item[7]);
                int roundingValue = Integer.parseInt(item[8]);

                //tworzenie obiektu produktu
                p = new Product(location, GCAS, description, unit, type, procurement, strategy, target, roundingValue);

                InsertProductIntoDb(p);

                if (limiter > limit ) break;
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
