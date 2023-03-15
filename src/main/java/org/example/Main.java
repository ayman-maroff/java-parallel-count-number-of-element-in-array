package org.example;

import java.util.Random;

public class Main {
    //generate random array function
    public static long[] createRandomArray(long n) {
        Random rd = new Random();
        long[] array = new long[(int) n];
        int min = 1;
        int max = 100000;

        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(max-min+1) + min;
            System.out.print(array[i] +" ");
        }
        return array;
    }

    public static void main(String[] args) {

        long[] array = new long[10000000];
        array = createRandomArray(10000000);
    }
}