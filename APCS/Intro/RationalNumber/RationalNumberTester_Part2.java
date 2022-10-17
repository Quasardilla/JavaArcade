package Intro.RationalNumber;

import java.util.ArrayList;
import java.util.Collections;

//Mr. Uhl
//Program description: A class to test the RationalNumber class.


public class RationalNumberTester_Part2
{
   public static void main(String[] args)
   {
      //TEST CODE FOR PART 1
      //Construct rational numbers
      RationalNumber r1 = new RationalNumber(3, 4);
      RationalNumber r2 = new RationalNumber(2, 5);
      
      //The toString method of a RationalNumber object
      System.out.println("1) " + r1);  //Output: RationalNumber [numerator = 3, denominator = 4]
      System.out.println("2) " + r2);  //Output: RationalNumber [numerator = 2, denominator = 5]
      
      //Expressing the value of the RationalNumber object as a decimal
      System.out.println("3) " + r1.getRationalForm() + " is equivalent to " + r1.asDecimal());  //Output: 0.75
      System.out.println("4) " + r2.getRationalForm() + " is equivalent to " + r2.asDecimal());  //Output: 0.4
      
      //Operations with RationalNumber object
      r1.add(r2);
      System.out.println("5) " + r1);  //Output: RationalNumber [numerator=23, denominator=20]
      r1 = new RationalNumber(3, 4);
      r1.subtract(r2);
      System.out.println("6) " + r1);  //Output: RationalNumber [numerator=7, denominator=20]
      r1 = new RationalNumber(3, 4);
      r1.multiply(r2);
      System.out.println("7) " + r1);  //Output: RationalNumber [numerator=6, denominator=20]
      r1 = new RationalNumber(3, 4);
      r1.divide(r2);
      System.out.println("8) " + r1);  //Output: RationalNumber [numerator=15, denominator=8]
      
      //TEST CODE FOR PART 2
      RationalNumber r3 = new RationalNumber(10, -30);
      System.out.println("9) " + r3.getRationalForm());                 //Output: 10/-30
      r3.simplify();
      System.out.println("10) " + r3.getRationalForm());                 //Output: -1/3
      System.out.println("11) " + r3.getReciprocal().getRationalForm()); //Output: -3
      
      RationalNumber r4 = new RationalNumber(1, 2);
      RationalNumber r5 = new RationalNumber(5, 8);
      r4.add(r5);
      System.out.println("12) " + r4.getRationalForm());                 //Output: 18/16
      r4.simplify();
      System.out.println("13) " + r4.getRationalForm());                 //Output: 9/8
      
      r4.subtract(r4);
      System.out.println("14) " + r4.getRationalForm());                 //Output: 0/64
      r4.simplify();
      System.out.println("15) " + r4.getRationalForm());                 //Output: 0
      
      //Testing equals
      System.out.println("\nTesting equals...");

      RationalNumber r6 = new RationalNumber(15, 3);
      RationalNumber r7 = new RationalNumber(-5, -1);
      RationalNumber r8 = new RationalNumber(10, 2);
      RationalNumber r9 = new RationalNumber(5, -1);
      
      System.out.print("16) " + r6.getRationalForm() + " equals " + r7.getRationalForm() + " is ");
      System.out.println(r6.equals(r7));  //Output: true
      
      System.out.print("17) " + r7.getRationalForm() + " and " + r8.getRationalForm() + " is ");
      System.out.println(r7.equals(r8));  //Output: true
      
      System.out.print("18) " + r7.getRationalForm() + " and " + r9.getRationalForm() + " is ");
      System.out.println(r7.equals(r9));  //Output: false
      
      //Testing compareTo
      System.out.println("\nTesting compareTo...");
      RationalNumber r10 = new RationalNumber(42,1);
      RationalNumber r11 = new RationalNumber(-314,100);
      
      ArrayList<RationalNumber> fractionList = new ArrayList<RationalNumber>();
      fractionList.add(r1);fractionList.add(r2);fractionList.add(r3);fractionList.add(r4);
      fractionList.add(r5);fractionList.add(r6);fractionList.add(r7);fractionList.add(r8);
      fractionList.add(r9);fractionList.add(r10);fractionList.add(r11);
      
      System.out.println("Current List of Rational Numbers:");
      for(RationalNumber r : fractionList)
         System.out.print(r.getRationalForm() + " ");
      System.out.println();
      
      Collections.sort(fractionList);
      System.out.println("\nSorted List of Rational Numbers:");
      for(RationalNumber r : fractionList)
         System.out.print(r.getRationalForm() + " ");
      System.out.println();
      
      System.out.println("\nSorted List as Decimals:");
      for(RationalNumber r : fractionList)
         System.out.print(r.asDecimal() + " ");
      System.out.println();
   }
}
