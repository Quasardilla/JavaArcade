package Sorting;

import java.util.Arrays;

public class Sorter {


    public static void selectionSort(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            int min = i;

            for(int j = i + 1; j < a.length; j++) {
                if(a[j] < a[min])
                    min = j;
            }

            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;

            // System.out.println(a[i]);
        }
    }
    public static void betterselectionSort(int[] a) 
    {
        for(int i = 0; i < (a.length)/2; i++) 
        {
            int min = i;
            int max = i;

            for(int j = i + 1; j < a.length-i; j++) 
            {
                if(a[j] < a[min])
                    min = j;
                if(a[j] > a[max])
                    max = j;
            }

            //after decades of debugging, this one line fixes all the issues T_T
            if (i==max) max = min;

            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
            
            temp = a[a.length-i-1];
            a[a.length-i-1] = a[max];
            a[max] = temp;
        }
    }

    public static void insertionSort(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < i; j++) {
                
            }
        }
    }

    public static void bogoSort(int[] a)
    {
        boolean sorted = false;
        sort_loop:
        while (!sorted)
        {

            int index1 = (int) Math.random()*a.length;
            int index2 = (int) Math.random()*a.length;

            int temp = a[index1];
            a[index1] = a[index2];
            a[index2] = temp;

            //check if sorted
            for (int i = 1; i < a.length; i++)
            {
                if (a[i] <= a[i-1])
                {
                    sorted = false;
                    continue sort_loop;
                }
            }
            sorted = true;
        }
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{1, 90, 34, 7, 6, 3, 2, 2, 8, 87};
        // // selectionSort(arr);
        // System.out.println("unsorted: "+Arrays.toString(arr));
        
        // arr = mergeSort(arr);

        // System.out.println("sorted: "+Arrays.toString(arr));

        // int size = 100_000;
        // int[] big_arr = new int[size];
        // for (int i = 0; i < size; i++)
        // {
        //     big_arr[i] = (int) (Math.random()*size);
        //     // System.out.println(big_arr[i]);
        // }

        // long nano = System.nanoTime();
        // betterselectionSort(big_arr);
        // selectionSort(big_arr);

        // for (int i: big_arr)
        //     System.out.println(i);
    
        // System.out.println((System.nanoTime()-nano)/1_000_000_000.0);


        int[] first = {};
        int[] second= {1, 3};
        int[] a = new int[first.length+second.length];
        a = merge(first, second);
        System.out.println(Arrays.toString(a));
    }

    private static int[] mergeSort(int[] arr) 
    {
        //base case
        if (arr.length <= 1)
        {
            return arr;
        }

        //return statement
        //merge the sorted left half plus the sorted right half
        int[] leftArr = new int[arr.length/2];
        int[] rightArr = new int[arr.length/2];
        
        for (int i = 0; i < arr.length/2; i++)
        {
            leftArr[i] = arr[i];
            rightArr[i] = arr[(arr.length/2)+i];
        }
        
        return merge(mergeSort(leftArr), mergeSort(rightArr));
    }


    private static int[] merge(int[] first, int[] second) 
    {
        int[] a = new int[first.length+second.length];
        
        for (int iFirst = 0, iSecond = 0; iFirst+iSecond<a.length;)
        {
            //if ((iFirst < first.length && iSecond < second.length) && iFirst < first.length && first[iFirst] < second[iSecond])
            //else if ((iFirst < first.length && iSecond < second.length) && iFirst < first.length && first[iFirst] < second[iSecond])
            

            /*
             * do first if:
             * second out of bounds and first in bounds
             * second in bounds and first in bounds and first less than second
             * 
             * both need first in bounds
             * second in bounds cancels out 
             * 
             * pseudocode
             * if (first in bounds && (second out of bounds || first less than second))
             * 
             * short circuits the or so that if second is out of bounds, it doesnt check second
             * 
             * real code
             * if (iFirst < first.length && (iSecond >= second.length || first[iFirst] < second[iSecond]))
             * 
             * do second if:
             * first out of bounds and second in bounds
             * second in bounds and first in bounds and second less than or equal to first
             * 
             * both need second in bounds
             * first in bounds cancels out 
             * 
             * pseudocode
             * if (second in bounds && (first out of bounds || second less than or equal to first))
             * 
             * real code
             * 
             * else if (iSecond < second.length && (iFirst >= first.length || second[iSecond] <= first[iFirst]))
             * 
             * 
             * doesnt work T_T
             */

            // System.out.println("iFirst: "+iFirst+", iSecond: "+iSecond);

            // if (iFirst >= first.length || first[iFirst] >= second[iSecond])
            // if (iFirst < first.length || ((iFirst < first.length && iSecond < second.length) && first[iFirst] < second[iSecond]))
            // if ((iFirst < first.length && iSecond < second.length) && iFirst < first.length && first[iFirst] < second[iSecond])            
            if (iFirst < first.length && (iSecond >= second.length || first[iFirst] < second[iSecond]))
            {
                System.out.println("iFirst++, "+iFirst);
                a[iFirst+iSecond] = first[iFirst];
                iFirst++;
            }
            // else if (iSecond >= second.length || first[iFirst] < second[iSecond])
            // else if (iSecond < second.length || ((iFirst < first.length && iSecond < second.length) && first[iFirst] >= second[iSecond]))
            // else if ((iFirst < first.length && iSecond < second.length) && iFirst < first.length && first[iFirst] < second[iSecond])
            else if (iSecond < second.length && (iFirst >= first.length || second[iSecond] <= first[iFirst]))
            {
                System.out.println("iSecond++, "+iSecond);
                a[iFirst+iSecond] = second[iSecond];
                iSecond++;
            }
        }

        return a;

    }   


    // private static int[] merge(int[] first, int[] second) {
    //     int[] a = new int[first.length + second.length];
        
    //     // for (int iFirst = 0, iSecond = 0; iFirst+iSecond<a.length;)

        //iFirst < first.length || ((iFirst < first.length && iSecond < second.length) && first[iFirst] < second[iSecond])
        //iSecond < second.length || ((iFirst < first.length && iSecond < second.length) && !(first[iFirst] < second[iSecond]))
    //     int iFirst = 0, iSecond = 0;
    //     while (iFirst < first.length && iSecond < second.length) {
    //         if (first[iFirst] < second[iSecond]) {
    //             a[iFirst+iSecond] = first[iFirst];
    //             iFirst++;
    //         } else {
    //             a[iFirst+iSecond] = second[iSecond];
    //             iSecond++;
    //         }
    //     }
        
    //     while (iFirst < first.length) {
    //         a[iFirst+iSecond] = first[iFirst];
    //         iFirst++;
    //     }
        
    //     while (iSecond < second.length) {
    //         a[iFirst+iSecond] = second[iSecond];
    //         iSecond++;
    //     }
        
    //     return a;
    // }
    
    
}
