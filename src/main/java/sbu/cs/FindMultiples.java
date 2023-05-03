package sbu.cs;

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

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FindMultiples
{
    // TODO create the required multithreading class/classes using your preferred method.
    public static class DoSum extends Thread {
        int sumOfThread = 0;
        int bound;
        int divisibleTo;
        static Lock sumLock = new ReentrantLock();
        public DoSum(int bound, int divisibleTo) {
            this.bound = bound;
            this.divisibleTo = divisibleTo;
        }

        public void run() {
            for (int i = divisibleTo; i <= bound; i += divisibleTo) {
                this.sumOfThread += i;
            }

            int sign = 1;
            if ((this.divisibleTo == 15) || (this.divisibleTo == 21) || (this.divisibleTo == 35)) {
                sign = -1;
            }

            sumLock.lock();
            sum += sign * this.sumOfThread;
            sumLock.unlock();
        }
    }

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public static int sum = 0;
    public static int getSum(int n) {
        ArrayList<Thread> threads = new ArrayList<>();
        threads.add(0, new DoSum(n, 3));
        threads.add(1, new DoSum(n, 5));
        threads.add(2, new DoSum(n, 7));
        threads.add(3, new DoSum(n, 15));
        threads.add(4, new DoSum(n, 21));
        threads.add(5, new DoSum(n, 35));
        threads.add(6, new DoSum(n, 105));

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        getSum(1000);
    }
}
