package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

public class EchoCommand implements Command {
    CommandProcessor commandProcessor;
    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        if (args == null) {
            System.out.println();
        } else {
            for (var item : args) {
                System.out.print(item + " ") ;
            }
            System.out.println();
        }
        commandProcessor.setRetcode(0);
        return true;
    }

    @Override
    public String getName() {
        return "ECHO";
    }
}
