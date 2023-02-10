package AnimalHierarchy;

public class Dog extends Pet {
    int licenseNumber;

    @Override
    public void feed() {
        System.out.println(super.name + " eating dog food.");
    }

    @Override
    public void groom() {
        System.out.println(super.name + " getting a bath and clipping nails");
        
    }

    @Override
    public void checkUp() {
        System.out.println(super.name + " getting dog stuff examined");

    }


    @Override
    public String toString() {
        return super.toString() + "{" +
            " licenseNumber#='" + licenseNumber + "'" +
            "}";
    }

}
