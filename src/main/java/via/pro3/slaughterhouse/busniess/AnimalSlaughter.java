package via.pro3.slaughterhouse.busniess;

import org.springframework.stereotype.Service;
import via.pro3.slaughterhouse.model.Animal;
import via.pro3.slaughterhouse.busniess.persistence.Persistence;
import via.pro3.slaughterhouse.busniess.persistence.PersistenceException;
import via.pro3.slaughterhouse.model.AnimalType;
import via.pro3.slaughterhouse.model.ValdidationExecption;

import java.util.*;

@Service

public class AnimalSlaughter implements AnimalSlaugtherSystem {

    private final Map<Integer, Animal> animalCache = new HashMap<>();
    private final Persistence persistence;

    public AnimalSlaughter(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Animal addAnimal(int id, AnimalType type, double weight) throws PersistenceException, ValdidationExecption {
        if(type == null) throw new ValdidationExecption("type is required");
        if(weight <= 0) throw new ValdidationExecption("weight must be positive");

        Animal animal = persistence.create(id, type, weight);
        animalCache.put(id, animal);
        return animal;
    }
    @Override
    public void removeAnimal(Animal animal) throws PersistenceException, ValdidationExecption {
        if(animal == null) throw new ValdidationExecption("animal is required");
        animalCache.remove(animal.getId());
        persistence.delete(animal);
    }

    @Override
    public void updateAnimal(Animal animal) throws PersistenceException, ValdidationExecption {
        if(animal == null) throw new ValdidationExecption("animal is required");
        animalCache.put(animal.getId(), animal);
        persistence.update(animal);
    }

    @Override
    public Animal getAnimal(int id) throws PersistenceException, ValdidationExecption {
        if(id <= 0) throw new ValdidationExecption("id must be positive");
        if (!animalCache.containsKey(id)) {
            animalCache.put(id, persistence.read(id));
        }
        return animalCache.get(id);
    }

    @Override
    public List<Animal> getAllAnimals() throws PersistenceException, ValdidationExecption {
        Collection<Animal> allAnimals = persistence.readAll();
        LinkedList<Animal> list = new LinkedList<>();
        for(Animal animal: allAnimals) {
            if (!animalCache.containsKey(animal.getId())) {
                animalCache.put(animal.getId(), animal);
            }
            list.add(animalCache.get(animal.getId()));
        }
        return list;
    }
}
