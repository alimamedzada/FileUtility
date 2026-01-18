package org.example;

import java.io.*;

public class FileUtility {
    public static void writeObject(Serializable object, String fileName) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName));) {
            oos.writeObject(object);
        }
    }

    public static Object readObject(String fileName) throws Exception {
        Object obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));) {
            obj = ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
        return obj;
    }

}
