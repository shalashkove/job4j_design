package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> dublicatesList = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //System.out.println(file.toAbsolutePath());
        if (attrs.isRegularFile()) {
            String name = file.getFileName().toString();
                //  System.out.println("file.getFileName().toString(): = " + name);
            //long size = attrs.size();
            long size = Files.size(file);
            FileProperty newItem = new FileProperty(size, name);
            if (dublicatesList.containsKey(newItem)) {
                System.out.println("dublicate finded: " + file.toString());
                System.out.println("for this file: " + dublicatesList.get(newItem));
            } else {
                dublicatesList.put(newItem, file);
            }
        }
        return super.visitFile(file, attrs);
    }
}
