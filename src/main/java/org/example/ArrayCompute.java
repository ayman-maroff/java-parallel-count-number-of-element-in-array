package org.example;

import java.util.concurrent.RecursiveTask;

public class ArrayCompute extends RecursiveTask<Integer> {
    Long [] array;
    int start;
    int end;
    int threshold =3000000;
    int number;
    public ArrayCompute(Long[] array, int start, int end,int number) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.number =number;
    }

    protected Integer compute() {
        if (end - start < threshold ) {
            return computeDirectly();
        } else {
            int middle = (end + start) / 2;

            ArrayCompute subTask1 = new ArrayCompute(array, start, middle,number);
            ArrayCompute subTask2 = new ArrayCompute(array, middle, end,number);

            invokeAll(subTask1, subTask2);


            return subTask1.join() + subTask2.join();
        }
    }

    protected Integer computeDirectly() {
        Integer count = 0;

        for (int i = start; i < end; i++) {
            if (array[i] == number) {
                count++;
            }
        }

        return count;
    }
}