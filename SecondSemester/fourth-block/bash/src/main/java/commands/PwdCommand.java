package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;

import java.io.File;

public class PwdCommand implements Command {
    CommandProcessor commandProcessor;

    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        print(context.getCurrentDirectory());
        commandProcessor.setRetcode(0);
        return true;
    }

    @Override
    public String getName() {
        return "PWD";
    }

    public void print(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getName());
            }
        }
    }
}