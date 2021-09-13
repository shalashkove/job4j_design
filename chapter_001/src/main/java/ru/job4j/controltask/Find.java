package ru.job4j.controltask;

import ru.job4j.io.SearchFiles;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    private final Map<String, String> argsList = new HashMap<>();
    private List<Path> listOfPaths;

    public Map<String, String> getArgsList() {
        return argsList;
    }

    public List<Path> getListOfPaths() {
        return listOfPaths;
    }

    public void parsArgs(String[] params) {
        for (String elem : params) {
            String[] line = elem.split("=");
            if (line.length == 2 && line[0].length() != 0 && line[1].length() != 0) {
                argsList.put(line[0], line[1]);
            }
        }
    }

    public boolean validation() {
        boolean result = true;
        if (argsList.size() != 4) {
            result = false;
        }
        if (!argsList.containsKey("-d")
                || !argsList.containsKey("-n")
                || !argsList.containsKey("-o")
                || !argsList.containsKey("-t")) {
            result = false;
        } else {
            String value = argsList.get("-t");
            if (!value.equals("mask") && !value.equals("name") && !value.equals("regex")) {
                result = false;
            }
        }
        if (!result) {
            System.out.println("Wrong format of arguments. Use next format:\n"
                    + "java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt\n"
                    + "where -t=mask|name|regex value");
        }
        return result;
    }

    public void findMask() {
        final String patternString;
        if (argsList.get("-n").startsWith("*")) {
            patternString = argsList.get("-n").substring(1);
        } else {
            patternString = argsList.get("-n");
        }
        Predicate<Path> condition = p -> p.toFile().getName().endsWith(patternString);
        find(condition);
    }

    public void findName() {
        Predicate<Path> condition = p -> p.toFile().getName().matches(argsList.get("-n"));
        find(condition);
    }

    public void findRegex() {
        Predicate<Path> condition = p -> {
            String patternString = argsList.get("-n");
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(p.toFile().toString());
            return matcher.find();
        };
        find(condition);
    }

    private void find(Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        Path startPath = Paths.get(argsList.get("-d"));
        try {
            Files.walkFileTree(startPath, searcher);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        listOfPaths = searcher.getPaths();
    }

    public void writeResult() {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsList.get("-o"))
                ))) {
            listOfPaths.forEach(x -> out.println(x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Find find = new Find();
        find.parsArgs(args);
        if (find.validation()) {
            switch (find.getArgsList().get("-t")) {
                case "mask" : find.findMask();
                case "name" : find.findName();
                case "regex" : find.findRegex();
                default : break;
            }
        }
        find.writeResult();
//        System.out.printf("\n   Finded %s items. Files result list:\n", find.getListOfPaths().size());
//        find.getListOfPaths().forEach(x -> System.out.println(x.toAbsolutePath()));
    }
}
