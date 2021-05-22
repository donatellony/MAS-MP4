package exceptions;

public class InvalidAnimalSellerDataException extends Exception{
    @Override
    public String toString() {
        return "The animal sellers' data is invalid!";
    }
}
