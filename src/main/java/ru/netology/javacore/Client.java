package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static String pickRandomType() {
        String[] type = new String[]{"ADD", "REMOVE"};
        int temp = (int)Math.floor(Math.random() * type.length);
        return type[temp];
    }

    public static String pickRandomTask() {
        String[] tasks = new String[]{"Пойти на прогулку", "Накормить собаку", "Купить продукты", "Разобрать почту",
                "Собрать чемодан"};
        int temp1 = (int)Math.floor(Math.random() * tasks.length);
        return tasks[temp1];
    }

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket("localhost", 8989);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("{ \"type\": "+ pickRandomType() + ", \"task\": \"" + pickRandomTask() + "\" }");
            System.out.println(in.readLine());
        }
    }
}