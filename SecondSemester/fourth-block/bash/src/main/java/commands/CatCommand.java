package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CatCommand implements Command {
    CommandProcessor commandProcessor;

    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        if (args == null) {
            System.out.println("No such file or directory");
            commandProcessor.setRetcode(1);
        } else {
            for (String file : args) {
                printFile(file);
            }
        }
        return true;
    }

    public void printFile(String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file), UTF_8);
            for (String item : lines) {
                System.out.println(item);
            }
            commandProcessor.setRetcode(0);
        } catch (IOException e) {
            System.out.println("cat: " + file + ": No such file");
            commandProcessor.setRetcode(1);
        }
    }

    @Override
    public String getName() {
        return "CAT";
    }
}