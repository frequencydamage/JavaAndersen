package org.novak.java.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataLoaderUtil {

    public static <T> T loadData(String fileName) {
        Path path = Paths.get("src/main/java/org/novak/java/storage", fileName);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("File cannot be created!");
                return null;
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No available data to load!");
            return null;
        }
    }

    public static <T> void saveData(T instance, String fileName) {
        Path path = Paths.get("src/main/java/org/novak/java/storage", fileName);

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Error creating directory to store the data!");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            System.err.println("Data cannot be saved!");
        }
    }
}