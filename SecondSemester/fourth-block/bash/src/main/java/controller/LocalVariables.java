package controller;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalVariables {
    private final CommandProcessor commandProcessor;
    private final TreeMap<String, String> localVariables;
    private Pattern pattern;
    private Matcher matcher;

    public LocalVariables() {
        commandProcessor = CommandProcessor.getInstance();
        localVariables = new TreeMap<>();
    }

    public void add(String commandWithArgs) {
        this.pattern = Pattern.compile("\\w+=\\w+");
        this.matcher = pattern.matcher(commandWithArgs);
        while (matcher.find()) {
            String substring = matcher.group();
            String[] parts = substring.split("=");
            localVariables.put(parts[0], parts[1]);
        }
    }

    public String replace(String commandWithArgs) {
        this.pattern = Pattern.compile("\\$\\w+");
        this.matcher = pattern.matcher(commandWithArgs);
        while (matcher.find()) {
            String substring = matcher.group();
            String variable = substring.replace("$", "");
            String tempLine;
            if (localVariables.get(variable) == null) {
                tempLine = substring.replace(substring, "");
            } else {
                tempLine = substring.replace(substring, localVariables.get(variable));
            }
            commandWithArgs = commandWithArgs.replace(substring, tempLine);
        }
        return commandWithArgs;
    }

    public void findDollar(String commandWithArgs) {
        Pattern pattern = Pattern.compile("\\s*(\\s)\\s*");
        String[] parts = pattern.split(commandWithArgs);
        if (parts != null && parts[0].indexOf("$") == 0) {
            commandProcessor.getParsedCommand().setCommand("$");
        } else {
            commandProcessor.getParsedCommand().setCommand(null);
        }
    }
}
