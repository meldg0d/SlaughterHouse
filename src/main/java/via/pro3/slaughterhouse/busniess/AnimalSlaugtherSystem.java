package via.pro3.slaughterhouse.busniess;

import via.pro3.slaughterhouse.model.Animal;
import via.pro3.slaughterhouse.busniess.persistence.PersistenceException;

import java.util.List;

public interface AnimalSlaugtherSystem {
    Animal addAnimal(int id, AnimalType type, double weight) throws PersistenceException;
    void removeAnimal(Animal animal) throws PersistenceException;
    void updateAnimal(Animal animal) throws PersistenceException;
    Animal getAnimal(int id) throws PersistenceException;
    List<Animal> getAllAnimals() throws PersistenceException;
}
