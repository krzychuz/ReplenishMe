package calculation;

import init.DataLoader;
import master.TLane;

import java.util.ArrayList;
import java.util.List;

public class MRPList {

    public List<MRPElement> MRPElements;
    private DataLoader dl;
    private int product;
    private int location;

    public MRPList() {
        MRPElements = new ArrayList<>();
        dl = new DataLoader();
    }

    private List<MRPElement> calculateAvailableQuantity (List<MRPElement> list){
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

        int sourcePlant = dl.getProductMaster(product, location).getLocationFrom();
        TLane t = dl.getTLaneDetails(sourcePlant,location);
        int replenishmentLeadTime = (t.getDuration())/(24);
        calculateAvailableQuantity(MRPElements);
    }
}
