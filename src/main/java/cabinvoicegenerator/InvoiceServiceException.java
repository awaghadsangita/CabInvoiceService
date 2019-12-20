package cabinvoicegenerator;

public class InvoiceServiceException extends Exception {

    enum ExceptionType{
        INVALID_JOURNEY_TYPE,INVALID_USER, INVALID_INPUT;
    }
    ExceptionType type;
    public InvoiceServiceException(ExceptionType type, String message) {
        super(message);
        this.type=type;
    }
}
