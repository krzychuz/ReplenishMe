package calculation;

import enums.MRP;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MRPElement {
    private String Date;
    private MRP MRPType;
    private int MRPElementData;
    private int MRPElementQuantity;
    private int AvailabileQuantity;
    private int Plant;

    public MRPElement(Delivery d){
        this.Date = d.getUnloadingDate();
        this.MRPType = MRP.Deliv;
        this.MRPElementData = d.getDeliveryNumber();
        this.MRPElementQuantity = d.getQuantity();
        this.AvailabileQuantity = 0;
        this.Plant = d.getLocationFrom();
    }

    public MRPElement (Forecast f) {
        this.Date = f.getDate();
        this.MRPType = MRP.IndReq;
        this.MRPElementData = f.getForecastId();
        this.MRPElementQuantity = f.getQuantity();
        this.AvailabileQuantity = 0;
        this.Plant = 0;
    }

    public MRPElement (Shipment s) {
        this.Date = s.getUnloadingDate();
        this.MRPType = MRP.ShipNt;
        this.MRPElementData = s.getShipmentNumber();
        this.MRPElementQuantity = s.getQuantity();
        this.AvailabileQuantity = 0;
        this.Plant = s.getLocationFrom();
    }

    public MRPElement (Stock s) {
        LocalDate localDate = LocalDate.now();
        this.Date = DateTimeFormatter.ofPattern("uuuu-MM-dd").format(localDate);
        this.MRPType = MRP.Stock;
        this.MRPElementData = 0;
        this.MRPElementQuantity = s.getQuantity();
        this.AvailabileQuantity = s.getQuantity();
        this.Plant = 0;
    }

    public String getDate() {
        return Date;
    }

    public MRP getMRPType() {
        return MRPType;
    }

    public int getMRPElementData() {
        return MRPElementData;
    }

    public int getMRPElementQuantity() {
        return MRPElementQuantity;
    }

    public int getAvailableQuantity() {
        return AvailabileQuantity;
    }

    public int getPlant() {
        return Plant;
    }
}
