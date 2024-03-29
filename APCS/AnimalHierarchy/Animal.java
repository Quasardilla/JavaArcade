package AnimalHierarchy;

public abstract class Animal implements Careable{
    protected String species;
    protected double weight;
    protected String birthday;

    public Animal(String species, double weight, String birthday) {
        this.species = species;
        this.weight = weight;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "[" +
            "Species: " + species +
            ", Born: " + birthday + 
            ", Weight: " + weight +
            "]";
    }

}
