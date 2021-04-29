package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Pack {
    private ArgsName argsName;

    private boolean validate(String[] args) {
        boolean result = true;
        this.argsName = ArgsName.of(args);
        if (this.argsName.get("d") == null) {
            result = false;
        }
        if (this.argsName.get("e") == null) {
            result = false;
        }
        if (this.argsName.get("o") == null) {
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        Pack pack = new Pack();
        if (!pack.validate(args)) {
            throw new IllegalArgumentException("Wrong arguments format. "
                    + "Use format: java -jar pack.jar -d=VALUE -e=VALUE -o=VALUE.");
        }
        Path sourceDirectory = Paths.get(pack.argsName.get("d"));
        List<Path> zippedPaths = null;
        try {
            zippedPaths = Search.search(sourceDirectory,
                    p -> !p.toFile().getName().endsWith(pack.argsName.get("e")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> zippedFiles = new ArrayList<>();
        for (Path path : zippedPaths) {
            zippedFiles.add(path.toFile());
        }
        File outputFile = new File(pack.argsName.get("o"));
        Zip zip = new Zip();
        zip.packFiles(zippedFiles, outputFile);
    }
}
