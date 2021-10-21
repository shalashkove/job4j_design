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

    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(new FileInputStream(new File(argsName.get("path"))));
        String[] columnsList = null;
        if (scanner.hasNextLine()) {
            columnsList = scanner.nextLine().split(";");
        }
        scanner.close();
        String[] setColumns = argsName.get("filter").split(",");
        List<Integer> columnsToOut = CSVReader.columnNumberDefine(columnsList, setColumns);
        Pattern pattern = Pattern.compile("([\\n" + argsName.get("delimiter") + "]|(\\r\\n))+");
        scanner = new Scanner(new FileInputStream(new File(argsName.get("path"))))
                .useDelimiter(pattern);
        int cellInRaw = 0;
        int cursor = 0;
        StringBuilder outData = new StringBuilder();
        while (scanner.hasNext()) {
            String cell = scanner.next();
            for (int el : columnsToOut) {
                if (el == cursor) {
                    outData.append(cell);
                    cellInRaw++;
                    if (cellInRaw == setColumns.length || cursor == columnsList.length) {
                        outData.append(System.lineSeparator());
                    } else {
                        outData.append(argsName.get("delimiter"));
                    }
                    break;
                }
            }
            cursor++;
            if (cursor == columnsList.length) {
                cursor = 0;
            }
            if (cellInRaw == setColumns.length) {
                cellInRaw = 0;
            }
        }
        if (argsName.get("out").equals("stdout")) {
            System.out.println(outData.toString());
        } else  {
            File file = new File(argsName.get("out"));
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                out.write(outData.toString().getBytes());
            }
        }
    }

    public static List<Integer> columnNumberDefine(String[] columnsList, String[] setColumns) {
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
            try {
                CSVReader.handle(argsName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
