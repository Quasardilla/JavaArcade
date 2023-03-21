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

    public static void main(String[] args) {
        int[] arr = new int[]{1, 90, 34, 7, 6, 3, 2, 2, 8, 87};
        String[] starr;
        try { starr = FileUtility.readFile("wordlist.txt");  } catch (Exception e) { starr = new String[]{"uh oh"};}
        // selectionSort(arr);
        insertionSort(arr);
        insertionSort(starr);
        
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(starr));
    }
}
