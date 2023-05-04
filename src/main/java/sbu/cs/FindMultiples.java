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

public class FindMultiples
{
    boolean workDone3=false;
    boolean workDone5=false;
    boolean workDone7=false;
    ArrayList<Integer>total=new ArrayList<>();
    ArrayList<Integer> counter3 = new ArrayList<>();
    ArrayList<Integer> counter5 = new ArrayList<>();
    ArrayList<Integer> counter7 = new ArrayList<>();
public class Group3 implements Runnable {

    public int n;
    public Group3(int n){
        this.n=n;

    }

    @Override
    public void run(){

        for(int i=1;i<=n;i++){
            if(i%3==0){
                counter3.add(i);
            }

        }
        workDone3=true;


    }
}
    public class Group5 implements Runnable {
        public int n;
        public Group5(int n){
            this.n=n;
        }

        @Override
        public void run(){

            for(int i=1;i<=n;i++){
                if(i%5==0){
                    counter5.add(i);
                }

            }
            workDone5=true;


        }
    }
    public class Group7 implements Runnable {
        public int n;
        public Group7(int n){
            this.n=n;
        }

        @Override
        public void run(){

            for(int i=1;i<=n;i++){
                if(i%7==0){
                    counter7.add(i);
                }

            }
            workDone7=true;


        }
    }

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) {
        int sum = 0;
        Thread thread1=new Thread(new Group3(n));
        Thread thread2=new Thread(new Group5(n));
        Thread thread3=new Thread(new Group7(n));
        thread1.start();
        thread2.start();
        thread3.start();
        if(workDone3 && workDone5 && workDone7){
            for (int int1:counter3){
                total.add(int1);
                sum+=int1;
            }
            for(int int1:counter5){
                boolean unique=true;
                for (int int2:total){
                    if(int1==int2){
                        unique=false;
                    }
                }
                if(unique){
                    total.add(int1);
                    sum+=int1;
                }
            }
            for(int int1:counter7){
                boolean unique=true;
                for (int int2:total){
                    if(int1==int2){
                        unique=false;
                    }
                }
                if(unique){
                    total.add(int1);
                    sum+=int1;
                }
            }

        }
        for (int i:total){
            System.out.println(i);
        }

        // TODO

        return sum;
    }

    public static void main(String[] args) {
        FindMultiples findMultiples=new FindMultiples();
        findMultiples.getSum(9);


    }
}
