package AnimalHierarchy;

public abstract class Pet extends Animal {
    protected String name;

    public Pet(String name, String species, double weight, String birthday)
    {
        super(species, weight, birthday);
        this.name = name;
    }

    @Override
    public abstract void feed();

    @Override
    public abstract void groom();

    @Override
    public abstract void checkUp();


    @Override
    public String toString() {
        return super.toString() + "[" +
            "Name: " + name +
            "]";
    }

}
