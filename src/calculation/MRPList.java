package calculation;

import db.DataInterface;
import init.DataLoader;
import master.Product;
import master.TLane;
import simulation.GlobalParameters;

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

    public MRPList() {
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

        int endStock = -1;

        while (endStock < 0){
            for (int i = 0; i < MRPElements.size(); i++) {
                endStock = MRPElements.get(i).getMRPElementQuantity();
                if (endStock < 0 ) {
                    int gap = safetyTarget - endStock;
                    if (gap < roundingValue) {
                        // replenishment = 1 RV
                        isMRPListChanged = true;
                    } else {
                        // replenishment
                    }
                }
                calculateAvailableQuantity(MRPElements);
            }
        }

    }
}
