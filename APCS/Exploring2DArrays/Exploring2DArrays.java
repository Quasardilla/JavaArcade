package Exploring2DArrays;

public class Exploring2DArrays {
    public static void main(String[] args) {
        int[][] nums = new int[3][5];
        randomize2DArray(nums);
        nums[2][2] = 42;
        print2DArrayForEach(nums);

        System.out.println(getColumnAverage(nums, 2));
    }

    public static void print2DArray(int[][] arr2D)
    {
        for(int i = 0; i < arr2D.length; i++)
        {
            for(int j = 0; j < arr2D[i].length; j++)
            System.out.print(arr2D[i][j]);

            System.out.println("");
        }
    }

    public static void print2DArrayForEach(int[][] arr2D)
    {
        for(int[] row : arr2D)
        {
            for(int col : row)
                System.out.print(col + " ");

            System.out.println("");
        }
    }

    public static void initialize2DArray(int[][] arr2d)
    {
        for(int i = 0; i < arr2d.length; i++)
        {
            for(int j = 0; j < arr2d[i].length; j++)
                arr2d[i][j] = -1;
        }
    }

    public static void randomize2DArray(int[][] arr2d)
    {
        for(int i = 0; i < arr2d.length; i++)
        {
            for(int j = 0; j < arr2d[i].length; j++)
                arr2d[i][j] = (int) (Math.random() * 21) + 80;
        }
    }

    public static double getColumnAverage(int[][] arr2d, int col)
    {
        double sum = 0;
        for(int i = 0; i < arr2d.length; i++)
            sum += arr2d[i][col];
        return sum /= arr2d[0].length;

    }
}

