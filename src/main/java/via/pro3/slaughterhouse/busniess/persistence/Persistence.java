package via.pro3.slaughterhouse.busniess.persistence;

import via.pro3.slaughterhouse.model.Animal;
import via.pro3.slaughterhouse.model.AnimalType;

import java.util.Collection;

public interface Persistence {
    Animal create(int id, AnimalType type, double weight) throws PersistenceException;
    Collection<Animal> readAll() throws PersistenceException;
    void update(Animal animal) throws PersistenceException;
    void delete(Animal animal) throws PersistenceException;
    Animal read(int id) throws PersistenceException;
}
