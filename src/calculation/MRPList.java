package calculation;

import db.DataInterface;
import init.DataLoader;
import master.Product;
import master.TLane;
import simulation.GlobalParameters;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MRPList {

    public List<MRPElement> MRPElements;
    private DataLoader dl;
    private int product;
    private int location;
    private DataInterface di;
    private boolean isMRPListChanged;

    public MRPList() throws SQLException {
        MRPElements = new ArrayList<>();
        dl = new DataLoader();
        di = new DataInterface();
    }

    private List<MRPElement> calculateAvailableQuantity (List<MRPElement> list){
        if (isMRPListChanged) list = dl.getMrpElementsPerProductLocation(product,location);
        this.MRPElements = list;
        int tmp = 0;

        for (int i = 0; i < list.size(); i++){
            tmp += list.get(i).getMRPElementQuantity();
            MRPElements.get(i).setAvailableQuantity(tmp);
        }

        return  MRPElements;
    }

    public void setMRPList (int product, int location) {
        this.product = product;
        this.location = location;
        MRPElements = dl.getMrpElementsPerProductLocation(product,location);
    }

    public List<MRPElement> getMRPList () {
        calculateAvailableQuantity(MRPElements);
        return MRPElements;
    }

    public void runMRP () {

        System.out.println("\nBeginning MRP run for product: " + product + " at plant: " + location);

        Product p = dl.getProductMaster(product, location);

        int sourcePlant = p.getLocationFrom();
        int safetyTarget = p.getTarget();
        int currentStock = dl.getStockPerProductLocation(product,location).getQuantity();
        int roundingValue = p.getRoundingValue();
        TLane t = dl.getTLaneDetails(sourcePlant,location);
        int replenishmentLeadTime = (t.getDuration())/(24);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(GlobalParameters.currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, +replenishmentLeadTime);
        Date earliestReplenishmentDate = calendar.getTime();

        calculateAvailableQuantity(MRPElements);

        int endStock = MRPElements.get(MRPElements.size() - 1).getAvailableQuantity();

        while (endStock < safetyTarget){
            if (MRPElements.size() == 2) break;
            for (int i = 0; i < MRPElements.size(); i++) {
                if (endStock < safetyTarget ) {
                    int gap = safetyTarget - endStock;
                    int Quantity = 0;
                    if (gap < roundingValue) {
                        Quantity = roundingValue;
                    } else {
                        Quantity = gap/roundingValue + roundingValue;
                    }
                    int Product = product;
                    int LocationFrom = sourcePlant;
                    int LocationTo = location;
                    int PlannedOrderNumber = di.incrementAndGetDocumentNumber("PLORD");
                    java.sql.Date fecha = new java.sql.Date(earliestReplenishmentDate.getTime());
                    String Date = fecha.toString();
                    ReplenishmentIn ri = new ReplenishmentIn(LocationFrom, LocationTo, PlannedOrderNumber, Date,
                            Product, Quantity);
                    di.InsertReplenishmentInIntoDb(ri);
                    isMRPListChanged = true;
                }
                endStock = MRPElements.get(MRPElements.size()-1).getMRPElementQuantity();
                calculateAvailableQuantity(MRPElements);
            }
            endStock = MRPElements.get(MRPElements.size()-1).getMRPElementQuantity();
        }

    }
}
