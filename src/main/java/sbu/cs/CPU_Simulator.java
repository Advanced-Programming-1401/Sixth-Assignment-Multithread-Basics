package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.sort;

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

        public long getProcessingTime() {
            return processingTime;
        }

        public String getID() {
            return ID;
        }

        public Task(String ID, long processingTime) {
            this.processingTime = processingTime;
            this.ID = ID;
        }

    /*
        Simulate running a task by utilizing the sleep method for the duration of
        the task's processingTime. The processing time is given in milliseconds.
    */
        @Override
        public void run() {
            try {

                Thread.sleep(300);

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
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        ArrayList<Long> processingTimes=new ArrayList<>();
        ArrayList<Task> sortedTasks=new ArrayList<>();
        for(Task task:tasks){
            processingTimes.add(task.getProcessingTime());
        }
        Collections.sort(processingTimes);
        for(Long long1:processingTimes) {
            for(Task task:tasks){
                if(long1==task.getProcessingTime()){
                    sortedTasks.add(task);
                }
            }

        }
        for(Task task:sortedTasks){
            Thread thread=new Thread(task);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            executedTasks.add(task.getID());
        }
        return executedTasks;
    }





    public static void main(String[] args) {
    }
}
