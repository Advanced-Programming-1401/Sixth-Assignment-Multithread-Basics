package sbu.cs;

import java.util.ArrayList;
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

    // TODO create the required multithreading class/classes using your preferred method.

    public static class Division implements Runnable {

        private int divideTo;
        private int range;

        public Division(int divideTo, int range) {
            this.divideTo = divideTo;
            this.range = range;
        }

        public void run(){
            boolean addChecker = false;

            if(divideTo == 3 || divideTo == 5 || divideTo == 7 || divideTo == 105)
                addChecker = true;

            int temp = 0;
            for (int i = 1; i <= this.range; i++){
                if(i % divideTo == 0){
                    temp += i;
                }
            }

            if(addChecker){
                addToSum(temp);
            }
            else {
                reduceFromSum(temp);
            }

        }
    }

    private static int summ;

    public static synchronized void addToSum(int value){
        summ += value;
    }

    public static synchronized void reduceFromSum(int value){
        summ -= value;
    }
    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) {
        int sum = 0;
         FindMultiples findMultiples = new FindMultiples();
         ArrayList<Division> tasks = new ArrayList<>();

         tasks.add(new Division(3, n));
        tasks.add(new Division(5, n));
        tasks.add(new Division(7, n));
        tasks.add(new Division(15, n));
        tasks.add(new Division(21, n));
        tasks.add(new Division(35, n));
        tasks.add(new Division(105, n));

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (Division task: tasks){
            threadPool.execute(task);
        }

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(100000, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        sum = summ;
        return sum;
    }

    public static void main(String[] args) {
    }
}
