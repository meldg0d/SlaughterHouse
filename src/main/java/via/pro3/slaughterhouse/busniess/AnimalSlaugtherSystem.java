package via.pro3.slaughterhouse.busniess;

import org.springframework.stereotype.Service;
import via.pro3.slaughterhouse.model.Animal;
import via.pro3.slaughterhouse.busniess.persistence.PersistenceException;
import via.pro3.slaughterhouse.model.AnimalType;

import java.util.List;

public interface AnimalSlaugtherSystem {
    Animal addAnimal(int id, AnimalType type, double weight) throws PersistenceException;
    void removeAnimal(Animal animal) throws PersistenceException;
    void updateAnimal(Animal animal) throws PersistenceException;
    Animal getAnimal(int id) throws PersistenceException;
    List<Animal> getAllAnimals() throws PersistenceException;
}
