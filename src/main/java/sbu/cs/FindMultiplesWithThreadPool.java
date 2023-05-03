package sbu.cs;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FindMultiplesWithThreadPool {
    public static class DoSum implements Runnable {
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

    public static int sum = 0;
    public static int getSum(int n) {
        ArrayList<DoSum> tasks = new ArrayList<>();
        tasks.add(0, new DoSum(n, 3));
        tasks.add(0, new DoSum(n, 5));
        tasks.add(0, new DoSum(n, 7));
        tasks.add(0, new DoSum(n, 15));
        tasks.add(0, new DoSum(n, 21));
        tasks.add(0, new DoSum(n, 35));
        tasks.add(0, new DoSum(n, 105));

        ExecutorService threadPool = Executors.newFixedThreadPool(7);
        for (DoSum task : tasks) {
            threadPool.execute(task);
        }
        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getSum(1000));
    }
}
