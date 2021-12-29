package com.algo;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

//        int[] arr = { 2, 20, 3, 2,7, 9, 1, 17, 0, 1,4, 18 };
        int[] arr = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50,50, 10, 90, 30, 70, 40, 80, 60, 20};
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
        System.out.println(Arrays.toString(arr));
    }

    static void merge(int[] a, int start, int mid, int end, int[] t) {
        int i=start, j=mid+1, k=0;
        while (i <= mid && j <= end) t[k++] = (a[i] < a[j]) ? a[i++] : a[j++];
        while ( i <= mid) t[k++] = a[i++];
        while ( j <= end) t[k++] = a[j++];
        for (i=0; i<k; i++) a[start+i] = t[i];
    }

    static void sort(int[] a, int start, int end, int[] t) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            sort(a, start, mid, t);
            sort(a, mid+1, end, t);
            merge(a, start, mid, end, t);
        }
    }

}
