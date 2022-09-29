package Intro;

import java.util.ArrayList;
import java.util.Arrays;
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

        
        System.out.println("arraylist");
        removeDuplicates(products1);
        Collections.sort(products1);
        printLst(products1);

        System.out.println("standard");

        Product[] products2 = new Product[1000];
        for(int i = 0; i < products2.length; i++)
        {
            products2[i] = new Product();
        }

        removeDuplicates(products2);
        sortStandardArray(products2);

        for (Product product : products2) {
            if (product != null) System.out.println(product);
        }
    }

    static void printLst(ArrayList<Product> arr)
    {
        for (Product p : arr)
            System.out.println(p);
    }

    static void removeDuplicates(ArrayList<Product> arr)
    {
        for (int i = 0; i < arr.size(); i++)
        {
            for (int j = i + 1; j < arr.size(); j++)
            {
                if (arr.get(i).equals(arr.get(j)))
                {
                    arr.remove(j);
                    j--;
                }
            }
        }
    }

    public static void removeDuplicates(Product[] arr)
    {
        //set all duplicate items to null
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = i+1; j < arr.length; j++)
            {
                if (arr[i] != null && arr[j] != null && arr[i].equals(arr[j])) arr[j] = null;
            }
        }
    }

    public static void sortStandardArray(Product[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = i+1; j < arr.length; j++)
            {
                if (arr[i] != null && arr[j] != null && arr[i].compareTo(arr[j]) > 0)
                {
                    Product temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
