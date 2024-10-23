package via.pro3.slaughterhouse.grpc;

import via.pro3.slaughterhouse.model.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class GrpcToModel {

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

    public static via.pro3.slaughterhouse.model.Animal animal(via.pro3.proto.Animal animal) {

        // Convert enum to String
        String animalTypeString = animal.getType().name();

        // Convert the String back to AnimalType if needed (just an example for your use case)
        via.pro3.slaughterhouse.model.AnimalType animalType = via.pro3.slaughterhouse.model.AnimalType.valueOf(animalTypeString);


        return new via.pro3.slaughterhouse.model.Animal(animal.getId(), animalType , animal.getWeight());
    }

    public static List<via.pro3.slaughterhouse.model.Animal> animals(via.pro3.proto.Animals animals) {
        return animals.getAnimalsList().stream()
                .map(GrpcToModel::animal)
                .collect(Collectors.toList());
    }


}
