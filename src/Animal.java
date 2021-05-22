import exceptions.InvalidAnimalDataException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Animal {
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

    private static void addAnimal(Animal animal) {
        if (!animals.contains(animal))
            animals.add(animal);
    }

    public static void removeAnimal(Animal chat) {
        animals.remove(chat);
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
}
