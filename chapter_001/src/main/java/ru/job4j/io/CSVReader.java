package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {

    public boolean validation(ArgsName argsList) {
        boolean result = true;
        try {
            argsList.get("path");
            argsList.get("delimiter");
            argsList.get("filter");
            argsList.get("out");
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format of arguments. Use next format:\n"
                    + "java -jar target/csvReader.jar -path=file.csv -delimiter=\";\"  -out=stdout -filter=name,age\n"
                    + "where -out=stdout|path to output file, -filter=filtered columns");
            result = false;
        }
        return result;
    }

    public static void handle(ArgsName argsName) {
        final String DELIMITER = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(new FileInputStream(new File(argsName.get("path"))))) {
            String[] columnsList = null;
            String headersString = null;
            if (scanner.hasNextLine()) {
                headersString = scanner.nextLine();
                columnsList = headersString.split(DELIMITER);
            }
            String[] setColumns = argsName.get("filter").split(",");
            List<Integer> columnsToOut = CSVReader.columnNumberDefine(columnsList, setColumns);
            StringBuilder outData = new StringBuilder();
            outData.append(CSVReader.formString(DELIMITER, headersString, columnsToOut));
            while (scanner.hasNextLine()) {
                String newLine = scanner.nextLine();
                outData.append(CSVReader.formString(DELIMITER, newLine, columnsToOut));
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(outData.toString());
            } else {
                File file = new File(argsName.get("out"));
                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                    out.write(outData.toString().getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder formString(String delimiter, String inString, List<Integer> columnsToOut) {
        StringBuilder sb = new StringBuilder();
        String[] rowSting = inString.split(delimiter);
        for (int i = 0; i != columnsToOut.size(); i++) {
            sb.append(rowSting[columnsToOut.get(i)]);
            if (i != columnsToOut.size() - 1) {
                sb.append(delimiter);
            }
        }
        sb.append(System.lineSeparator());
        return sb;
    }

    public static List<Integer> columnNumberDefine(String[]columnsList, String[]setColumns) {
        List<Integer> result = new ArrayList<>();
        for (String el : setColumns) {
            for (int i = 0; i != columnsList.length; i++) {
                if (el.equals(columnsList[i])) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        if (csvReader.validation(argsName)) {
            CSVReader.handle(argsName);
        }
    }
}