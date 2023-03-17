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
        }
    }

    public static void insertionSort(int[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < i; j++) {
                
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 90, 34, 7, 6, 3, 2, 2, 8, 87};
        // selectionSort(arr);
        insertionSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
