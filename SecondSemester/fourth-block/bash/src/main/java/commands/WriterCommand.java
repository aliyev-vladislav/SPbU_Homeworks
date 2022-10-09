package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WriterCommand implements Command {
    CommandProcessor commandProcessor;
    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        try  {
            File fileWriter = new File(commandProcessor.getFileToWrite());
            PrintStream printStream = new PrintStream(fileWriter, UTF_8);
            System.setOut(printStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public String getName() {
        return ">";
    }
}
