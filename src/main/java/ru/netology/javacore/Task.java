package ru.netology.javacore;

public class Task {
    Type type;
    String task;

    public enum Type {
        ADD, REMOVE
    }

    public Task(Type type, String task) {
        this.type = type;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "type=" + type +
                ", task='" + task + '\'' +
                '}';
    }
}
