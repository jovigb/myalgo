package com.algo;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {

//        int[] arr = { 2, 20, 3, 2,7, 9, 1, 17, 0, 1,4, 18 };
        int[] arr = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50,50, 10, 90, 30, 70, 40, 80, 60, 20};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bubbleSort(int[] a) {
        for (int i=0; i<a.length; i++) {
            for (int j=a.length-1; j>i; j--) {
                if (a[i] > a[j]) {
                    int tmp = a[j];
                    a[j] = a[i];
                    a[i] = tmp;
                }
            }
        }
    }
}
