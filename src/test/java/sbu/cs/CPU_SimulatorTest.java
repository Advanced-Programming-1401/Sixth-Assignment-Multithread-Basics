package sbu.cs;

import sbu.cs.CPU_Simulator.Task;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;


public class CPU_SimulatorTest {
    private static CPU_Simulator simulator;

    @BeforeAll
    static void setup() {
        simulator = new CPU_Simulator();
    }

    @Test
    void testOne() {
        Task[] taskArray = {new Task("A", 50),
                new Task("B", 150),
                new Task("C", 10),
                new Task("D", 300),
                new Task("E", 20),
                new Task("F", 160)};

        ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(taskArray));
        ArrayList<String> target = new ArrayList<>(Arrays.asList("C", "E", "A", "B", "F", "D"));

        assertEquals(target, simulator.startSimulation(taskList));
    }

    @Test
    void testTwo() {
        Task[] taskArray = {new Task("A", 100),
                new Task("B", 90),
                new Task("C", 80),
                new Task("D", 70),
                new Task("E", 60),
                new Task("F", 50),
                new Task("G", 40),
                new Task("H", 30),
                new Task("I", 20),
                new Task("J", 10)};

        ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(taskArray));
        ArrayList<String> target = new ArrayList<>(Arrays.asList("J", "I", "H", "G", "F", "E", "D", "C", "B", "A"));

        assertEquals(target, simulator.startSimulation(taskList));
    }

    @Test
    void testThree() {
        Task[] taskArray = {new Task("A", 0),
                new Task("B", 1000),
                new Task("C", 200),
                new Task("D", 300)};

        ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(taskArray));
        ArrayList<String> target = new ArrayList<>(Arrays.asList("A", "C", "D", "B"));

        assertEquals(target, simulator.startSimulation(taskList));
    }
}
