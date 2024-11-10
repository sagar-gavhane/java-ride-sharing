package com.example.geektrust;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> commands = getCommandsFromFile(args[0]);

        RideSharingApp rideSharingApp = new RideSharingApp(commands);
        rideSharingApp.executeCommands();
    }

    public static List<String> getCommandsFromFile(String fileName) {
        List<String> commands = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName)) {
            Scanner scanner = new Scanner(fis);

            while (scanner.hasNextLine()) {
                commands.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("IOException while reading file: " + fileName);
        }

        return commands;
    }
}
