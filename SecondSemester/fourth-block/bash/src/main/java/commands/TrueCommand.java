package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

public class TrueCommand implements Command {
    private CommandProcessor commandProcessor;
    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        commandProcessor.setRetcode(0);
        System.out.println(commandProcessor.getRetcode());
        return true;
    }

    @Override
    public String getName() {
        return "TRUE";
    }
}