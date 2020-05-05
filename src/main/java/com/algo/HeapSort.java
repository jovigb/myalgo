package com.algo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = { 50, 10, 90, 30, 70, 40, 80, 60, 20 };
//        int[] arr = {51, 46, 20, 18, 65, 2, 20, 3, 7, 9, 1, 17, 18, 0, 4,97, 82, 30, 77, 50,50, 10, 90, 30, 70, 40, 80, 60, 20};
        ArrayList al = new ArrayList();
        for(int i=0; i<10; i++) {
            al.add(i);
        }
        Collections.shuffle(al);
        System.out.println(Arrays.toString(al.toArray()));
        int[] arr = al.stream().mapToInt(i -> (int)i).toArray();
        long start = System.currentTimeMillis();
        // 堆排序
        sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
        System.out.println(Arrays.toString(arr));
    }

    static void adjust(int[] a,  int start, int end){
        int tmp = a[start];
        for(int j =2*start+1; j<end; j=2*start+1) {
            if (j+1 < end && a[j] < a[j+1])
                j++;
            if (tmp > a[j]) break;
            a[start] = a[j];
            start = j;
        }
        a[start] = tmp;
    }

    static void sort(int[] a) {
        for(int i=a.length/2-1; i>=0; i--)
            adjust(a, i, a.length);
        for(int i=a.length-1; i>0; i--) {
            int tmp = a[i];
            a[i] = a[0];
            a[0] = tmp;
            adjust(a, 0, i);
        }
    }
}
