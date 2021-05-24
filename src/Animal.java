import exceptions.InvalidAnimalDataException;
import exceptions.XorAnimalException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Animal {
    private ArrayList<Client> owners;
    private ArrayList<Seller> sellers;

    private static ArrayList<Animal> animals = new ArrayList<>();

    private String name;
    private LocalDate birthDate;
    private String breed;

    public Animal(String name, String breed, LocalDate birthDate) throws InvalidAnimalDataException {
        setName(name);
        setBreed(breed);
        setBirthDate(birthDate);
        addAnimal(this);
    }

    public void addOwner(Client owner) throws XorAnimalException {
        if (sellers == null && owners == null) {
            owners = new ArrayList<>();
        }
        if (sellers == null) {
            if (!owners.contains(owner)) {
                owners.add(owner);
                owner.addOwnedAnimal(this);
            }
        } else
            throw new XorAnimalException();
    }

    public void addSeller(Seller seller) throws XorAnimalException {
        if (sellers == null && owners == null) {
            sellers = new ArrayList<>();
        }
        if (owners == null) {
            if (!sellers.contains(seller)) {
                sellers.add(seller);
                seller.addAnimalToSell(this);
            }
        } else
            throw new XorAnimalException();
    }

    public void removeOwner(Client owner) throws XorAnimalException {
        if (sellers != null)
            throw new XorAnimalException();
        if (owners != null)
            owners.remove(owner);
    }

    public void removeSeller(Seller seller) throws XorAnimalException {
        if (owners != null)
            throw new XorAnimalException();
        if (sellers != null)
            sellers.remove(seller);
    }

    public void showXorAssociation() {
        if (owners != null) {
            System.out.println(getName() + " owners:");
            for (Client client : owners)
                System.out.println(client);
        }
        if (sellers != null) {
            System.out.println(getName() + " sellers:");
            for(Seller seller : sellers)
                System.out.println(seller);
        }
    }

    private static void addAnimal(Animal animal) {
        if (!animals.contains(animal))
            animals.add(animal);
    }

    public static void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public static void showAnimals() {
        for (Animal animal : animals)
            System.out.println(animal + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidAnimalDataException {
        if (name == null)
            throw new InvalidAnimalDataException();
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws InvalidAnimalDataException {
        if (name == null)
            throw new InvalidAnimalDataException();
        this.birthDate = birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) throws InvalidAnimalDataException {
        if (name == null)
            throw new InvalidAnimalDataException();
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Animal " + getName() + ", breed: " + getBreed() + ", birth date: " + getBirthDate();
    }
}
