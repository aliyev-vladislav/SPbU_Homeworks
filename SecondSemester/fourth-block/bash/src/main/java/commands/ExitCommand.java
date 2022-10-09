package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

public class ExitCommand implements Command {
    CommandProcessor commandProcessor;
    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        System.out.println("Finishing command processor... done.");
        System.exit(0);
        return false;
    }

    @Override
    public String getName() {
        return "EXIT";
    }
}