package commands;

import controller.CommandProcessor;
import controller.Context;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class BashCommandTest {
    BashCommand bashCommand;
    private Context context;
    private String[] args;
    private ByteArrayOutputStream output;
    private PrintStream old;

    private CommandProcessor commandProcessor;

    @BeforeEach
    public void setUp() {
        commandProcessor = CommandProcessor.getInstance();
        bashCommand = new BashCommand();
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
    public void execute_NoArgs_ShouldPrintAnErrorMessage() {
        bashCommand.execute(context, args);
        String message = "Script not found\n";

        assertEquals(message, output.toString());
    }

    @Test
    public void execute_WithSomeArgs_ShouldReturnTrue() {
        args = new String[] {"file.txt"};

        boolean result = bashCommand.execute(context, args);

        assertTrue(result);
    }

    @Test
    public void addScript_ScriptFileNotFound_ShouldPrintAnErrorMessage() {
        args = new String[] {"file.txt"};
        String message = "Script not found\n";

        bashCommand.addScript(args[0]);

        assertEquals(message, output.toString());
    }
}
