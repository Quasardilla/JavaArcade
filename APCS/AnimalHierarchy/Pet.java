package AnimalHierarchy;

public abstract class Pet extends Animal {
    String name;

    @Override
    public abstract void feed();

    @Override
    public abstract void groom();

    @Override
    public abstract void checkUp();


    @Override
    public String toString() {
        return "[" +
            "Name: " + name +
            "]";
    }

}
