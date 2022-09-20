package APCS.Intro;

import java.util.ArrayList;
import java.util.Scanner;

public class Intro {
    public static void main(String[] args) {

        ArrayList<Product> products1 = new ArrayList<Product>(20);
        // System.out.println("Hexxllo World!");

        // System.out.println("AB\nCD\n\"E\"");

        // Scanner sc = new Scanner(System.in);

        // System.out.print("Please Enter Your Name: ");
        // String name = sc.nextLine();
        // System.out.println(name);

        // sc.close();

        // double x1 = 5.0;
        // double x2 = 8.0;

        // System.out.println((x1 + x2) / 2);
        
        for(int i = 0; i < 20; i++)
        {
            products1.add(new Product());
        }

        for (Product p : products1)
            System.out.println(p);
        
    }
}
