package exceptions;

public class InvalidSellerDataException extends Exception{
    @Override
    public String toString() {
        return "The animal sellers' data is invalid!";
    }
}
