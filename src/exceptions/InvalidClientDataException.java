package exceptions;

public class InvalidClientDataException extends Exception{
    @Override
    public String toString() {
        return "The client's data is invalid!";
    }
}
