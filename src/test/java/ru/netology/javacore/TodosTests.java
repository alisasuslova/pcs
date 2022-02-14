package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTests {

    Todos sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new Todos();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testAddTask() {
        List<String> tasks = new ArrayList<>();
        String task1 = "task1";
        String task2 = "task2";
        tasks.add(task1);
        tasks.add(task2);

        int expected = 2;
        int result = tasks.size();

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveTask() {
        List<String> tasks = new ArrayList<>();
        String task1 = "task1";
        String task2 = "task2";
        String task3 = "task3";
        String task4 = "task4";
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.remove(task1);
        tasks.remove(task3);

        String expected = "[task2, task4]";
        String result = tasks.toString();

        assertEquals(expected, result);

    }

    @Test
    public void testGetAllTasks() {
        StringBuilder sb = new StringBuilder();
        List<String> tasks = new ArrayList<>();
        String task1 = "task1";
        String task2 = "task2";
        String task3 = "task3";
        String task4 = "task4";
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        Collections.sort(tasks);

        for (String str: tasks) {
            sb.append(str)
                    .append(" ");
        }
        String result = sb.toString();
        String expected = "task1 task2 task3 task4 ";

        assertEquals(expected, result);
    }
}
