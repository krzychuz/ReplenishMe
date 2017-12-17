package calculation;

import db.DataInterface;
import db.DataLoader;
import master.Product;
import master.TLane;
import simulation.GlobalParameters;

import java.sql.SQLException;
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

    private List<MRPElement> calculateAvailableQuantity(List<MRPElement> list) {
        if (isMRPListChanged) list = dl.getMrpElementsPerProductLocation(product, location);
        this.MRPElements = list;
        int tmp = 0;

        for (int i = 0; i < list.size(); i++) {
            tmp += list.get(i).getMRPElementQuantity();
            MRPElements.get(i).setAvailableQuantity(tmp);
        }

        return MRPElements;
    }

    public void setMRPList(int product, int location) {
        this.product = product;
        this.location = location;
        MRPElements = dl.getMrpElementsPerProductLocation(product, location);
    }

    public List<MRPElement> getMRPList() {
        calculateAvailableQuantity(MRPElements);
        return MRPElements;
    }

    private Date getRelativeDate (Date initDate, int relativeDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.DAY_OF_YEAR, +relativeDays);
        return calendar.getTime();
    }

    private String getStringDate (Date date) {
        return new java.sql.Date(date.getTime()).toString();
    }

    public void runMRP() {

        System.out.println("\nBeginning MRP run for product: " + product + " at plant: " + location);

        Product p = dl.getProductMaster(product, location);
        int sourcePlant = p.getLocationFrom();

        if (location == sourcePlant && MRPElements.size() > 2) {
            generateProduction();
        } else if (location != sourcePlant && MRPElements.size() > 2) {
            generateReplenishment();
        }

    }

    private void generateReplenishment() {

        Product p = dl.getProductMaster(product, location);
        int sourcePlant = p.getLocationFrom();
        int safetyTarget = p.getTarget();
        int roundingValue = p.getRoundingValue();
        TLane t = dl.getTLaneDetails(sourcePlant, location);
        int replenishmentLeadTime = (t.getDuration()) / (24);

        Date earliestReplenishmentInDate = getRelativeDate(GlobalParameters.currentDate, replenishmentLeadTime);

        di.DeleteReplenishmentInFromDb(location,product);
        di.DeleteReplenishmentOutFromDb(sourcePlant,product);
        calculateAvailableQuantity(MRPElements);

        int endStock = MRPElements.get(MRPElements.size() - 1).getAvailableQuantity();

        while (endStock < safetyTarget) {
            for (int i = 2; i < MRPElements.size(); i++) {
                int gap = safetyTarget - MRPElements.get(i).getAvailableQuantity();
                if (endStock < safetyTarget && gap > 0) {
                    int Quantity;
                    if (gap < roundingValue) {
                        Quantity = roundingValue;
                    } else {
                        Quantity = (gap / roundingValue) * roundingValue + roundingValue;
                    }
                    int RiDocNumber = di.incrementAndGetDocumentNumber("PLORD");
                    ReplenishmentIn ri = new ReplenishmentIn(sourcePlant, location, RiDocNumber,
                            getStringDate(earliestReplenishmentInDate), product, Quantity);
                    di.InsertReplenishmentInIntoDb(ri);

                    int RoDocNumber = di.incrementAndGetDocumentNumber("PLOREL");
                    Date earliestReplenishmentOutDate = getRelativeDate(earliestReplenishmentInDate,-replenishmentLeadTime);
                    ReplenishmentOut ro = new ReplenishmentOut(sourcePlant,location,RoDocNumber,
                            getStringDate(earliestReplenishmentOutDate), product, -Quantity);
                    di.InsertReplenishmentOutIntoDb(ro);

                    isMRPListChanged = true;
                    calculateAvailableQuantity(MRPElements);
                    endStock = MRPElements.get(MRPElements.size() - 1).getAvailableQuantity();
                }
                earliestReplenishmentInDate = getRelativeDate(earliestReplenishmentInDate,1);
            }
        }
    }

    private void generateProduction() {
        //TODO: production implementation
    }
}
