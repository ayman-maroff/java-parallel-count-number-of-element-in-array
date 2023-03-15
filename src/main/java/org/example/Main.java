package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class Main {
    //generate random array function
    public static Long[] createRandomArray(long n) {
        Random rd = new Random();
        Long[] array = new Long[(int) n];
        int min = 1;
        int max = 100000;

        for (int i = 0; i < array.length; i++) {
            array[i] = Long.valueOf(rd.nextInt(max-min+1) + min);
            System.out.print(array[i] +" ");
        }
        return array;
    }
    public  static int CountIterationsSequentially(int number ,Long[] array)
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
    public  static int CountIterationsSequentiallyStream(int number ,Long[] array)
    {

     Stream<Long> stream =  Arrays.stream(array);
    var count =  stream.filter(num ->num ==number).count();
        return (int) count;
    }
    public  static int CountIterationsParallelStream(int number ,Long[] array)
    {

        Stream<Long> stream =  Arrays.stream(array);
        var count =  stream.parallel().filter(num ->num ==number).count();
        return (int) count;
    }
    public static void main(String[] args) {

        Long[] array = new Long[100000000];
        array = createRandomArray(100000000);
        Scanner s = new Scanner(System.in);
        int n;
        System.out.print("\n Enter a number between 1 and 100000 to find the iterations of it:");
        n = s.nextInt();
        long start1 = System.currentTimeMillis();
        System.out.print("the number of iterations(sequentially) is:"+CountIterationsSequentially(n,array) +"\n" );
        long end1 = System.currentTimeMillis();
        System.out.println("implementation Time in milli seconds: "+ (end1-start1));

        ArrayCompute mainTask = new ArrayCompute(array, 0, 100000000,n);
        ForkJoinPool pool = new ForkJoinPool();
        long start2 = System.currentTimeMillis();
        Integer NumberCount = pool.invoke(mainTask);
        long end2 = System.currentTimeMillis();
        System.out.println("the number of iterations (parallel) is: " + NumberCount);
        System.out.println("implementation Time in milli seconds: "+ (end2-start2));

        long start3 = System.currentTimeMillis();
        System.out.print("the number of iterations(sequentially stream) is:"+CountIterationsSequentiallyStream(n,array) +"\n" );
        long end3 = System.currentTimeMillis();
        System.out.println("implementation Time in milli seconds: "+ (end3-start3));

        long start4 = System.currentTimeMillis();
        System.out.print("the number of iterations(parallel stream) is:"+CountIterationsParallelStream(n,array) +"\n" );
        long end4 = System.currentTimeMillis();
        System.out.println("implementation Time in milli seconds: "+ (end4-start4));
    }
}