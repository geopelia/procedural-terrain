package com.atilio.procedural.mics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.atilio.procedural.entities.BinaryTreeNode;

public class TreeToStream {
    static final String FOLDER_NAME = "serdata/";
    static final String EXTENTION = ".ser";

    private TreeToStream() {
    }

    public static <T extends Serializable> void saveToStream(BinaryTreeNode<T> rootNode, String prefix) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");
        String timestamp = LocalDateTime.now().format(formatter);
        String filename = FOLDER_NAME + prefix + timestamp + EXTENTION;
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rootNode);
            objectOutputStream.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> BinaryTreeNode<T> loadFromStream(String filename) {
        BinaryTreeNode<T> binaryTreeNode = null;
        if (filename == null) {
            filename = getFileName(null);
        }
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            binaryTreeNode = (BinaryTreeNode<T>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return binaryTreeNode;
    }

    public static String getFileName(String folder) {
        try (Stream<Path> stream = Files.list(Paths.get(folder == null ? FOLDER_NAME : folder))) {
            Optional<String> filename = stream.filter(file -> !Files.isDirectory(file)).map(Path::toFile).max(Comparator.comparingLong(File::lastModified)).map(File::getPath);
            if (filename.isPresent()) {
                return filename.get();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
