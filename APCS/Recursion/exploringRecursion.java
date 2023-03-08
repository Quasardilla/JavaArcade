package Recursion;

public class exploringRecursion {



    public static void main(String[] args) {
        // System.out.println(sumAB(3, 5));
        // System.out.println(countDigits(3));
        // System.out.println(evenOdd(239877));
        // System.out.println(rushEEE("hello worlde"));
        // System.out.println(isPalindrome("gohangasalamiimalasagnahog"));
        // forkBomb();
        System.out.println(gcf(16, 32));
    }




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


    public static String evenOdd(int num) {
        return  ((num & 1) == 1) ? "odd" : "even";
    }

    public static int rushEEE(String str)
    {
        return (str.length() == 0) ? 0 : (str.charAt(0) == 'e' || str.charAt(0) == 'E') ? 1 + rushEEE(str.substring(1)) : 0 + rushEEE(str.substring(1));
        // if (str.length() != 0)
        // {
        //     return (str.charAt(0) == 'e' || str.charAt(0) == 'E') ? 1 + rushEEE(str.substring(1)) : 0 + rushEEE(str.substring(1));
        // }
        // else
        // {
        //     return 0;
        // }
    }

    public static boolean isPalindrome(String str)
    {
        return (str.length() <= 2) ? true : (str.charAt(0) == str.charAt(str.length()-1)) ? isPalindrome(str.substring(1, str.length()-1)) && true : false;

        // if (str.length() <= 2)
        // {
        //     return true;
        // }
        // else
        // {
        //     if (str.charAt(0) == str.charAt(str.length()-1))
        //     {
        //         return isPalindrome(str.substring(1, str.length()-1)) && true;
        //     }
        //     else
        //     {
        //         return isPalindrome(str.substring(1, str.length()-1)) && false;
        //     }
        // }
    }

    public static void forkBomb()
    {
        Thread t = new Thread(
            () -> {System.out.println("test");}
        );

        t.start();

        System.out.println("oki");
    }

    public static int gcf(int a, int b)
    {
        System.out.println("a: "+a+" b: "+b);
        return (a <= 0 || b <= 0) ? Math.min(a, b) : gcf(b, a%b);

        // if (a <= 0 || b <= 0) return (a != 0) ? a : b;
        // // int temp = Math.min(a, b);
        // // a = Math.max(a, b);
        // // b = temp;
        // return gcf(b, a%b);
    }
}
