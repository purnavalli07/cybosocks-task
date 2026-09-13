package com.cybosocks.runner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            printError("Usage: java -jar runner.jar <program.json>");
            System.exit(1);
        }

        String filePath = args[0];

        try {
            Gson gson = new Gson();

            try (FileReader reader = new FileReader(filePath)) {

                JsonObject program = gson.fromJson(
                        reader,
                        JsonObject.class
                );

                if (program == null) {
                    throw new IllegalArgumentException(
                            "JSON file is empty"
                    );
                }

                ProgramRunner runner = new ProgramRunner();

                runner.executeProgram(program);

                Turtle turtle = runner.getTurtle();

                System.out.println(
                        "Final position: ("
                                + turtle.getX()
                                + ", "
                                + turtle.getY()
                                + ") facing "
                                + formatDirection(turtle.getDirection())
                );
            }

        } catch (JsonParseException e) {

            printError("Malformed JSON");
            System.exit(1);

        } catch (IOException e) {

            printError("Could not read file: " + filePath);
            System.exit(1);

        } catch (IllegalArgumentException e) {

            printError(e.getMessage());
            System.exit(1);

        } catch (Exception e) {

            printError("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void printError(String message) {
        System.out.println("Error: " + message);
    }

    private static String formatDirection(Turtle.Direction direction) {
        return switch (direction) {
            case NORTH -> "North";
            case EAST -> "East";
            case SOUTH -> "South";
            case WEST -> "West";
        };
    }
}