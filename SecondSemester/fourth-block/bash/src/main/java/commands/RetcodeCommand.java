package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

public class RetcodeCommand implements Command {
    CommandProcessor commandProcessor;
    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        System.out.println(commandProcessor.getRetcode());
        return true;
    }

    @Override
    public String getName() {
        return "$?";
    }
}
