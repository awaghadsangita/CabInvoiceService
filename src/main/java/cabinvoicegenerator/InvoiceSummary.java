package cabinvoicegenerator;

public class InvoiceSummary {
    public final double totalFare;
    public final int numOfRides;
    public final double averageFare;

    public InvoiceSummary(int numOfRides, double totalFare) {
        this.numOfRides= numOfRides;
        this.totalFare = totalFare;
        this.averageFare = this.totalFare/this.numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return totalFare == that.totalFare &&
                Double.compare(that.numOfRides, numOfRides) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }
}
