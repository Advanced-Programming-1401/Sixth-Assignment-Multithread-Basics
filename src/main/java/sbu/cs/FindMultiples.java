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
public class FindMultiples
{

    public static ArrayList<Integer> dividends = new ArrayList<>();
    //public static int temp = 0;
    public static synchronized void increment(int temp){
        dividends.add(temp);
    }
    public static class thread implements Runnable{

        public int divisor;
        public int n;

        public thread(int n , int divisor){
            this.n=n;
            this.divisor=divisor;
        }

        @Override
        public void run() {

            for (int i=1 ; i<=n ; i++){
                if (i%divisor==0){
                    //temp=i;
                    increment(i);
                }
            }
        }
    }
    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) {
        int sum = 0;
        Thread t1 = new Thread(new thread(n,3));
        Thread t2 = new Thread(new thread(n,7));
        Thread t3 = new Thread(new thread(n,5));
        // TODO
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){

        }
        Set<Integer> set = new HashSet<>(dividends);
        dividends.clear();
        dividends.addAll(set);

        for (int i : dividends){
            sum+=i;
        }

        return sum;
    }

    public static void main(String[] args) {

        FindMultiples obj = new FindMultiples();
        Scanner input = new Scanner(System.in);
        System.out.println(obj.getSum(input.nextInt()));
    }
}