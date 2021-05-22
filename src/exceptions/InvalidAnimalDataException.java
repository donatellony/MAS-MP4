package exceptions;

public class InvalidAnimalDataException extends Exception {
    @Override
    public String toString() {
        return "Animal's entered data is invalid!";
    }
}
