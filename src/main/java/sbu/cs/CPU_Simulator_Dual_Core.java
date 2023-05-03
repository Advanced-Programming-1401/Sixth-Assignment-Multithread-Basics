package sbu.cs;

import java.util.ArrayList;

public class CPU_Simulator_Dual_Core {
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.ID = ID;
            this.processingTime = processingTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(processingTime);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.ID + " Done...");
        }
    }

    public static ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        tasks = sortTasks(tasks);

        Thread coreOne = new Thread(tasks.get(0));
        coreOne.start();
        executedTasks.add(tasks.get(0).ID);
        System.out.println("task 0 with ID \"" + tasks.get(0).ID + "\" sent to core one");

        Thread coreTwo = new Thread(tasks.get(1));
        coreTwo.start();
        executedTasks.add(tasks.get(1).ID);
        System.out.println("task 1 with ID \"" + tasks.get(1).ID + "\" sent to core two");

        int index = 2;

        while (true) {
            if (index >= tasks.size()) {
                break;
            }

            if(!coreOne.isAlive()) {
                coreOne = new Thread(tasks.get(index));
                coreOne.start();
                executedTasks.add(tasks.get(index).ID);
                System.out.println("task " + index + " with ID \"" + tasks.get(index).ID + "\" sent to core one");
                index ++;
            }
            if (!coreTwo.isAlive()) {
                coreTwo = new Thread(tasks.get(index));
                coreTwo.start();
                executedTasks.add(tasks.get(index).ID);
                System.out.println("task " + index + " with ID \"" + tasks.get(index).ID + "\" sent to core two");
                index ++;
            }
        }
        try {
            System.out.println("waiting for termination...");
            coreOne.join();
            coreTwo.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return executedTasks;
    }
    public static ArrayList<Task> sortTasks (ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).processingTime > tasks.get(j).processingTime) {
                    Task temp = tasks.get(i);
                    tasks.set(i, tasks.get(j));
                    tasks.set(j, temp);
                }
            }
        }
        return tasks;
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("A", 5000));
        tasks.add(new Task("B", 2000));
        tasks.add(new Task("C", 4000));
        tasks.add(new Task("D", 3000));
        tasks.add(new Task("E", 1000));
        tasks.add(new Task("F", 500));
        tasks.add(new Task("G", 1500));

        System.out.println(startSimulation(tasks));
    }
}
