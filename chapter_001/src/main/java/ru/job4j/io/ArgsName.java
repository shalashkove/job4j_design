package ru.job4j.io;

/* test */

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException("No this KEY data in Map.");
        }
        return result;
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Start new argument pair with \"-\" symbol.");
            }
            String line = arg.substring(1);
            String[] parsData = line.split("=");
            if (parsData.length != 2) {
                throw new IllegalArgumentException("Wrong format of arguments. Use KEY=VALUE format.");
            }
            if (parsData[0].length() == 0 || parsData[1].length() == 0) {
                throw new IllegalArgumentException("Wrong format of arguments. Use KEY=VALUE format.");
            }
            values.put(parsData[0], parsData[1]);
        }

    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
