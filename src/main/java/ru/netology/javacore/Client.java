package ru.netology.javacore;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        try (
                Socket socket = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"Пойти на прогулку\" }");
            System.out.println(in.readLine());
        }

        try (
                Socket socket1 = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                PrintWriter out = new PrintWriter(socket1.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"Накормить собаку\" }");
            System.out.println(in.readLine());
        }

        try (
                Socket socket2 = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                PrintWriter out = new PrintWriter(socket2.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"Купить продукты\" }");
            System.out.println(in.readLine());
        }

        try (
                Socket socket3 = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket3.getInputStream()));
                PrintWriter out = new PrintWriter(socket3.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"Разобрать почту\" }");
            System.out.println(in.readLine());
        }


        try (
                Socket socket4 = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket4.getInputStream()));
                PrintWriter out = new PrintWriter(socket4.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"REMOVE\", \"task\": \"Пойти на прогулку\" }");
            System.out.println(in.readLine());
        }

        try (
                Socket socket5 = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket5.getInputStream()));
                PrintWriter out = new PrintWriter(socket5.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"ADD\", \"task\": \"Собрать чемодан\" }");
            System.out.println(in.readLine());
        }

        try (
                Socket socket6 = new Socket("localhost", 8992);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket6.getInputStream()));
                PrintWriter out = new PrintWriter(socket6.getOutputStream(), true);
        ) {
            out.println("{ \"type\": \"REMOVE\", \"task\": \"Разобрать почту\" }");
            System.out.println(in.readLine());
        }
    }
}
