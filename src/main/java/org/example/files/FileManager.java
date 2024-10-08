package org.example.files;

import org.example.ecxeptions.CaesarsCipherException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    private BufferedReader bufferedReader;

    public String readLineFromFile(String filePath) {
        try {
            Path path = Path.of(filePath);
            if (bufferedReader == null) {
                bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            }
            //TODO закрытие потока?
            return bufferedReader.readLine();
        } catch (IOException | RuntimeException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    public void appendToFile(String content, String filePath) {
        try {
            Path path = Path.of(filePath);
            Files.writeString(path, content, StandardOpenOption.APPEND);
        } catch (IOException | RuntimeException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    public void createFile(String filePath) {
        try {
            Files.createFile(Path.of(filePath));
        } catch (IOException | RuntimeException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }

    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new CaesarsCipherException(e.getMessage(), e);
        }
    }
}
