package cabinvoicegenerator;

import java.util.regex.Pattern;

public class InvoiceService {
    RideRepository rideRepository = null;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, RideTypeEnum rideType){
        double totalFare = 0.0;
        totalFare = distance * rideType.MINIMUM_COST_PER_KILOMETER + time * rideType.COST_PER_TIME;
        return Math.max(totalFare,rideType.MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time, ride.rideType);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) throws InvoiceServiceException {
        try {
            Pattern.matches("^[a-zA-Z0-9.-@]{2,}", userId);
            rideRepository.addRides(userId, rides);
        } catch (NullPointerException e) {
            throw new InvoiceServiceException(InvoiceServiceException.ExceptionType.INVALID_USER, "Invalid user Id");
        }
    }

    public InvoiceSummary getInvoiceSummary(String userId){
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
