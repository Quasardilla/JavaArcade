package Exploring2DArrays;

import java.io.Console;
import java.util.Arrays;

public class Exploring2DArrays {
    public static void main(String[] args) {
        int[][] nums = new int[3][5];
        randomize2DArray(nums);
        nums[2][2] = 42;
        // print2DArrayForEach(nums);

        int[][] numArr = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        int[][] temp1 = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        String[][] strArr = {{"B", "N", "T"}, {"Z", "A", "J"}};

        System.out.println("-------- Average --------");
        System.out.println(getColumnAverage(nums, 2));
        
        System.out.println("-------- Default Array --------");
        print2DArray(numArr);
        System.out.println("-------- Transposed Array --------");
        print2DArray(getTransposedMatrix(numArr));
        System.out.println("-------- Reversed Array --------");
        reverse2dArray(numArr);
        print2DArray(numArr);
        System.out.println("-------- Reflected Array --------");
        reflect2dArray(numArr);
        print2DArray(numArr);
        System.out.println("-------- First Alphabetically --------");
        System.out.println(firstInAlphabet2d(strArr));
        System.out.println("-------- Scaled Array --------");
        print2DArray(scaleColumnBy(numArr, 0, 50));
        
    }

    public static void print2DArray(int[][] arr2D)
    {
        for(int i = 0; i < arr2D.length; i++)
        {
            for(int j = 0; j < arr2D[i].length; j++)
            System.out.print(arr2D[i][j]+" ");

            System.out.println("");
        }
    }

    public static void print2DArray(double[][] arr2D)
    {
        for(int i = 0; i < arr2D.length; i++)
        {
            for(int j = 0; j < arr2D[i].length; j++)
            System.out.print(arr2D[i][j]+" ");

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

    public static void reverse2dArray(int[][] arr)
    {
        for(int[] row: arr)
            reverseArray(row);
    }

    public static void reflect2dArray(int[][] arr)
    {
        for(int[] row: arr)
            reflectArrayOverMiddle(row);
    }

    public static String firstInAlphabet2d(String[][] arr)
    {
        String[] temp = new String[arr.length];
        int count = 0;
        for(String[] row : arr)
        {
            temp[count] = firstInAlphabet(row);
            count++;
        }
        return firstInAlphabet(temp);
    }

    public static double[][] scaleColumnBy(int[][] arr, int col, double percent)
    {
        double[][] scaled = new double[arr.length][arr[0].length];
        percent /= 100;

        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr[i].length; j++)
            {
                if(j == col)
                    scaled[i][j] = arr[i][j] * percent;
                else
                    scaled[i][j] = arr[i][j];

            }

        return scaled;
    } 

    //Single Array Methods

    public static void reverseArray(int[] arr)
    {
        int temp;
        for(int i = 0; i < arr.length/2; i++)
        {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
    
    public static void reflectArrayOverMiddle(int[] arr)
    {
        if(arr.length % 2 == 0)
        {
            for(int i = 0; i < (arr.length / 2); i++)
                arr[(arr.length / 2) + i] = arr[(arr.length / 2) - i - 1];
        }
        else
        {
            for(int i = 0; i < (arr.length / 2); i++)
                arr[(arr.length / 2) + i + 1] = arr[(arr.length / 2) - i - 1];
        }
    }

    public static String firstInAlphabet(String[] arr)
    {
        String first = arr[0];
            for(String str : arr)
                if(first.compareTo(str) > 0)
                    first = str;
        return first;
    }

}

