package APCS.Intro;

import java.util.Scanner;

public class Intro {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println("AB\nCD\n\"E\"");

        Scanner sc = new Scanner(System.in);

        System.out.print("Please Enter Your Name: ");
        String name = sc.nextLine();
        System.out.println(name);

        sc.close();

        int x1 = 5;
        int x2 = 8;

        System.out.println((x1 + x2) / 2);
    }
}
