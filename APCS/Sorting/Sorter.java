package Sorting;

import java.util.Arrays;

import Searching.FileUtility;

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
            for(int j = i; j > 0 && a[j-1] > a[j]; j--) {
                int temp = a[j-1];
                a[j-1] = a[j];
                a[j] = temp;
            }
        }
    }

    public static void insertionSort(String[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j > 0 && a[j-1].toLowerCase().compareTo(a[j].toLowerCase()) > 0; j--) {
                String temp = a[j-1];
                a[j-1] = a[j];
                a[j] = temp;
            }
        }
    }

    private static int[] merge(int[] first, int[] second) 
    {        
        int[] a = new int[first.length + second.length];
        for (int iFirst = 0, iSecond = 0; iFirst+iSecond<a.length;)
        {
            if (iFirst < first.length && (iSecond >= second.length || first[iFirst] < second[iSecond]))
            {
                a[iFirst+iSecond] = first[iFirst];
                iFirst++;
            }
            else if (iSecond < second.length && (iFirst >= first.length || second[iSecond] <= first[iFirst]))
            {
                a[iFirst+iSecond] = second[iSecond];
                iSecond++;
            }
        }

        return a;

    } 

    private static int[] mergeSort(int[] arr) 
    {
        if (arr.length <= 1)
            return arr;

        int[] leftArr = new int[arr.length/2];
        int[] rightArr = new int[arr.length/2];
        
        for (int i = 0; i < arr.length/2; i++)
        {
            leftArr[i] = arr[i];
            rightArr[i] = arr[(arr.length/2)+i];
        }
        
        return merge(mergeSort(leftArr), mergeSort(rightArr));
    }

    public static int[] mergeSortB(int[] arr) {
        if(arr.length <= 1)
            return arr;
                    
        int[] first = new int[arr.length / 2];
        int[] second = new int[arr.length - first.length];
        
        System.arraycopy(arr, 0, first, 0, first.length);
        System.arraycopy(arr, first.length, second, 0, second.length);

        return merge(mergeSort(first), mergeSort(second));
    }

    // public static void mergeSortC(int[] a) {
    //     if(a.length <= 1) return;
    //     int[] first = new int[a.length / 2];
    //     int[] second = new int[a.length - first.length];

    //     System.arraycopy(a, 0, first, 0, first.length);
    //     System.arraycopy(a, first.length, second, 0, second.length);

    //     mergeSort(first);
    //     mergeSort(second);

    //     merge(a, first, second);
    // }

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
        // String[] starr;
        // try { starr = FileUtility.readFile("wordlist.txt");  } catch (Exception e) { starr = new String[]{"uh oh"};}
        
        // System.out.println("unsorted: "+Arrays.toString(arr));

        // selectionSort(arr);
        // insertionSort(arr);
        // insertionSort(starr);
        // arr = mergeSort(arr);
        // arr = mergeSortB(arr);
        // mergeSortC(arr);
        
        // System.out.println("sorted: "+Arrays.toString(arr));
        
        int size = 2_000_000_000;
        int[] big_arr = new int[size];
        for (int i = 0; i < size; i++)
        {
            big_arr[i] = (int) (Math.random()*size);
            // System.out.println(big_arr[i]);
        }

        long nano = System.nanoTime();
        // betterselectionSort(big_arr);
        // selectionSort(big_arr);x

        big_arr = mergeSortB(big_arr);

        // for (int i: big_arr)
        //     System.out.println(i);
    
        System.out.println((System.nanoTime()-nano)/1_000_000_000.0);
        // System.out.println(Arrays.toString(big_arr));

        // int[] first = {7, 9, 12};
        // int[] second= {1, 3, 27};
        // int[] a = merge(first, second);
        // System.out.println(Arrays.toString(a));
        
    }

}
