package Intro.product;

import java.lang.Comparable;

public class Product implements Comparable<Product>
{
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
        // price = getRandomNumber(0, 101) + ((double) getRandomNumber(0, 100)/100.0);
        price = 100;
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

    @Override
    public String toString()
    {
        return "Product[name=" +name + ", price=" + price + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        Product p = (Product) obj;
        return p.name.equals(this.name) && p.price == this.price;
    }

    @Override
    public int compareTo(Product other)
    {
        //or str.compareTo();
        /*
         * if(name.compareTo(other.name))
         * return price - (int) other.price;
         * 
         * return name.compareTo(other.name);
         * 
         */

        if(name.compareTo(other.name) == 0)
            return (int) price - (int) other.price;
         
        return name.compareTo(other.name);

        // if (this.price < other.price)
        //     return -1;
        // if (this.price > other.price)
        //     return 1;
        // return 0;

    
    }
}
