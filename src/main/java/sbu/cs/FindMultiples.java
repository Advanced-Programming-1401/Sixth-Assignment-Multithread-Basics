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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindMultiples
{

    // TODO create the required multithreading class/classes using your preferred method.
    public static class Mul implements Runnable{
        public ArrayList<Integer> muls = new ArrayList<>();
        public int first;
        public int second;

        public Mul(int first,int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            for (int i=first;i<=second;i++){
                if (i % first == 0){
                    muls.add(i);
                }
            }
        }
    }

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list)
    {

        ArrayList<Integer> newList = new ArrayList<>();
        for (int element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static int getSum(int n) {
        int sum = 0;
        Mul mul3 = new Mul(3,n);
        Thread t1 = new Thread(mul3);
        t1.start();
        Mul mul5 = new Mul(5,n);
        Thread t2 = new Thread(mul5);
        t2.start();
        Mul mul7 = new Mul(7,n);
        Thread t3 = new Thread(mul7);
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }


        ArrayList<Integer> allMuls = new ArrayList<Integer>();
        allMuls.addAll(mul3.muls);
        allMuls.addAll(mul5.muls);
        allMuls.addAll(mul7.muls);

        allMuls = removeDuplicates(allMuls);

        for (int i=0;i<allMuls.size();i++){
            sum+=allMuls.get(i);
        }

        return sum;
    }

    public static void main(String[] args) {
    }
}
