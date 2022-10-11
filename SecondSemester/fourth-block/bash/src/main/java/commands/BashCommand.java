package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BashCommand implements Command {
    CommandProcessor commandProcessor;

    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        if (args != null) {
            for (var item : args) {
                addScript(item);
            }
        } else {
            System.out.println("Script not found");
        }
        return true;
    }

    public boolean addScript(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                Files.readAllLines(Paths.get(fileName)).forEach(line ->
                        commandProcessor.getScripts().add(line));
            } catch (IOException e) {
                //
            }
        } else {
            System.out.println("Script not found");
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "BASH";
    }
}
