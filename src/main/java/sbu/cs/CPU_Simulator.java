package sbu.cs;

import java.util.*;

/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, add its ID to the executed tasks arraylist.
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.ID = ID;
            this.processingTime = processingTime;
        }

    /*
        Simulate running a task by utilizing the sleep method for the duration of
        the task's processingTime. The processing time is given in milliseconds.
    */
        @Override
        public void run() {
            try{
                Thread.sleep(this.processingTime);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        // TODO
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    class TaskComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2){
            if(t1.processingTime == t2.processingTime)
                return 0;
            else if (t1.processingTime > t2.processingTime) {
                return 1;
            }
            else
                return -1;
        }
    }
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();

        Collections.sort(tasks, new TaskComparator());

        for (Task task: tasks){
            Thread thread = new Thread(task);

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

            executedTasks.add(task.ID);
        }

        // TODO

        return executedTasks;
    }

    public static void main(String[] args) {

        CPU_Simulator cpu = new CPU_Simulator();
        ArrayList<Task> task = new ArrayList<>();
        task.add(new Task("d", 1000));
        task.add(new Task("s", 373));
        task.add(new Task("z", 567));
        task.add(new Task("r", 1000));

        ArrayList<String> main = cpu.startSimulation(task);

        for (String item: main){
            System.out.println(item);
        }
    }
}
