package commands;

import controller.Context;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TrueAndFalseCommandTest {
    private TrueCommand trueCommand;
    private FalseCommand falseCommand;
    private Context context;
    private String[] args;
    private ByteArrayOutputStream output;
    private PrintStream old;

    @BeforeEach
    public void setUp() {
        trueCommand = new TrueCommand();
        falseCommand = new FalseCommand();
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
    public void execute_True_ShouldPrintZero() {
        String expectedOutput = "0\n";
        trueCommand.execute(context, args);

        assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void execute_False_ShouldPrintOne() {
        String expectedOutput = "1\n";
        falseCommand.execute(context, args);
        assertEquals(expectedOutput, output.toString());
    }
}