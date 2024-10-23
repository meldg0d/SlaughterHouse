package via.pro3.slaughterhouse.grpc;

import com.google.protobuf.Empty;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import via.pro3.proto.*;
import via.pro3.slaughterhouse.busniess.AnimalSlaugtherSystem;
import via.pro3.slaughterhouse.busniess.persistence.NotFoundException;
import via.pro3.slaughterhouse.busniess.persistence.PersistenceException;
import via.pro3.slaughterhouse.model.ValdidationExecption;

import java.util.List;

@GrpcService
public class SlaughterhouseServiceImpl extends SlaughterhouseServiceGrpc.SlaughterhouseServiceImplBase {

    private final AnimalSlaugtherSystem slaugter;
    private static Logger logger = LoggerFactory.getLogger("Service");

    public SlaughterhouseServiceImpl(AnimalSlaugtherSystem slaugter) {
        this.slaugter = slaugter;
    }


    @Override
    public void addAnimal(Animal request, StreamObserver<Animal> responseObserver) {
        try {
            // Get data from the gRPC request
            int id = request.getId();
            AnimalType protoType = request.getType();  // gRPC AnimalType
            double weight = request.getWeight();

            // Convert gRPC AnimalType to model AnimalType
            via.pro3.slaughterhouse.model.AnimalType modelType = via.pro3.slaughterhouse.model.AnimalType.valueOf(protoType.name());

            // Add the animal using the model's AnimalType
            via.pro3.slaughterhouse.model.Animal modelAnimal = slaugter.addAnimal(id, modelType, weight);

            // Convert modelAnimal to protoAnimal
            via.pro3.proto.Animal protoAnimal = ModelToGrpc.toProto(modelAnimal);

            // Send the gRPC response
            responseObserver.onNext(protoAnimal);
            responseObserver.onCompleted();
        } catch (PersistenceException e) {
            Status error = Status.newBuilder().setCode(Code.INTERNAL_VALUE).setMessage("Could not save data").build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(error));
            logger.error("Could not save", e);
        } catch (ValdidationExecption e) {
            Status error = Status.newBuilder().setCode(Code.INVALID_ARGUMENT_VALUE).setMessage(e.getMessage()).build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(error));
            logger.error("Validation", e);
        }
    }


    @Override
    public void getAllAnimals(Empty request, StreamObserver<Animals> responseObserver) {
        try {
            // Fetch the list of model Animal objects from the slaughter service
            List<via.pro3.slaughterhouse.model.Animal> modelAnimals = slaugter.getAllAnimals();

            // Convert the list of model Animals to gRPC proto Animals
            via.pro3.proto.Animals protoAnimals = ModelToGrpc.animals(modelAnimals);

            // Send the response
            responseObserver.onNext(protoAnimals);
            responseObserver.onCompleted();
        } catch (PersistenceException e) {
            // Handle the error
            Status error = Status.newBuilder()
                    .setCode(Code.INTERNAL_VALUE)
                    .setMessage("Could not retrieve data")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(error));
            logger.error("Could not retrieve animals", e);
        }
    }

    @Override
    public void getAnimal(AnimalRequest request, StreamObserver<Animal> responseObserver) {
        try{
            int id = request.getId();
            via.pro3.slaughterhouse.model.Animal animal = slaugter.getAnimal(id);
            responseObserver.onNext(ModelToGrpc.toProto(animal));
            responseObserver.onCompleted();

        } catch (NotFoundException e) {
            Status error = Status.newBuilder().setCode(Code.NOT_FOUND_VALUE).setMessage("Car not found").build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(error));
        } catch (PersistenceException e) {
            Status error = Status.newBuilder().setCode(Code.INTERNAL_VALUE).setMessage("Could not read data").build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(error));
        } catch (ValdidationExecption e) {
            Status error = Status.newBuilder().setCode(Code.INVALID_ARGUMENT_VALUE).setMessage(e.getMessage()).build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(error));
        }
    }


}
