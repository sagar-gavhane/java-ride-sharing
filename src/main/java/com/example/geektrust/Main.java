package com.example.geektrust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<String> commands = getCommandsFromFile(args[0]);

        RideSharingApp rideSharingApp = new RideSharingApp(commands);
        rideSharingApp.executeCommands();
    }

    public static List<String> getCommandsFromFile(String fileName) {
        List<String> commands = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(fileName)) {
            Scanner scanner = new Scanner(fis);

            while (scanner.hasNextLine()) {
                commands.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            logger.error("File not found {}", fileName);
        } catch (IOException e) {
            logger.error("IOException while reading file {}", fileName);
        }

        return commands;
    }
}
