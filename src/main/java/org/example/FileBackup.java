package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileBackup {
    public static void createBackup(String sourceDirectory) {
        File sourceDir = new File(sourceDirectory);
        File backupDir = new File(sourceDir.getParent(), "backup");

        if (!backupDir.exists()) {
            boolean created = backupDir.mkdirs(); // Можно использовать mkdirs() для создания родительских каталогов, если это необходимо.
            if (!created) {
                System.err.println("Не удалось создать резервный каталог.");
                return;
            }
        }

        File[] files = sourceDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    File destination = new File(backupDir.getAbsolutePath(), file.getName());
                    try {
                        Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Не удалось выполнить резервное копирование " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
            System.out.println("Резервное копирование успешно завершено.");
        }
    }

    public static void main(String[] args) {
        String sourceDirectory = "src/main/java/resources"; 
        createBackup(sourceDirectory);
    }
}
