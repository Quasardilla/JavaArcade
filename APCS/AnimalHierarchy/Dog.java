package AnimalHierarchy;

public class Dog extends Pet {
    protected static int currentLicenseNumber = 10000;
    protected int licenseNumber;

    public Dog() {
        super("Default Dog", "dog", 0.0, "01-01-2016");
        licenseNumber = currentLicenseNumber;
        currentLicenseNumber++;
    }

    public Dog(String name) {
        super(name, "dog", 0.0, "01-01-2016");
        licenseNumber = currentLicenseNumber;
        currentLicenseNumber++;
    }
    
    public Dog(String name, String birthday, double weight) {
        super(name, "dog", weight, birthday);
        licenseNumber = currentLicenseNumber;
        currentLicenseNumber++;
    }


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
        return super.toString() + "[" +
            "License# " + licenseNumber +
            "]";
    }

}
