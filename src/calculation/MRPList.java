package calculation;

import master.Product;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class MRPList {
    Map<LocalDate, List> MRPElements = new HashMap<>();
    Product p;

    public MRPList(Product _p){
        LocalDate start = LocalDate.parse("2017-09-01"),
                end   = LocalDate.parse("2017-10-01");
        LocalDate next = start.minusDays(1);
        while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
            MRPElements.put(next,new ArrayList());
        }
    }

    public void printMRPList() throws FileNotFoundException{
        List <String> exportList = new ArrayList<>();
        for (LocalDate ld: MRPElements.keySet()){
            LocalDate key = ld;
            String value = MRPElements.get(ld).toString();
            String s = key + " " + value;
            exportList.add(s);
        }
        PrintWriter output = new PrintWriter(p.getGCAS() + ".txt");
        output.println(exportList);
        output.close();
    }

}
