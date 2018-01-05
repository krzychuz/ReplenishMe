package calculation;

import db.DataInterface;
import db.DataLoader;
import enums.SafetyStrategy;
import master.Product;
import master.TLane;
import org.apache.log4j.Logger;
import simulation.GlobalParameters;
import db.DateHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MRPList {

    public List<MRPElement> MRPElements;
    private DataLoader dl;
    private int product;
    private int location;
    private DataInterface di;
    private static Logger logger;

    public MRPList() throws SQLException {
        MRPElements = new ArrayList<>();
        dl = new DataLoader();
        di = new DataInterface();
        logger = Logger.getLogger("simlog");
    }

    private List<MRPElement> calculateAvailableQuantity(List<MRPElement> list) {
        list = dl.getMrpElementsPerProductLocation(product, location);
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

    public void runMRP() {

        if (MRPElements.size() > 2) {
            LogToFile("MRP run for product: " + product + " at plant: " + location);
            generateReplenishment();
            LogToFile("MRP run finished");
        }

    }

    private boolean IsWithinReplenishmentLeadTime (Date earliestReplenishment, Date mrpElementDate) {
        boolean result = false;
        if (earliestReplenishment.after(mrpElementDate)) result = true;
        else if (earliestReplenishment.before(mrpElementDate)) result = false;
        else if (earliestReplenishment.equals(mrpElementDate)) result = false;
        return result;
    }

    private void generateReplenishment() {

        Product p = dl.getProductMaster(product, location);
        int sourcePlant = p.getLocationFrom();
        int safetyTarget = 0;

        if (p.hasSafety() && GlobalParameters.GlobalSafetyStrategy == SafetyStrategy.SS) {
            safetyTarget = p.getTarget();
        } else if (p.hasSafety() && GlobalParameters.GlobalSafetyStrategy == SafetyStrategy.ST) {
            safetyTarget = CalculateRequirementsForSafetyTime();
            p.setTarget(safetyTarget);
            di.UpdateSafetyPerProductLocation(p);

        }

        int roundingValue = p.getRoundingValue();
        TLane t = dl.getTLaneDetails(sourcePlant, location);
        int replenishmentLeadTime;

        if (location == sourcePlant) {
            replenishmentLeadTime = 0;
        } else {
            replenishmentLeadTime = (t.getDuration()) / (24);
        }

        final Date replenishmentHorizon = DateHandler.getRelativeDate(GlobalParameters.CurrentTime, replenishmentLeadTime);

        di.DeleteReplenishmentInFromDb(location,product);
        if (location != sourcePlant) di.DeleteReplenishmentOutFromDb(sourcePlant,product);
        calculateAvailableQuantity(MRPElements);

        for (int i = 2; i < MRPElements.size(); i++) {
            int gap = safetyTarget - MRPElements.get(i).getAvailableQuantity();
            if (gap > 0) {
                if(IsWithinReplenishmentLeadTime(replenishmentHorizon,
                        DateHandler.getDateFromString(MRPElements.get(i).getDate()))) continue;
                try {
                    if (MRPElements.get(i).getDate().equals(MRPElements.get(i+1).getDate())) continue;
                } catch (IndexOutOfBoundsException e) {
                    // End of the list, do nothing
                }

                int Quantity;
                if (gap < roundingValue) {
                    Quantity = roundingValue;
                } else {
                    Quantity = (gap / roundingValue) * roundingValue + roundingValue;
                }

                int RiDocNumber = di.incrementAndGetDocumentNumber("PLORD");
                ReplenishmentIn ri = new ReplenishmentIn(sourcePlant, location, RiDocNumber,
                        MRPElements.get(i).getDate(), product, Quantity);
                di.InsertReplenishmentInIntoDb(ri);

                if (location != sourcePlant) {
                    int RoDocNumber = di.incrementAndGetDocumentNumber("PLOREL");
                    Date earliestReplenishmentOutDate = DateHandler.getRelativeDate(DateHandler.getDateFromString(MRPElements.get(i).getDate())
                            ,-replenishmentLeadTime);
                    ReplenishmentOut ro = new ReplenishmentOut(sourcePlant,location,RoDocNumber,
                            DateHandler.getStringDate(earliestReplenishmentOutDate), product, -Quantity);
                    di.InsertReplenishmentOutIntoDb(ro);
                }
                calculateAvailableQuantity(MRPElements);
            }
        }
    }

    private int CalculateRequirementsForSafetyTime () {
        List<MRPElement> RequirementsList = dl.getRequirementsPerProductLocation(product, location);
        int tmp = 0;
        int i = 0;
        Date DummyDate = GlobalParameters.CurrentTime;
        Date RequirementsHorizon = DateHandler.getRelativeDate(GlobalParameters.CurrentTime,10);

        while (DummyDate.before(RequirementsHorizon)) {
            tmp += RequirementsList.get(i).getMRPElementQuantity();
            RequirementsList.get(i).setAvailableQuantity(tmp);
            i++;
            DummyDate = DateHandler.getRelativeDate(DummyDate,1);
        }

        return Math.abs(tmp);
    }

    public void LogToFile (String SimulationStep){
        if(logger.isDebugEnabled()){
            logger.debug(SimulationStep);
        }
    }

}
