package parser;

import controller.CommandProcessor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private String command;
    private final ArrayList<String> commandsWithArgs;
    private String[] args;
    private Pattern pattern;
    private Matcher matcher;
    private String tempLine;
    private final ArrayList<String> connectors;
    private final ArrayList<String> redirection;
    private final CommandProcessor commandProcessor;
    public CommandParser(String line) {
        this.commandProcessor = CommandProcessor.getInstance();
        this.commandsWithArgs = new ArrayList<>();
        this.connectors = new ArrayList<>();
        this.redirection = new ArrayList<>();
        this.connectors.add(" ");
        this.pattern = Pattern.compile(".*?(&&|;|\\|\\|)|.*");
        this.matcher = pattern.matcher(line);
        while (matcher.find()) {
            String substring = matcher.group();
            if (substring.contains(";")) {
                tempLine = substring.replace(";", "").trim();
                connectors.add(";");
            } else if (substring.contains("&&")) {
                tempLine = substring.replace("&&", "").trim();
                connectors.add("&&");
            } else if (substring.contains("||")) {
                tempLine = substring.replace("||", "").trim();
                connectors.add("||");
            } else {
                tempLine = substring.trim();
            }
            commandsWithArgs.add(tempLine);
        }
    }

    public String replaceWriterOrReader(String commandWithArgs) {
        this.pattern = Pattern.compile("(>>|>).+");
        this.matcher = pattern.matcher(commandWithArgs);
        while (matcher.find()) {
            String substring = matcher.group();
            if (substring.contains(">>")) {
                commandProcessor.setFileToOverwrite(
                        tempLine = substring.replace(">>", "").trim());
                commandWithArgs = commandWithArgs.replace(substring, "").trim();
                redirection.add(">>");
            } else if (substring.contains(">")) {
                commandProcessor.setFileToWrite(
                        tempLine = substring.replace(">", "").trim());
                commandWithArgs = commandWithArgs.replace(substring, "").trim();
                redirection.add(">");
            }
        }
        return commandWithArgs;
    }

    public void splitCommandWithArgs(String commandWithArgs) {
        Pattern pattern = Pattern.compile("\\s*(\\s)\\s*");
        String[] parts = pattern.split(replaceWriterOrReader(commandWithArgs));
        if (parts != null) {
            command = parts[0];
            if (parts.length > 1) {
                args = new String[parts.length - 1];
                System.arraycopy(parts, 1, args, 0, args.length);
            }
        }
    }

    public String getCommand() {
        return command;
    }

    public ArrayList<String> getCommandsWithArgs() {
        return commandsWithArgs;
    }

    public String[] getArgs() {
        return args;
    }

    public ArrayList<String> getConnectors() {
        return connectors;
    }

    public ArrayList<String> getRedirection() {
        return redirection;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}