package via.pro3.slaughterhouse.grpc;

import via.pro3.proto.Animal;
import via.pro3.proto.Animals;

import java.util.List;
import java.util.stream.Collectors;

public class ModelToGrpc {


    public static via.pro3.proto.Animal toProto(via.pro3.slaughterhouse.model.Animal modelAnimal) {
        return via.pro3.proto.Animal.newBuilder()
                .setId(modelAnimal.getId())
                .setType(toProto(modelAnimal.getType()))  // Convert model AnimalType back to gRPC AnimalType
                .setWeight(modelAnimal.getWeight())
                .build();
    }

    public static via.pro3.proto.AnimalType toProto(via.pro3.slaughterhouse.model.AnimalType modelType) {
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

    // Convert model AnimalType to proto AnimalType

    // Convert proto AnimalType to model AnimalType
    public static via.pro3.slaughterhouse.model.AnimalType toModel(via.pro3.proto.AnimalType protoType) {
        switch (protoType) {
            case COW:
                return via.pro3.slaughterhouse.model.AnimalType.COW;
            case PIG:
                return via.pro3.slaughterhouse.model.AnimalType.PIG;
            case CHICKEN:
                return via.pro3.slaughterhouse.model.AnimalType.CHICKEN;
            default:
                throw new IllegalArgumentException("Unknown AnimalType: " + protoType);
        }
    }

    // Convert a list of model Animal objects to proto Animals message
    // Convert a list of model Animal objects to proto Animals message
    public static via.pro3.proto.Animals animals(List<via.pro3.slaughterhouse.model.Animal> modelAnimals) {
        // Convert each model Animal to proto Animal using the toProto method
        List<via.pro3.proto.Animal> protoAnimals = modelAnimals.stream()
                .map(ModelToGrpc::toProto)  // Method reference to convert each model Animal to proto Animal
                .collect(Collectors.toList());  // Use Collectors.toList() to gather results into a List

        // Build and return the Animals message with the list of proto Animals
        return via.pro3.proto.Animals.newBuilder()
                .addAllAnimals(protoAnimals)  // Add the list of proto Animals to the Animals message
                .build();
    }
}
