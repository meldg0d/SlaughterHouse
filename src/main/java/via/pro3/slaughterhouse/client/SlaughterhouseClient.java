package via.pro3.slaughterhouse.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import via.pro3.proto.*;

import java.util.List;

public class SlaughterhouseClient {

    private final SlaughterhouseServiceGrpc.SlaughterhouseServiceBlockingStub blockingStub;

    public SlaughterhouseClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Use plaintext for local testing
                .build();
        blockingStub = SlaughterhouseServiceGrpc.newBlockingStub(channel);
    }

    public void addAnimal(int id, AnimalType type, double weight) {
        Animal request = Animal.newBuilder()
                .setId(id)
                .setType(type)
                .setWeight(weight)
                .build();

        Animal response = blockingStub.addAnimal(request);
        System.out.println("Animal added: " + response);
    }

    public void getAnimal(int id) {
        AnimalRequest request = AnimalRequest.newBuilder()
                .setId(id)
                .build();

        Animal response = blockingStub.getAnimal(request);
        System.out.println("Animal fetched: " + response);
    }

    public void getAllAnimals() {
        Animals response = blockingStub.getAllAnimals(com.google.protobuf.Empty.getDefaultInstance());
        List<Animal> animals = response.getAnimalsList();
        System.out.println("All animals: ");
        animals.forEach(System.out::println);
    }

    public static void main(String[] args) {
        SlaughterhouseClient client = new SlaughterhouseClient("localhost", 6565); // Change port if needed

        System.out.println("Adding an animal...");
        client.addAnimal(7, AnimalType.CHICKEN, 500.0);

        System.out.println("Fetching animal with ID 1...");
        client.getAnimal(1);

        System.out.println("Fetching all animals...");
        client.getAllAnimals();
    }
}
