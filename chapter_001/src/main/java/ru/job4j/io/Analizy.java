package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.length() != 0) {
                    String[] startLine = line.split(" ");
                    if (startLine[0].equals("400") || startLine[0].equals("500")) {
                        String startTime = startLine[1];
                        String endTime = startLine[1];
                        while ((line = in.readLine()) != null) {
                            if (line.length() != 0) {
                                String[] nextLine = line.split(" ");
                                endTime = nextLine[1];
                                if (!(nextLine[0].equals("400") || nextLine[0].equals("500"))) {
                                    break;
                                }
                            }
                        }
                        out.printf("%s;%s%s", startTime, endTime, System.lineSeparator());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./data/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
