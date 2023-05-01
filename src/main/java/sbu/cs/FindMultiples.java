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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindMultiples
{

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */

    public ArrayList<Integer> dividends = new ArrayList<Integer>();
    public static int n;
    int temp = 0;
    public synchronized void increment(){
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



    public int getSum() {
        int sum = 0;
        Set<Integer> set = new HashSet<>(dividends);
        dividends.clear();
        dividends.addAll(set);

        for (int i : dividends){
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        FindMultiples obj = new FindMultiples();
        obj.divisibleBy3.start();
        obj.divisibleBy5.start();
        obj.divisibleBy7.start();
        try {
            obj.divisibleBy3.join();
            obj.divisibleBy5.join();
            obj.divisibleBy7.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(obj.getSum());

    }

}
