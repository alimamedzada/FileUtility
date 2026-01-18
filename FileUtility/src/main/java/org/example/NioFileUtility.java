package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioFileUtility implements Serializable {
    public static void writeBytes(String fileName, byte[] data) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, data);
    }

    public static byte[] readBytes(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }

    public static void writeJson(Serializable object, String fileName) throws IOException {
        Gson g = new Gson();
        String json = g.toJson(object);
        byte[] bytes = json.getBytes();
        Path path = Files.write(Path.of(fileName), bytes);
    }

    public static Object readJson(Class clazz, String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        Gson g = new Gson();
        return g.fromJson(fileName, clazz);
    }

    public static void writeObject(Serializable object, String fileName) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName));) {
            oos.writeObject(object);
        }
    }

    public static Object readObject(Serializable object, String fileName) throws Exception {
        Object obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));) {
            obj = ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        } finally {
            return obj;
        }
    }
}
