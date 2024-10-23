package via.pro3.slaughterhouse.grpc;

import via.pro3.proto.Animal;
import via.pro3.proto.Animals;
import via.pro3.slaughterhouse.model.AnimalType;

import java.util.List;
import java.util.stream.Collectors;

public class ModelToGrpc {

    // Convert model AnimalType to proto AnimalType
    public static via.pro3.proto.AnimalType toProto(AnimalType modelType) {
        switch (modelType) {
            case COW:
                return via.pro3.proto.AnimalType.COW;
            case PIG:
                return via.pro3.proto.AnimalType.PIG;
            case CHICKEN:
                return via.pro3.proto.AnimalType.CHICKEN;
            default:
                throw new IllegalArgumentException("Unknown AnimalType: " + modelType);
        }
    }

    // Convert a single model Animal to proto Animal
    public static via.pro3.proto.Animal toProto(via.pro3.slaughterhouse.model.Animal modelAnimal) {
        return via.pro3.proto.Animal.newBuilder()
                .setId(modelAnimal.getId())
                .setType(toProto(modelAnimal.getType()))  // Convert model AnimalType back to proto AnimalType
                .setWeight(modelAnimal.getWeight())
                .build();
    }

    // Convert a list of model Animals to proto Animals
    public static via.pro3.proto.Animals animals(List<via.pro3.slaughterhouse.model.Animal> modelAnimals) {
        List<via.pro3.proto.Animal> protoAnimals = modelAnimals.stream()
                .map(ModelToGrpc::toProto)  // Convert each model Animal to proto Animal
                .collect(Collectors.toList());

        return via.pro3.proto.Animals.newBuilder()
                .addAllAnimals(protoAnimals)  // Add the list of proto Animals to the Animals message
                .build();
    }
}
