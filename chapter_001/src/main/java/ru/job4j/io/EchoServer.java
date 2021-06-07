package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    boolean isBye = false;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("?msg=Bye")) {
                            isBye = true;
                        }
                        if (isBye && str.contains("localhost:9000")) {
                            server.close();
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\"".getBytes());
                }
            }
        }
    }
}
