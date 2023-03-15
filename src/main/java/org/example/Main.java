package org.example;

import java.util.Random;
import java.util.Scanner;

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
    public  static int CountIterationsSequentially(int number ,long[] array)
    {
        int count =0;
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] == number)
            {
                count++;
            }
        }
        return  count;
    }

    public static void main(String[] args) {

        long[] array = new long[10000000];
        array = createRandomArray(10000000);
        Scanner s = new Scanner(System.in);
        int n;
        System.out.print("\n Enter a number between 1 and 100000 to find the iterations of it:");
        n = s.nextInt();
        long start1 = System.currentTimeMillis();
        System.out.print("the number of iterations is:"+CountIterationsSequentially(n,array) +"\n" );
        long end1 = System.currentTimeMillis();
        System.out.println("implementation Time in milli seconds: "+ (end1-start1));

    }
}