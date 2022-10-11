package commands;

import controller.Context;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class RetcodeCommandTest {
    private RetcodeCommand retcodeCommand;
    private Context context;
    private String[] args;
    private ByteArrayOutputStream output;
    private PrintStream old;

    @BeforeEach
    public void setUp() {
        retcodeCommand = new RetcodeCommand();
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
    public void execute_ShouldPrintZero() {
        String expectedOutput = "0\n";
        retcodeCommand.execute(context, args);

        assertEquals(expectedOutput, output.toString());
    }
}