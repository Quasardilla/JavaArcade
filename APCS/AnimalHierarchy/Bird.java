package AnimalHierarchy;

public class Bird extends Pet {
    protected boolean exotic = false;

    public Bird() {
        super("Default Bird", "bird", 0.0, "01-01-2016");
        this.exotic = false;
        
    }

    public Bird(String name) {
        super(name, "bird", 0.0, "01-01-2016");
        this.exotic = false;
    }

    public Bird(String name, String birthday, double weight, boolean exotic) {
        super(name, "bird", weight, birthday);
        this.exotic = exotic;
    }

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
 