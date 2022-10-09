package controller;

import commands.*;
import parser.CommandParser;

import java.io.*;
import java.util.*;

/**
 * controller.CommandProcessor
 * @author Vladislav Aliyev
 * @since 27.11.12
 */
public class CommandProcessor {
    public static final String MSG_COMMAND_NOT_FOUND = "Command not found";
    private ArrayList<String> scripts;
    private int retcode = 0;
    private final Map<String, Command> commands;
    private static CommandProcessor instance;
    private String fileToWrite;
    private String fileToOverwrite;
    private CommandParser commandParser;

    public static CommandProcessor getInstance() {
        if (instance == null) {
            instance = new CommandProcessor();
        }
        return instance;
    }

    private CommandProcessor() {
        commands = new TreeMap<>();
        Command bash = new PwdCommand();
        commands.put(bash.getName(), bash);
        bash = new BashCommand();
        commands.put(bash.getName(), bash);
        bash = new CatCommand();
        commands.put(bash.getName(), bash);
        bash = new EchoCommand();
        commands.put(bash.getName(), bash);
        bash = new TrueCommand();
        commands.put(bash.getName(), bash);
        bash = new FalseCommand();
        commands.put(bash.getName(), bash);
        bash = new RetcodeCommand();
        commands.put(bash.getName(), bash);
        bash = new DollarCommand();
        commands.put(bash.getName(), bash);
        bash = new ExitCommand();
        commands.put(bash.getName(), bash);
        bash = new WriterCommand();
        commands.put(bash.getName(), bash);
        bash = new OverwriteCommand();
        commands.put(bash.getName(), bash);
        bash = new WdCommand();
        commands.put(bash.getName(), bash);
    }

    public void execute() {
        Context c = new Context(new File(".").getAbsoluteFile());
        LocalVariables localVariables = new LocalVariables();
        scripts = new ArrayList<>();
        boolean result = true;
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            int count = 0;
             if (scripts.size() > 0) {
                 commandParser = new CommandParser(scripts.get(0));
                 scripts.remove(0);
             } else {
                 System.out.print("> ");
                 input = scanner.nextLine();
                 commandParser = new CommandParser(input);
             }

            for (var item : commandParser.getCommandsWithArgs()) {
                localVariables.add(item);
                localVariables.findDollar(item);
                commandParser.splitCommandWithArgs(localVariables.replace(item));
                if (commandParser.getCommand() == null || "".equals(commandParser.getCommand())) {
                    continue;
                }
                if (commandParser.getConnectors().size() > 0
                        && commandParser.getConnectors().get(count).equals("&&")
                        && retcode == 1) {
                    continue;
                }
                if (commandParser.getConnectors().size() > 0
                        && commandParser.getConnectors().get(count).equals("||")
                        && retcode == 0) {
                    continue;
                }
                Command cmd = commands.get(commandParser.getCommand().toUpperCase());
                if (cmd == null) {
                    System.out.println(MSG_COMMAND_NOT_FOUND);
                    continue;
                }
                redirect(c, commandParser.getArgs());
                result = cmd.execute(c, commandParser.getArgs());
                count++;
            }
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        } while (result);
    }

    public void redirect(Context context, String... args) {
        if (getParsedCommand().getRedirection().size() == 1) {
            Command cmd = getCommands().get(
                    getParsedCommand().getRedirection().get(0)
            );
            cmd.execute(context, args);
        }
    }

    public String getFileToWrite() {
        return fileToWrite;
    }

    public String getFileToOverwrite() {
        return fileToOverwrite;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public CommandParser getParsedCommand() {
        return commandParser;
    }

    public ArrayList<String> getScripts() {
        return scripts;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public void setFileToWrite(String fileToWrite) {
        this.fileToWrite = fileToWrite;
    }

    public void setFileToOverwrite(String fileToOverwrite) {
        this.fileToOverwrite = fileToOverwrite;
    }


}