package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public List<String> getTasks() {
        return this.tasks;
    }

    public void removeTask(String task) {
        this.tasks.remove(task);
    }

    @Override
    public String toString() {
        return tasks + " ";
    }

    public String getAllTasks() {
        List<String> str = getTasks();
        List<String> strSort = str.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        String temp = strSort.toString();
        String[] strArray = temp.split(",");
        String result = String.join(" ", strArray);
        return result;
    }

}
