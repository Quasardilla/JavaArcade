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
        name = "";
        price = 0;
    }

    //METHODS

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
