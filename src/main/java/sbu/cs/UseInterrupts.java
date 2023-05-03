package sbu.cs;

/*
    In this exercise, you must analyse the following code and use interrupts
    in the main function to terminate threads that run for longer than 3 seconds.

    A thread may run for longer than 3 seconds due the many different reasons,
    including lengthy process times or getting stuck in an infinite loop.

    Take note that you are NOT ALLOWED to change or delete any existing line of code.
 */

public class UseInterrupts
{

    public static class SleepThread extends Thread {
        int sleepCounter;

        public SleepThread(int sleepCounter) {
            super();
            this.sleepCounter = sleepCounter;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            while (this.sleepCounter > 0 && !Thread.currentThread().isInterrupted())
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }
                finally {
                    this.sleepCounter--;
                    System.out.println("Number of sleeps remaining: " + this.sleepCounter);
                }
            }

            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this.getName() + " has been interrupted");
            }

        }
    }


    public static class LoopThread extends Thread {
        int value;
        public LoopThread(int value) {
            super();
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            for (int i = 0; i < 10 && !Thread.currentThread().isInterrupted(); i += 3)
            {
                i -= this.value;

            }

            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this.getName() + " has been interrupted");
            }
        }
    }

/*
    You can add new code to the main function. This is where you must utilize interrupts.
    No existing line of code should be changed or deleted.
 */
    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread(5);
        sleepThread.start();

        try {
            Thread.sleep(3000);
            sleepThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LoopThread loopThread = new LoopThread(3);
        loopThread.start();

        try {
            Thread.sleep(3000);
            loopThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
