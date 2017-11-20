package calculation;

import enums.MRP;
import enums.SafetyStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MRPElement {
    private String Date;
    private MRP MRPType;
    private int MRPElementData;
    private int MRPElementQuantity;
    private int AvailableQuantity;
    private int Plant;

    public MRPElement(Delivery d){
        this.Date = d.getUnloadingDate();
        this.MRPType = MRP.Deliv;
        this.MRPElementData = d.getDeliveryNumber();
        this.MRPElementQuantity = d.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = d.getLocationFrom();
    }

    public MRPElement (Forecast f) {
        this.Date = f.getDate();
        this.MRPType = MRP.IndReq;
        this.MRPElementData = f.getForecastId();
        this.MRPElementQuantity = f.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = 0;
    }

    public MRPElement (Shipment s) {
        this.Date = s.getUnloadingDate();
        this.MRPType = MRP.ShipNt;
        this.MRPElementData = s.getShipmentNumber();
        this.MRPElementQuantity = s.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = s.getLocationFrom();
    }

    public MRPElement (Stock s) {
        try {
            this.MRPElementQuantity = s.getQuantity();
            this.AvailableQuantity = s.getQuantity();
        } catch (NullPointerException e) {
            this.MRPElementQuantity = 0;
            this.AvailableQuantity = 0;
        } finally {
            LocalDate localDate = LocalDate.now();
            this.Date = DateTimeFormatter.ofPattern("uuuu-MM-dd").format(localDate);
            this.MRPType = MRP.Stock;
            this.MRPElementData = 0;
            this.Plant = 0;
        }
    }

    public MRPElement (Order o) {
        this.Date = o.getLoadingDate();
        this.MRPType= MRP.Order;
        this.MRPElementData = o.getOrderNumber();
        this.MRPElementQuantity = o.getQuantity();
        this.MRPElementQuantity = 0;
        this.Plant = 0;
    }

    public MRPElement (QualityLot qmlot) {
        this.Date = qmlot.getReleaseDate();
        this.MRPType = MRP.QMLot;
        this.MRPElementData = qmlot.getQualityLotNumber();
        this.MRPElementQuantity = qmlot.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = 0;
    }

    public MRPElement (ReplenishmentIn ri) {
        this.Date = ri.getDate();
        this.MRPType = MRP.PlOrd;
        this.MRPElementData = ri.getPlannedOrderNumber();
        this.MRPElementQuantity = ri.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = ri.getLocationFrom();
    }

    public MRPElement (ReplenishmentOut ro) {
        this.Date = ro.getDate();
        this.MRPType = MRP.PlORel;
        this.MRPElementData = ro.getPlannedOrderNumber();
        this.MRPElementQuantity = ro.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = ro.getLocationTo();
    }

    public MRPElement (Reservation r) {
        this.Date = r.getUseDate();
        this.MRPType = MRP.DepReq;
        this.MRPElementData = r.getReservationNumber();
        this.MRPElementQuantity = r.getQuantity();
        this.AvailableQuantity = 0;
        this.Plant = r.getUsage();
    }

    public MRPElement (Safety s) {
        try {
            if (s.getStrategy() == SafetyStrategy.SS) {
                this.MRPElementQuantity = s.getQuantity();
            }
        } catch (NullPointerException e) {
            this.MRPElementQuantity = 0;
        } finally {
            LocalDate localDate = LocalDate.now();
            this.Date = DateTimeFormatter.ofPattern("uuuu-MM-dd").format(localDate);
            this.MRPType = MRP.SafetyStock;
            this.MRPElementData = 0;
            this.AvailableQuantity = 0;
            this.Plant = 0;
        }
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
        return AvailableQuantity;
    }

    public int getPlant() {
        return Plant;
    }

    public void setAvailableQuantity(int availableQuantity) {
        AvailableQuantity = availableQuantity;
    }
}
