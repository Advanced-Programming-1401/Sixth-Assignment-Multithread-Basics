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
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public static ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++){
            for (int j = i+1; j < tasks.size(); j++){
                if (tasks.get(i).processingTime > tasks.get(j).processingTime){
                    Collections.swap(tasks, i, j);
                }
            }
        }
        for (Task task : tasks){
            Thread thread = new Thread(task);
            thread.start();
            try{
                executedTasks.add(task.ID);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return executedTasks;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            try {
                Task task = new Task(sc.nextLine(), sc.nextByte());
                tasks.add(task);
            } catch (Exception e) {
                break;
            }
        }
        startSimulation(tasks);
    }
}
