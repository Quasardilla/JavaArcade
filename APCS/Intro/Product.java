package APCS.Intro;

public class Product {
    //INSTANCE FIELDS
    
    private String name;
    private double price;

    //CONSTRUCTORS

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product()
    {
        name = ""+(char)+getRandomNumber(65, 91);
        price = getRandomNumber(0, 101) + ((double) getRandomNumber(0, 100)/100.0);
    }

    //METHODS

    //get random number between a min and max
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void reducePrice(double dollars)
    {
        price -= dollars;
    }

    public String toString()
    {
        return "Product[name=" +name + ", price=" + price + "]";
    }
}
