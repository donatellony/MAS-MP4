import exceptions.AttributeSellerRestriction;
import exceptions.InvalidSellerDataException;
import exceptions.XorAnimalException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class Seller {
    private static ArrayList<Seller> sellers = new ArrayList<Seller>() {
        @Override
        public boolean add(Seller seller) {
            boolean result = super.add(seller);
            if (!result)
                return false;
            this.sort(Comparator.comparingDouble(Seller::getRating));
            return true;
        }
    };

    private ArrayList<Transaction> transactions = new ArrayList<>();

    private ArrayList<Animal> animalsToSell = new ArrayList<>();

    private String name;
    private String surname;
    private String town;
    private LocalDate birthDate;
    private static final short minSellerAge = 16; // attribute restriction

    private float rating;

    public Seller(String name, String surname, String town, LocalDate birthDate) throws InvalidSellerDataException, AttributeSellerRestriction {
        setName(name);
        setSurname(surname);
        setTown(town);
        setRating(0f);
        setBirthDate(birthDate);
        addSeller(this);
    }

    public Seller(String name, String surname, String town, LocalDate birthDate, float rating) throws InvalidSellerDataException, AttributeSellerRestriction {
        this(name, surname, town, birthDate);
        setRating(rating);
    }

    public void addAnimalToSell(Animal animal) throws XorAnimalException {
        if (animal != null && !animalsToSell.contains(animal)) {
            animalsToSell.add(animal);
            animal.addSeller(this);
        }
    }

    public void removeAnimalToSell(Animal animal) throws XorAnimalException {
        if (animal != null && !animalsToSell.contains(animal)) {
            animalsToSell.add(animal);
            animal.removeSeller(this);
        }
    }

    public void showAnimalsToSell() {
        for (Animal animal : animalsToSell)
            System.out.println(animal);
    }

    public Transaction addTransaction(Client buyer, String description, LocalDate date, float price) {
        return new Transaction(this, buyer, description, date, price);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.getSeller().equals(this))
            transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            transactions.remove(transaction);
            Transaction.removeTransaction(transaction);
        }
    }

    public void showTransactions() {
        for (Transaction transaction : transactions)
            System.out.println(transaction);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidSellerDataException {
        if (name == null)
            throw new InvalidSellerDataException();
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidSellerDataException {
        if (surname == null)
            throw new InvalidSellerDataException();
        this.surname = surname;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) throws InvalidSellerDataException {
        if (town == null)
            throw new InvalidSellerDataException();
        this.town = town;
    }

    public static void addSeller(Seller seller) {
        if (!sellers.contains(seller))
            sellers.add(seller);
    }

    public static void removeSeller(Seller seller) {
        sellers.remove(seller);
    }

    public static void showSellers() {
        for (Seller p : sellers)
            System.out.println(p.toString());
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) throws InvalidSellerDataException {
        if (rating < 0 || rating > 5)
            throw new InvalidSellerDataException();
        this.rating = rating;
        sellers.sort(Comparator.comparingDouble(Seller::getRating));
    }

    @Override
    public String toString() {
        return "Seller " + getName() + " " + getSurname() + ", from " + getTown() + ". His rating is: " + getRating();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws InvalidSellerDataException, AttributeSellerRestriction {
        if (birthDate == null)
            throw new InvalidSellerDataException();
        if (ChronoUnit.YEARS.between(birthDate, LocalDate.now()) < minSellerAge)
            throw new AttributeSellerRestriction(String.format("User must be at least (%s) years old to become the Seller!", minSellerAge));
        this.birthDate = birthDate;
    }
}
