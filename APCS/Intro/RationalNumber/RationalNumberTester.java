//Mr. Uhl
//Program description: A class to test the RationalNumber class.

package Intro.RationalNumber;

public class RationalNumberTester
{
   public static void main(String[] args)
   {
      //Construct rational numbers
      RationalNumber r1 = new RationalNumber(3, 4);
      RationalNumber r2 = new RationalNumber(2, 5);
      
      //The toString method of a RationalNumber object
      System.out.println(r1);  //Output: RationalNumber [numerator = 3, denominator = 4]
      System.out.println(r2);  //Output: RationalNumber [numerator = 2, denominator = 5]
      
      //Expressing the value of the RationalNumber object as a decimal
      System.out.println(r1.asDecimal());  //Output: 0.75
      System.out.println(r2.asDecimal());  //Output: 0.4
      
      //Operations with RationalNumber object
      r1.add(r2);
      System.out.println(r1);  //Output: RationalNumber [numerator=23, denominator=20]
      r1 = new RationalNumber(3, 4);
      r1.subtract(r2);
      System.out.println(r1);  //Output: RationalNumber [numerator=7, denominator=20]
      r1 = new RationalNumber(3, 4);
      r1.multiply(r2);
      System.out.println(r1);  //Output: RationalNumber [numerator=6, denominator=20]
      r1 = new RationalNumber(3, 4);
      r1.divide(r2);
      System.out.println(r1);  //Output: RationalNumber [numerator=15, denominator=8]
    

   }

}
