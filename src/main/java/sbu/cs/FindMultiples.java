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

import java.util.*;

class divisibleBy3 extends Thread{


    private long n;
    public divisibleBy3(long n){
        this.n=n;
    }
    @Override
    public void run() {
        for (long i=1 ; i<=n ; i++){
            if (i%3==0L){
                FindMultiples.temp=i;
                FindMultiples.increment();
            }
        }
    }

}

class divisibleBy5 extends Thread{


    private long n;
    public divisibleBy5(long n){
        this.n=n;
    }
    @Override
    public void run() {
        for (long i=1 ; i<=n ; i++){
            if (i%5==0L){
                FindMultiples.temp=i;
                FindMultiples.increment();
            }
        }
    }

}

class divisibleBy7 extends Thread{

    private long n;
    public divisibleBy7(long n){
        this.n=n;
    }
    @Override
    public void run() {
        for (long i=1 ; i<=n ; i++){
            if (i%7==0L){
                FindMultiples.temp=i;
                FindMultiples.increment();
            }
        }
    }

}

public class FindMultiples
{

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */

    public static synchronized void increment(){
        FindMultiples.dividends.add(FindMultiples.temp);
    }
    public static ArrayList<Long> dividends = new ArrayList<Long>();
    //public static int n;
    public static long temp = 0;
    /*public synchronized void increment(){
        dividends.add(temp);
    }
    Thread divisibleBy3  = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i=1 ; i<=n ; i++){
                if (i%3==0){
                    temp=i;
                    increment();
                }
            }
        }
    });

    Thread divisibleBy5  = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i=1 ; i<=n ; i++){
                if (i%5==0){
                    temp=i;
                    increment();
                }
            }
        }
    });

    Thread divisibleBy7  = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i=1 ; i<=n ; i++){
                if (i%7==0){
                    temp=i;
                    increment();
                }
            }
        }
    });
*/


    public Long getSum(long n) {
        Long sum = 0L;
        divisibleBy3 obj3 = new divisibleBy3(n);
        divisibleBy5 obj5 = new divisibleBy5(n);
        divisibleBy7 obj7 = new divisibleBy7(n);
        obj3.start();
        obj5.start();
        obj7.start();
        try {
            obj3.join();
            obj5.join();
            obj7.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Set<Long> set = new HashSet<>(dividends);
        dividends.clear();
        dividends.addAll(set);

        for (long i : dividends){
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {

        FindMultiples obj = new FindMultiples();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        obj.getSum(n);
    }

}
