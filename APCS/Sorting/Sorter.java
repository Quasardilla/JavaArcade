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

    public static int[] merge(int[] a, int[] b, int[] c) {
        int countb = 0;
        int countc = 0;

        for(int i = 0; i < (b.length + c.length); i++) {
            if(countc < c.length && (countb >= b.length || b[countb] > c[countc])) {
                a[i] = c[countc];
                countc++;
            }
            if(countb < b.length && (countc >= c.length || b[countb] < c[countc])) {
                a[i] = b[countb];
                countb++;
            }
        }

        return a;
    }

    public static int[] mergeSort(int[] arr) {
        int[] sorted = new int[arr.length];

        if(arr.length < 2)
            return arr;
                    
        int[] first = new int[arr.length / 2];
        System.arraycopy(arr, 0, first, 0, first.length);
        mergeSort(first);
        
        int[] second = new int[arr.length - first.length];
        System.arraycopy(arr, first.length, second, 0, second.length);
        mergeSort(second);
        
        System.out.println(Arrays.toString(first));
        System.out.println(Arrays.toString(second));
        
        merge(sorted, first, second);

        return sorted;
    }

    public static void mergeSortB(int[] a) {
        if(a.length <= 1) return;
        int[] first = new int[a.length / 2];
        int[] second = new int[a.length - first.length];

        System.arraycopy(a, 0, first, 0, first.length);
        System.arraycopy(a, first.length, second, 0, second.length);

        mergeSort(first);
        mergeSort(second);

        merge(a, first, second);
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{1, 90, 34, 7, 6, 3, 2, 2, 8, 87};
        // String[] starr;
        // try { starr = FileUtility.readFile("wordlist.txt");  } catch (Exception e) { starr = new String[]{"uh oh"};}
        // // selectionSort(arr);
        // mergeSortB(arr);
        // insertionSort(arr);
        // insertionSort(starr);

        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(starr));

        int[] first = {1, 12, 2};
        int[] second = {2, 2, 8};
        int[] a = new int[first.length + second.length];
        merge(a, first, second);
        System.out.println(Arrays.toString(a));

    }
}
