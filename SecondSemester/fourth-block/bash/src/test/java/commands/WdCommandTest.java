package commands;

import controller.Context;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class WdCommandTest {
    private WdCommand wdCommand;
    private Context context;
    private String[] args;
    private ByteArrayOutputStream output;
    private PrintStream old;

    private static <T> Predicate<T> predicate(Predicate<T> predicate) {
        return predicate;
    }

    @BeforeEach
    public void setUp() {
        wdCommand = new WdCommand();
        context = new Context(new File(".").getAbsoluteFile());
        output = new ByteArrayOutputStream();
        old = System.out;
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(old);
    }

    @Test
    public void execute_NoArgs_ShouldPrintErrorMessage() {
        String message = "No such file or directory\n";
        wdCommand.execute(context, args);

        assertEquals(message, output.toString());
    }

    @Test
    public void printData_FileNotFound_ShouldPrintErrorMessage() {
        args = new String[] {"file.txt"};
        String message = "wd: " + args[0] + ": No such file\n";

        wdCommand.printData(args[0]);

        assertEquals(message, output.toString());
    }

    @Test
    public void printData_FileFound_ShouldPrintNumberOfLinesWordsAndBytesInFiles() {
        String fileName = "src/test/resources/test-files/wdFile.txt";
        wdCommand.printData(fileName);
        try {
            File file = new File(fileName);
            Stream<String> lines = Files.lines(Paths.get(fileName));
            int[] result = lines
                    .map(s -> Stream.of(s.split("\\W+")).
                            filter(t -> !t.isEmpty())
                            .toArray()
                    )
                    .map(ss -> new int[]{1, ss.length})
                    .reduce(new int[2], (arr1, arr2) -> {
                        arr1[0] += arr2[0];
                        arr1[1] += arr2[1];
                        return arr1;
                    });
        String expectedOutput = result[0] + " "
                + result[1] + " "
                + file.length() + " "
                + fileName + "\n";
            assertEquals(expectedOutput, output.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
