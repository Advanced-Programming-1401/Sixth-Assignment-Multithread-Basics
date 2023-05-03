package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                e.printStackTrace();
            }
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();

        while (!tasks.isEmpty()) {
            // Find the task with the shortest processing time
            Task shortestTask = tasks.get(0);
            for (Task task : tasks) {
                if (task.processingTime < shortestTask.processingTime) {
                    shortestTask = task;
                }
            }

            // Remove the shortest task from the list and create a new thread for it
            tasks.remove(shortestTask);
            Thread thread = new Thread(shortestTask);

            // Start the thread, wait for it to finish, and add its ID to the executed tasks list
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executedTasks.add(shortestTask.ID);
        }
        return executedTasks;
    }

    public static void main(String[] args) {

        // Create some tasks for testing
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", 3000));
        tasks.add(new Task("Task 2", 1000));
        tasks.add(new Task("Task 3", 2000));

        // Start the simulation and print the list of executed tasks
        CPU_Simulator simulator = new CPU_Simulator();
        ArrayList<String> executedTasks = simulator.startSimulation(tasks);
        System.out.println("Executed tasks: " + executedTasks);

    }
}
