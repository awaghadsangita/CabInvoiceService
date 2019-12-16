package cabinvoicegenerator;

public class InvoiceService {
    private static final double MINIMUM_NORMAL_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_PREMIUM_COST_PER_KILOMETER = 15;
    private static final int NORMAL_COST_PER_TIME = 1;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double MINIMUM_NORMAL_FARE = 5;
    private static final double MINIMUM_PREMIUM_FARE = 20;


    RideRepository rideRepository = null;

    public enum JourneyType {NORMAL, PREMIUM}

    ;
    JourneyType journeyType;

    public InvoiceService(JourneyType journeyType) {
        this.rideRepository = new RideRepository();
        this.journeyType = journeyType;
    }

    public double calculateFare(double distance, int time) throws InvoiceServiceException {
        double totalFare = 0.0;
        if (this.journeyType.equals(JourneyType.NORMAL)) {
            totalFare = distance * MINIMUM_NORMAL_COST_PER_KILOMETER + time * NORMAL_COST_PER_TIME;
            return Math.max(totalFare, MINIMUM_NORMAL_FARE);
        }
        if (this.journeyType.equals(JourneyType.PREMIUM)) {
            totalFare = distance * MINIMUM_PREMIUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
            return Math.max(totalFare, MINIMUM_PREMIUM_FARE);
        }
        throw new InvoiceServiceException(InvoiceServiceException.ExceptionType.INVALID_JOURNEYTYPE, "Invalid Journey Type");
    }

    public InvoiceSummary calculateFare(Ride[] rides) throws InvoiceServiceException {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) throws InvoiceServiceException {
        return this.calculateFare(rideRepository.getRides(userId));
    }


}
