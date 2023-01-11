package WhileLoops;

import java.util.Scanner;

public class WhileLoopStuff {
    public static void main(String[] args) {
        exceeds100();
    }

    public static void exceeds100()
    {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        boolean hasNegative = false;

        do{
            System.out.println("Enter an integer");
            int input = sc.nextInt();
            sum += input;
            count++;
            if(input < 0)
                hasNegative = true;
        }
        while(sum < 100 && count < 20);

        sc.close();

        System.out.println("You entered " + count + " values.");
        if(hasNegative)
            System.out.println("It had negative values");
        System.out.println("This sum is " + sum + ".");
    }
}