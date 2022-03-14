package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private int port;
    private Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        Todos todos = new Todos();
        System.out.println("Сервер стартовал " + port + "...");
        GsonBuilder builder;


        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                    System.out.printf("Новый запрос, порт %d%n", clientSocket.getPort());

                    while (true) {
                        String line = in.readLine();
                        builder = new GsonBuilder();
                        Gson gson = builder.create();
                        Task task = gson.fromJson(line, Task.class);
                        System.out.println("Тип: \"" + task.type + "\", Задача: \"" + task.task + "\"");
                        if (task.type == Task.Type.ADD) {
                            todos.addTask(task.task);
                            out.println(todos.getAllTasks());
                        } else {
                            todos.removeTask(task.task);
                            out.println(todos.getAllTasks());
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
