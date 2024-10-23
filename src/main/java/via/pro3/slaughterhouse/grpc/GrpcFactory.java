package via.pro3.slaughterhouse.grpc;

import via.pro3.proto.Animal;
import via.pro3.proto.AnimalType;

public class GrpcFactory {

    public static Animal createAnimal(int id, AnimalType type, double weight) {
        return Animal.newBuilder()
                .setId(id)
                .setType(type)
                .setWeight(weight)
                .build();
    }
}
