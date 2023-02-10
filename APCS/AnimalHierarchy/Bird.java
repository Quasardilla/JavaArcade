package AnimalHierarchy;

public class Bird extends Pet {
    boolean exotic = false;

    @Override
    public void feed() {
        System.out.println(super.name + " eating bird food.");
    }

    @Override
    public void groom() {
        System.out.println(super.name + " taking a bird bath and filing beak");
        
    }

    @Override
    public void checkUp() {
        System.out.println(super.name + " getting bird parts examined");

    }


    @Override
    public String toString() {
        return super.toString() + "[" +
            "Exotic: " + exotic + 
            "]";
    }

}
 