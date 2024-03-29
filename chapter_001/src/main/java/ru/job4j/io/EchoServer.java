package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(9000)) {
                while (!server.isClosed()) {
                    Socket socket = server.accept();
                    try (OutputStream out = socket.getOutputStream();
                         BufferedReader in = new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                        boolean isExit = false;
                        String msg = null;
                        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            System.out.println(str);
                            if (str.contains("?msg=Exit")) {
                                isExit = true;
                                continue;
                            }
                            if (str.contains("?msg=")) {
                                String[] arr = str.split("=");
                                if (arr.length == 2) {
                                    msg = arr[1].split(" ")[0];
                                }
                            }
                        }
                        if (msg != null && !isExit) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(msg.getBytes());
                        }
                        if (isExit) {
                            out.write("HTTP/1.1 200 OK\r\n".getBytes());
                            server.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in main method", e);
        }
    }
}
