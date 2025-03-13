package org.novak.java.util;

import java.io.*;
import java.nio.file.*;

public class DataLoaderUtil {

    private static final String STORAGE_PATH = System.getProperty("app.storage.path");

    public static <T> T loadData(String fileName) {
        Path path = Paths.get(STORAGE_PATH, fileName);
        if (!Files.exists(path)) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load data from: " + path);
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void saveData(T instance, String fileName) {
        Path path = Paths.get(STORAGE_PATH, fileName);

        try {
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
        } catch (IOException e) {
            System.err.println("Error creating directory: " + path.getParent());
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(instance);
        } catch (IOException e) {
            System.err.println("Failed to save data to: " + path);
            e.printStackTrace();
        }
    }
}