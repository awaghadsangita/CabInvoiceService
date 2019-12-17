package cabinvoicegenerator;

public class Ride {
    public final double distance;
    public final int time;
    public final RideType rideType;

    public enum RideType {PREMIUM, NORMAL}

    public Ride(double distance, Integer time, RideType rideType) throws InvoiceServiceException {
        try {
            this.distance = distance;
            this.time = time;
            this.rideType = rideType;
        } catch (NullPointerException e) {
            throw new InvoiceServiceException(InvoiceServiceException.ExceptionType.INVALID_INPUT, "Invalid Input");
        }
    }
}
