package cabinvoicegenerator;

public class Ride {
    public final double distance;
    public final int time;
    public final RideTypeEnum rideType;

    public Ride(double distance, Integer time, RideTypeEnum rideType) throws InvoiceServiceException {
        try {
            this.distance = distance;
            this.time = time;
            this.rideType = rideType;
        } catch (NullPointerException e) {
            throw new InvoiceServiceException(InvoiceServiceException.ExceptionType.INVALID_INPUT, "Invalid Input");
        }
    }
}
