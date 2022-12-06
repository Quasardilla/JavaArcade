package Exploring2DArrays;

import java.util.Arrays;

public class Exploring2DArrays {
    public static void main(String[] args) {
        int[][] nums = new int[3][5];
        randomize2DArray(nums);
        nums[2][2] = 42;
        print2DArrayForEach(nums);

        System.out.println(getColumnAverage(nums, 2));
        System.out.println(Arrays.deepToString(new int[][] {{1, 2, 3}, {4, 5, 6}}));
        System.out.println(Arrays.deepToString(getTransposedMatrix(new int[][] {{1, 2, 3}, {4, 5, 6}})));
        
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
    
    public static double getRowAverage(int[][] arr2d, int row)
    {
        double sum = 0;
        for(int i = 0; i < arr2d.length; i++)
            sum += arr2d[row][i];
        return sum /= arr2d[0].length;

    }

    public static double getArrayAverage(int[][] arr2d)
    {
        int count = 0;
        double sum = 0;
        for(int[] row : arr2d)
        {
            for(int num : row)
            {
                sum += num;
                count++;
            }
        }
        return sum / count;
    }

    public static double getArrayMax(int[][] arr2d)
    {
        int max = arr2d[0][0];
        for(int[] row : arr2d)
        {
            for(int num : row)
            {
                max = max < num ? num : max;
            }
        }
        return max;
    }

    public static double getArrayMin(int[][] arr2d)
    {
        int min = arr2d[0][0];
        for(int[] row : arr2d)
        {
            for(int num : row)
            {
                min = min > num ? num : min;
            }
        }
        return min;
    }

    public static void zerosOnDiagnol(int[][] arr2d)
    {
        for(int i = 0; i < arr2d.length; i++)
            arr2d[i][i] = 0;
    }

    public static int[][] getTransposedMatrix(int[][] arr2d)
    {
        int[][] newArr2d = new int[arr2d[0].length][arr2d.length];
        for (int i = 0; i < arr2d.length; i++)
        {
            for (int j = 0; j < arr2d[i].length; j++)
            {
                newArr2d[j][i] = arr2d[i][j];
            }
            
        }

        return newArr2d;
    }
}

