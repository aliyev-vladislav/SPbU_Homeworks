package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OverwriteCommand implements Command {
    CommandProcessor commandProcessor;
    List<String> lines;
    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        try  {
            File fileWriter = new File(commandProcessor.getFileToOverwrite());
            lines = Files.readAllLines(Paths.get(commandProcessor.getFileToOverwrite()));
            PrintStream printStream = new PrintStream(fileWriter);
            System.setOut(printStream);
            for (String item : this.lines) {
                System.out.println(item);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public String getName() {
        return ">>";
    }
}
