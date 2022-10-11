package Intro.exploringMath;

import java.util.ArrayList;
import java.util.Arrays;

public class ExploringMathAlgorithms
{

    public static void main(String args[])
    {
        System.out.println("getDigitSum(567): "+getDigitSum(567));
        System.out.println("getNumDigits(578): "+getNumDigits(578));
        System.out.println("getFactors(16): "+getFactors(16));
        System.out.println("isPrime(7): "+isPrime(7));
        System.out.println("reverseNum(12345): "+reverseNum(12345));
        System.out.println("getQuadraticSolutions(1, 0, -4): "+Arrays.toString(getQuadraticSolutions(1, 0, -4)));
    }

    /*
     * 
     *   a) Method name: getDigitSum
              Parameter(s): a single positive integer value
              Return value: an integer representing the sum of the digits of the integer parameter

        b) Method name: getNumDigits
              Parameter(s): a single positive integer value
              Return value: an integer representing the number of digits in the integer parameter

        c) Method name: getFactors
              Parameter(s): a single positive integer value
              Return value: an ArrayList of integers representing the factors of the integer parameter

        d) Method name: isPrime
              Parameter(s): a single positive integer value
              Return value: true if the integer parameter is a prime number, false otherwise

        e) Method name: reverseNum
              Parameter(s): a single positive integer value
              Return value: an integer that is the reverse of the integer parameter

        f) Method name: getQuadraticSolutions
              Parameter(s): three double values representing a, b, and c for a quadratic equation in the form ax2 + bx + c = 0
              Return value: a standard array of two doubles
                                       - These values represent the two solutions to the equation represented by the given parameters
                                       - Imaginary solutions will cause problems... can this be fixed?
     */


     public static int getDigitSum(int num)
     {
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

    public static int getNumDigits(int num)
    {
        int length = 1;
        int temp = num;
        while (temp / 10 > 0)
        {
            length++;
            temp /= 10;
        }

        return length;
    }

    public static ArrayList<Integer> getFactors(int num)
    {
        ArrayList<Integer> points = new ArrayList<Integer>();
        for (int i = 1; i <= Math.sqrt(num) + 1; i++)
            if (num % i == 0)
            {
                points.add(i);
                if (i != num / i) points.add(num / i);
            }

        return points;
    }

    public static boolean isPrime(int num)
    {
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return false;
        return true;
    }

    public static int reverseNum(int num)
    {
        int length = 1;
        int temp = num;
        while (temp / 10 > 0)
        {
            length++;
            temp /= 10;
        }

        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int i = length; i > 0; i--)
        {
            values.add((int) ((num % Math.pow(10, i)) / Math.pow(10, i-1)));
        }

        int value = 0;
        for (int i = 0; i < values.size(); i++)
        {
            value += values.get(i) * Math.pow(10, i);
        }

        return value;


        /*
         * int reverse = 0;
         * while (num > 0)
         * {
         *      reverse *= 10;
         *      reverse += num % 10;
         *      num /= 10;
         * }
         * return reverse;
         * 
         */
    }

    public static double[] getQuadraticSolutions(double a, double b, double c)
    {
        double[] solutions = new double[2];

        solutions[0] = (-b + Math.sqrt((b*b)-(4*a*c))) / (2*a);
        solutions[1] = (-b - Math.sqrt((b*b)-(4*a*c))) / (2*a);            
        
        return solutions;
    }



}
