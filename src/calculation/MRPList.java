package calculation;

import java.util.List;

public class MRPList {

    private List<MRPElement> MRPElements;

    public List<MRPElement> calculateAvailableQuantity (List<MRPElement> list){
        this.MRPElements = list;
        int tmp = 0;

        for (int i = 0; i < list.size(); i++){
            tmp += list.get(i).getMRPElementQuantity();
            MRPElements.get(i).setAvailabileQuantity(tmp);
        }

        return  MRPElements;
    }
}
