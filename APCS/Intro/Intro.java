package Intro;

import java.util.ArrayList;
import java.util.Collections;

public class Intro {
    public static void main(String[] args) {

        ArrayList<Product> products1 = new ArrayList<Product>();
        // // System.out.println("Hexxllo World!");

        // // System.out.println("AB\nCD\n\"E\"");

        // // Scanner sc = new Scanner(System.in);

        // // System.out.print("Please Enter Your Name: ");
        // // String name = sc.nextLine();
        // // System.out.println(name);

        // // sc.close();

        // // double x1 = 5.0;
        //         // double x2 = 8.0;

        // // System.out.println((x1 + x2) / 2);
        

        for(int i = 0; i < 1000; i++)
        {
            products1.add(new Product());
        }
        // products1.set(products1.size() - 1, new Product("Tablet", 200));



        // // Product p1 = new Product("S22", 1200);
        // // System.out.println(p1);
        // // Product p2 = new Product("S22", 1200);
        // // System.out.println(p2);
        // // // Product p3 = new Product("S22", 1200);
        // // //sets p1 to p2's exact memory location
        // // p1 = p2;
        // // p2.reducePrice(500);
        // // System.out.println(p1 + "\n" + p2);


        // Product p1 = new Product("iPhone", 800);
        // Product p2 = new Product("iPhone", 500);
        // Product p3 = new Product("iPhone", 300);

        // System.out.println(p3.compareTo(p1));
        // System.out.println("D".compareTo("A"));

        // Collections.sort(products1);
        products1 = removeDuplicates(products1);
        printLst(products1);
    }

    static void printLst(ArrayList<Product> arr)
    {
        for (Product p : arr)
            System.out.println(p);
    }

    static ArrayList<Product> removeDuplicates(ArrayList<Product> arr)
    {
        Collections.sort(arr);
        
        for (int i = 0; i < arr.size()-1; i++)
        {
            if (arr.get(i).equals(arr.get(i+1))) arr.remove(i);
        }
        return arr;
    }
}
