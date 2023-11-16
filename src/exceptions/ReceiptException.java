package exceptions;

public class ReceiptException extends Exception{
    public ReceiptException() {
        super("Can't generate receipt!");
    }
}
