package calculation;

import init.DataLoader;

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

        int sourcePlant = dl.getProductMaster(product).getLocationFrom();
        int replenishmentLeadTime = (dl.getTLaneDetails(sourcePlant,location).getDuration())/(24);

        calculateAvailableQuantity(MRPElements);
    }
}
