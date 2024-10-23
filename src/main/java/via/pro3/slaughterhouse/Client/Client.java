package via.pro3.slaughterhouse.Client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import via.pro3.proto.AnimalType;
import via.pro3.proto.SlaughterhouseServiceGrpc;
import via.pro3.slaughterhouse.grpc.GrpcFactory;
import via.pro3.slaughterhouse.grpc.GrpcToModel;

import java.util.List;

public class Client {

    private final String host;

    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private ManagedChannel channel() {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }

    public via.pro3.slaughterhouse.model.Animal registerAnimal(int id, AnimalType type, double weight) {
        ManagedChannel channel = channel();
        try {
            // Create a stub for making gRPC requests
            SlaughterhouseServiceGrpc.SlaughterhouseServiceBlockingStub stub = SlaughterhouseServiceGrpc.newBlockingStub(channel);

            // Create the gRPC Animal object (via.pro3.proto.Animal) using a factory
            via.pro3.proto.Animal data = GrpcFactory.createAnimal(id, type, weight);

            // Send the gRPC Animal object and get the response (which is also a via.pro3.proto.Animal)
            via.pro3.proto.Animal animalData = stub.addAnimal(data);

            // Convert the response from via.pro3.proto.Animal to via.pro3.slaughterhouse.model.Animal
            return GrpcToModel.animal(animalData);
        } finally {
            // Shut down the channel
            channel.shutdown();
        }
    }

//    public List<via.pro3.slaughterhouse.model.Animal> getAllAnimals() {
//        ManagedChannel channel = channel();
//        try {
//            SlaughterhouseServiceGrpc.SlaughterhouseServiceBlockingStub stub = SlaughterhouseServiceGrpc.newBlockingStub(channel);
//            via.pro3.proto.Animals animalsData = stub.getAnimal(via.pro3.proto.Empty.newBuilder().build());
//            return GrpcToModel.animals(animalsData);    }


    }