package cabinvoicegenerator;

public enum RideTypeEnum {

    NORMAL(10,1,5),
    PREMIUM(15,2,20);

    public double MINIMUM_COST_PER_KILOMETER ;
    public int COST_PER_TIME;
    public double MINIMUM_FARE;

    RideTypeEnum(double MINIMUM_COST_PER_KILOMETER, int COST_PER_TIME, double MINIMUM_FARE) {
        this.MINIMUM_COST_PER_KILOMETER = MINIMUM_COST_PER_KILOMETER;
        this.COST_PER_TIME = COST_PER_TIME;
        this.MINIMUM_FARE = MINIMUM_FARE;
    }
}
