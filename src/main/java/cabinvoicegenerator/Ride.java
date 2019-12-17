package cabinvoicegenerator;

public class Ride {
    public final double distance;
    public final int time;
    public final RideType rideType;

    public Ride(double distance, int time, RideType rideType) {
        this.distance=distance;
        this.time=time;
        this.rideType=rideType;
    }

    public enum RideType {PREMIUM,NORMAL}
}
