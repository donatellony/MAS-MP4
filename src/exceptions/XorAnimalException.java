package exceptions;

public class XorAnimalException extends Exception {
    @Override
    public String toString() {
        return "An animal already has the XOR link!";
    }
}
