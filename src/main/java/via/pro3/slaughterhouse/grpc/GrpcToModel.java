package via.pro3.slaughterhouse.grpc;

import via.pro3.slaughterhouse.model.Animal;
import via.pro3.slaughterhouse.model.AnimalType;

import java.util.List;
import java.util.stream.Collectors;

public class GrpcToModel {

    // Convert proto AnimalType to model AnimalType
    public static AnimalType toModel(via.pro3.proto.AnimalType protoType) {
        switch (protoType) {
            case COW:
                return AnimalType.COW;
            case PIG:
                return AnimalType.PIG;
            case CHICKEN:
                return AnimalType.CHICKEN;
            default:
                throw new IllegalArgumentException("Unknown AnimalType: " + protoType);
        }
    }

    // Convert a single proto Animal to model Animal
    public static Animal animal(via.pro3.proto.Animal protoAnimal) {
        return new Animal(
                protoAnimal.getId(),
                GrpcToModel.toModel(protoAnimal.getType()),  // Convert AnimalType using toModel method
                protoAnimal.getWeight()
        );
    }

    // Convert a list of proto Animals to model Animals
    public static List<Animal> animals(via.pro3.proto.Animals protoAnimals) {
        return protoAnimals.getAnimalsList().stream()
                .map(GrpcToModel::animal)
                .collect(Collectors.toList());
    }
}
