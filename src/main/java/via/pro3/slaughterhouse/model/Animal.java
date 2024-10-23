package via.pro3.slaughterhouse.model;

public class Animal {
    private int id;
    private AnimalType type;
    private double weight;

    public Animal(int id, AnimalType type, double weight) throws ValdidationExecption {
        if(type == null) throw new ValdidationExecption("type is required");
        if(weight <= 0) throw new ValdidationExecption("weight must be positive");

        this.id = id;
        this.type = type;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
