package sbu.cs;

import java.util.ArrayList;

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

    // TODO create the required multithreading class/classes using your preferred method.

    private static int sum;
    private static ArrayList<Integer> numbers;


    public static class Divide implements Runnable {
        private int n;
        private int d;

        public Divide(int n, int d) {
            this.n = n;
            this.d = d;
        }

        public static synchronized void sum(int number) {
            if (!numbers.contains(number)) {
                sum += number;
                numbers.add(number);
            }
        }

        @Override
        public void run() {
            for(int i = d; i <= n; i += d) {
                sum(i);
            }
        }
    }

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */


    public int getSum(int n) {
        numbers = new ArrayList<Integer>();
        sum = 0;

        Thread divisibleBy_3 = new Thread(new Divide(n,3));
        Thread divisibleBy_5 = new Thread(new Divide(n,5));
        Thread divisibleBy_7 = new Thread(new Divide(n,7));
        divisibleBy_3.start();
        divisibleBy_5.start();
        divisibleBy_7.start();

        try {
            divisibleBy_3.join();
            divisibleBy_5.join();
            divisibleBy_7.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return sum;
    }

    public static void main(String[] args) {
    }
}
