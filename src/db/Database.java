package db;

import calculation.Forecast;
import calculation.MRPList;
import master.Product;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static List<Product> productList = new ArrayList<>();
    private static List<Forecast> forecastList = new ArrayList<>();
    private static List<MRPList> mrpLists = new ArrayList<>();

    public static void addProduct (Product p){
        productList.add(p);
    }

    public static void addForecast (Forecast f) {forecastList.add(f);}

    public static void printProducts () {
        for (Product p : productList){
            System.out.println(p.toString());
        }
    }

    public static void printForecast () {
        for (Forecast f : forecastList){
            System.out.println(f.toString());
        }
    }

    public static void generateMRPLists () {
        for (Product p : productList){
            mrpLists.add(new MRPList(p));
        }
    }

    public static void exportMRPLists () {
        for (MRPList m : mrpLists) {
            try {
                m.printMRPList();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
