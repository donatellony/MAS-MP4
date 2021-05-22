import exceptions.InvalidAnimalSellerDataException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnimalSeller {
    private static ArrayList<AnimalSeller> animalSellers = new ArrayList<AnimalSeller>(){
        @Override
        public boolean add(AnimalSeller animalSeller) {
            boolean result = super.add(animalSeller);
            if(!result)
                return false;
            this.sort((Comparator.comparingInt(o -> o.animalsSold)));
            return true;
        }
    };

    private String name;
    private String surname;
    private String town;
    private int animalsSold;

    public AnimalSeller(String name, String surname, String town) throws InvalidAnimalSellerDataException {
        setName(name);
        setSurname(surname);
        setTown(town);
        addAnimalSeller(this);
    }

    public AnimalSeller(String name, String surname, String town, int animalsSold) throws InvalidAnimalSellerDataException {
        this(name, surname, town);
        setAnimalsSold(animalsSold);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidAnimalSellerDataException {
        if (name == null)
            throw new InvalidAnimalSellerDataException();
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidAnimalSellerDataException {
        if (surname == null)
            throw new InvalidAnimalSellerDataException();
        this.surname = surname;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) throws InvalidAnimalSellerDataException {
        if (town == null)
            throw new InvalidAnimalSellerDataException();
        this.town = town;
    }

    public static void addAnimalSeller(AnimalSeller animalSeller) {
        if (!animalSellers.contains(animalSeller))
            animalSellers.add(animalSeller);
    }

    public static void removeAnimalSeller(AnimalSeller animalSeller) {
        animalSellers.remove(animalSeller);
    }

    public static void showAnimalSellers() {
        for (AnimalSeller p : animalSellers)
            System.out.println(p.toString());
    }

    public int getAnimalsSold() {
        return animalsSold;
    }

    public void setAnimalsSold(int animalsSold) throws InvalidAnimalSellerDataException {
        if(animalsSold < 0)
            throw new InvalidAnimalSellerDataException();
        this.animalsSold = animalsSold;
    }
}
