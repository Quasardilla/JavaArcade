package AnimalHierarchy;

public abstract class Animal implements Careable{
    String species;
    Double weight;
    Double birthday;


    @Override
    public abstract void feed();

    @Override
    public abstract void groom();

    @Override
    public abstract void checkUp();


    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
            " species='" + species + "'" +
            ", weight='" + weight + "'" +
            ", birthday='" + birthday + "'" +
            "}";
    }


}
