package com.algo;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {

//        int[] num = { 2, 20, 3, 7, 9, 1, 17, 18, 0, 4 };
//        int[] arr = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50,50, 10, 90, 30, 70, 40, 80, 60, 20};
        int[] arr = {51, 46, 20, 18, 65, 2, 20, 3, 7, 9, 1, 17, 18, 0, 4,97, 82, 30, 77, 50,50, 10, 90, 30, 70, 40, 80, 60, 20};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static int divide(int[] a, int start, int end) {
        int base = a[start], tmp;
        while (start < end) {
            if (start < end && base <= a[end]) end--;
            tmp = a[start];
            a[start] = a[end];
            a[end] = tmp;
            if (start < end && base >= a[start]) start++;
            tmp = a[start];
            a[start] = a[end];
            a[end] = tmp;
        }
        return start;
    }

    static void sort(int[] a, int start, int end) {
        if (start < end) {
            int part = divide(a, start, end);
            sort(a, start, part-1);
            sort(a, part+1, end);
        }
    }

}
