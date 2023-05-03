package sbu.cs;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class FindMultiples
{

    private static final int THREAD_POOL_SIZE = 4; // Set the thread pool size as needed


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) {
        int sum = 0;

            Set<Integer> results = new HashSet<>(); // store unique multiples

            // Create a thread pool to execute tasks in parallel
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            // Divide the range [1, n] into smaller chunks to be processed by different threads
            int chunkSize = (n + THREAD_POOL_SIZE - 1) / THREAD_POOL_SIZE;
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                int start = i * chunkSize + 1;
                int end = Math.min(start + chunkSize - 1, n);
                executor.execute(new MultiplesTask(start, end, results));
            }

            // Shutdown the thread pool
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int multiple : results) {
                sum += multiple;
            }

            return sum;
    }

    private static class MultiplesTask implements Runnable {
        private final int start;
        private final int end;
        private final Set<Integer> results;

        public MultiplesTask(int start, int end, Set<Integer> results) {
            this.start = start;
            this.end = end;
            this.results = results;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                    results.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {

        FindMultiples findMultiples = new FindMultiples();
        int n = 10;
        int sum = findMultiples.getSum(n);
        System.out.println("Sum of multiples in the range [1, " + n + "] = " + sum);

    }
}
