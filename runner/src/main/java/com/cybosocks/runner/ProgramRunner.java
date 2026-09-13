package com.cybosocks.runner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ProgramRunner {

    private final Turtle turtle;

    public ProgramRunner() {
        turtle = new Turtle();
    }

    public void executeProgram(JsonObject programObject) {
        if (programObject == null) {
            throw new IllegalArgumentException("Program JSON is missing");
        }

        JsonElement programElement = programObject.get("program");

        if (programElement == null || !programElement.isJsonArray()) {
            throw new IllegalArgumentException(
                    "Missing or invalid 'program' array"
            );
        }

        executeBlocks(programElement.getAsJsonArray());
    }

    private void executeBlocks(JsonArray blocks) {

        for (JsonElement element : blocks) {

            if (!element.isJsonObject()) {
                throw new IllegalArgumentException(
                        "Invalid block"
                );
            }

            JsonObject block = element.getAsJsonObject();

            JsonElement typeElement = block.get("type");

            if (typeElement == null || !typeElement.isJsonPrimitive()) {
                throw new IllegalArgumentException(
                        "Block is missing 'type'"
                );
            }

            String type = typeElement.getAsString();

            switch (type) {

                case "move":
                    executeMove(block);
                    break;

                case "turn":
                    executeTurn(block);
                    break;

                case "say":
                    executeSay(block);
                    break;

                case "repeat":
                    executeRepeat(block);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown block type: " + type
                    );
            }
        }
    }


    private void executeMove(JsonObject block) {

        JsonElement stepsElement = block.get("steps");

        if (stepsElement == null || !stepsElement.isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    "Move block is missing 'steps'"
            );
        }

        int steps;

        try {
            steps = stepsElement.getAsInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Move steps must be a number"
            );
        }

        if (steps < 0) {
            throw new IllegalArgumentException(
                    "Move steps cannot be negative"
            );
        }

        turtle.move(steps);
    }


    private void executeTurn(JsonObject block) {

        JsonElement directionElement = block.get("direction");

        if (directionElement == null || !directionElement.isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    "Turn block is missing 'direction'"
            );
        }

        String direction = directionElement.getAsString();

        if (!direction.equals("left")
                && !direction.equals("right")) {

            throw new IllegalArgumentException(
                    "Turn direction must be 'left' or 'right'"
            );
        }

        turtle.turn(direction);
    }


    private void executeSay(JsonObject block) {

        JsonElement textElement = block.get("text");

        if (textElement == null || !textElement.isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    "Say block is missing 'text'"
            );
        }

        System.out.println(textElement.getAsString());
    }


    private void executeRepeat(JsonObject block) {

        JsonElement timesElement = block.get("times");

        if (timesElement == null || !timesElement.isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    "Repeat block is missing 'times'"
            );
        }

        int times;

        try {
            times = timesElement.getAsInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Repeat times must be a number"
            );
        }

        if (times < 0) {
            throw new IllegalArgumentException(
                    "Repeat times cannot be negative"
            );
        }

        JsonElement bodyElement = block.get("body");

        if (bodyElement == null || !bodyElement.isJsonArray()) {
            throw new IllegalArgumentException(
                    "Repeat block is missing 'body'"
            );
        }

        JsonArray body = bodyElement.getAsJsonArray();

        // Recursive execution.
        for (int i = 0; i < times; i++) {
            executeBlocks(body);
        }
    }


    public Turtle getTurtle() {
        return turtle;
    }
}