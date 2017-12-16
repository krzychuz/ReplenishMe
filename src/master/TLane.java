package master;

public class TLane {
    private int startLocation;
    private int endLocation;
    private int duration;
    private int distance;

    public TLane(int startLocation, int endLocation, int duration, int distance ) {
        this. startLocation = startLocation;
        this.endLocation = endLocation;
        this.duration = duration;
        this.distance = distance;
    }

    public int getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(int startLocation) {
        this.startLocation = startLocation;
    }

    public int getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(int endLocation) {
        this.endLocation = endLocation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
