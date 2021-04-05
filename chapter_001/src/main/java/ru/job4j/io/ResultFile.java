package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            Matrix matrix = new Matrix();
            int[][] result = matrix.multiple(9);
            for (int i = 0; i != result.length; i++) {
                for (int j = 0; j != result[i].length; j++) {
                    out.write(((Integer) result[i][j]).toString().getBytes());
                }
                out.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
