package commands;

import controller.Command;
import controller.CommandProcessor;
import controller.Context;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WdCommand implements Command {
    CommandProcessor commandProcessor;

    @Override
    public boolean execute(Context context, String... args) {
        commandProcessor = CommandProcessor.getInstance();
        if (args == null) {
            System.out.println("No such file or directory");
            commandProcessor.setRetcode(1);
        } else {
            for (String file : args) {
                printData(file);
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "WD";
    }

    public void printData(String fileName) {
        try {
            File fileReader = new File(fileName);
            Stream<String> lines = Files.lines(Paths.get(fileName), UTF_8);
            Stream<String> lines2 = Files.lines(Paths.get(fileName), UTF_8);
            long linesCount = lines.count();
            long wordsCount = lines2
                    .flatMap(s -> Stream.of(s.split("\\w+")).filter(t -> !t.isEmpty()))
                    .count();
            System.out.println(linesCount + " "
                    + wordsCount + " "
                    + fileReader.length() + " "
                    + fileName);
            commandProcessor.setRetcode(0);
        } catch (IOException e) {
            System.out.println("wd: " + fileName + ": No such file");
            commandProcessor.setRetcode(1);
        }
    }
}
