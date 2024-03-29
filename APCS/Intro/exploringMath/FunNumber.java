package Intro.exploringMath;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class FunNumber implements Comparable<FunNumber>
{
   private int num;

   /**
    * Constructs a FunNumber from an integer parameter
    */
   public FunNumber(int num) {
      this.num = num;
   }

   /**
    * Constructs a FunNumber from a valid String parameter
    * @param num  the value to assign as this {@code FunNumber}
    */
   public FunNumber(String num) {
      this.num = Integer.parseInt(num);
   }
   
   /**
    * @return the value of the FunNumber as an integer
    */
   public int getFunNum() {
      return num;
   }

   /**
    * @param num  the value to assign to this {@code FunNumber}
    */
   public void setFunNum(int num) {
      this.num = num;
   }
   
   /**
    * @return the factorial of the FunNumber
    */
   public int getFunFact() {

      int temp = 1;

      for(int i = 1; i < num + 1; i++)
      {
         temp *= i;
      }

      return temp;
   }

   /**
    * @return a string list of the factors of the FunNumber value
    */
   public ArrayList<Integer> getFactors() {
      ArrayList<Integer> factors = new ArrayList<Integer>();

         for (int i = 1; i <= Math.sqrt(num) + 1; i++)
            if (num % i == 0)
            {
                factors.add(i);
                if (i != num / i) factors.add(num / i);
            }

      return factors;
   }

   /**
    * @return true if the FunNumber is prime, false otherwise
    */
   public boolean isPrime() {
      for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return false;
        return true;
   }

   /**
    * @return the number of digits in the FunNumber value
    */
   public int numDigits() {
      int length = 1;
      int temp = num;
      while (temp / 10 > 0)
      {
          length++;
          temp /= 10;
      }

      return length;
   }

   /**
    * @return the sum of the digits that make up the FunNumber
    */
   public int sumDigits() {
      int length = 1;
      int temp = num;
      while (temp / 10 > 0)
      {
          length++;
          temp /= 10;
      }

      int value = 0;

      for (int i = 0; i <= length; i++)
      {
          value += (num % Math.pow(10, i)) / Math.pow(10, i-1);
      }
      
      return value;
   }

   /**
    * @return an integer with digits that are the reverse of the FunNumber digits
    */
   public int reverseNum() {
      int reverse = 0;
      while (num > 0)
      {
         reverse *= 10;
         reverse += num % 10;
         num /= 10;
      }
      return reverse;
   }

   /**
    * Overrides the equals method of the Object class
    * @param  other
    *          The object to compare this {@code FunNumber} against
    * @return true of the value is equal to that of the parameter,
    *         false otherwise
    */
   public boolean equals(Object obj) {
      FunNumber other = (FunNumber) obj;
      if(this.num == other.getFunNum())
         return true;
      return false;
   }
   
   /**
    * Overrrides the compareTo method of the Comparable interface
    * @param  other
    *          The {@code FunNumber} to be compared
    * @return -1 if this FunNumber value is less than that of the parameter
    *          0 if this FunNumber value is equal to that of the parameter
    *          1 if this FunNumber value is greater than that of the parameter
    */
   public int compareTo(FunNumber other) {
      if(this.equals(other))
         return 0;
      else if (num > other.getFunNum())
         return 1;
      else
         return -1;
   }
   
   /**
    * @return a String representation of the FunNumber value
    */
   public String toString() {
      return "[num:" + this.num + "]";
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(num);
   }

   /**
    * @return true if the FunNumber list contains duplicate FunNumber objects,
    *         false otherwise
    */
   public static boolean hasDuplicates(ArrayList<FunNumber> nums) {

      Set<FunNumber> set = new HashSet<FunNumber>();

      for(int i = 0; i <= nums.size() - 1; i++)
      {
         if(!set.add(nums.get(i)))
            return true;
      }

      return false;
   }




   public static int lcm(int num1, int num2)
   {
      num1 = Math.abs(num1);
      num2 = Math.abs(num2);

      int lcm = num1 * num2;

      for(int i = lcm - 1; i > 1; i--)
      {
         if(i % num1 == 0 && i % num2 == 0)
         {
            lcm = i;
            System.out.println(i);
         }
      }

      return lcm;
   }

   public static int gcf(int num1, int num2)
   {
      num1 = Math.abs(num1);
      num2 = Math.abs(num2);

      int gcf = num1 * num2;

      for(int i = 1; i < Math.min(num1, num2); i++)
      {
         if(num1 % i == 0 && num2 % i == 0)
         {
            gcf = i;
         }
      }

      return gcf;
   }

   
   
   public static void main(String[] args) {
      ArrayList<FunNumber> nums = new ArrayList<FunNumber>();
      
      for(int i = 0; i < 10; i++)
         // nums.add(new FunNumber((int)(Math.random() * 50) + 1));
         nums.add(new FunNumber(1));
      
      for(FunNumber f : nums)
         System.out.println(f);
      
      System.out.println(FunNumber.hasDuplicates(nums));


      // FunNumber temp = new FunNumber(4);
      // FunNumber temp2 = new FunNumber(4);

      // System.out.println(temp.getFunFact());
      
      System.out.println(lcm(8, 12));
      System.out.println(gcf(8, 12));      
      System.out.println("Finished.");
      
//      //An exploration of using equals versus == (logical vs. reference equality)
//      FunNumber f1 = new FunNumber(48);
//      FunNumber f2 = new FunNumber(42);
//      f2.setNum(f1.getNum());
//      f2.setNum(42);
//      System.out.println(f1);
//      System.out.println(f2);
//      f1 = new FunNumber(f2.getNum());
//      f2.setNum(5);
//      
//      System.out.println(f1==f2);
//      System.out.println(f1.equals(f2));
//      
//      System.out.println(f1);
//      System.out.println(f2);
//      
//      System.out.println(f1.compareTo(f2)); //when is this positive? negative? zero?
      
//      System.out.println("Factors: " + num.getFactors());
//      System.out.println("isPrime: " +num.isPrime());
//      System.out.println("Number of Digits: " + num.numDigits());
//      System.out.println("Sum of Digits: " + num.sumDigits());
//      System.out.println("Reversed Number: " +num.reverseNum());

   }
}
