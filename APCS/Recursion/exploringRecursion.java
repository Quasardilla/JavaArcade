package Recursion;

public class exploringRecursion {
    public static int factorial(int x){
        return (x == 0) ? 1 : (x > 0) ? x * factorial(x - 1) : x * factorial(x + 1);
    }

    /**
     * 
     * @param a
     *      : Starting number, must be less than b
     * @param b
     *      : Ending number
     * @return
     *      A number that starts at a and adds itself to a number
     *      1 greater than it until the number added reaches b
     * 
     *      Example: sumAB(3, 5)
     *      returns 12, because
     *      3 + 4 + 5 = 12
     */
    public static int sumAB(int a, int b) {
        return (a!=b) ? a+sumAB(a+1, b) : b;
    }


    /**
     * 
     * @param a
     *      : Positive integer
     * @return
     *      The number of digits in a number
     */
    public static int countDigits(int a) {
        return (a < 10) ? 1 : 1 + countDigits(a / 10);
    }

    public static void main(String[] args) {
        System.out.println(sumAB(3, 5));
        System.out.println(countDigits(3));
        System.out.println(evenOdd(2));
    }

    public static String evenOdd(int num) {
        return  ((num>>0 & 1) == 1) ? "odd" : "even";
    }
}
