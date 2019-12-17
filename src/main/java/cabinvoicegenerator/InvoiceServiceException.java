package cabinvoicegenerator;

public class InvoiceServiceException extends Exception {

    enum ExceptionType{INVALID_JOURNEYTYPE,INVALID_USER, INVALID_INPUT;
    }
    ExceptionType type;
    public InvoiceServiceException(ExceptionType type, String message) {
        super(message);
        this.type=type;
    }
}
