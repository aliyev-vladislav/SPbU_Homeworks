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

public class CatCommandTest {
    private CatCommand catCommand;
    private Context context;
    private String[] args;
    private ByteArrayOutputStream output;
    private PrintStream old;
    private CommandProcessor commandProcessor;

    @BeforeEach
    public void setUp() {
        commandProcessor = CommandProcessor.getInstance();
        catCommand = new CatCommand();
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
        catCommand.execute(context, args);
        String message = "No such file or directory\n";

        assertEquals(message, output.toString());
    }

    @Test
    public void execute_WithSomeArgs_ShouldReturnTrue() {
        args = new String[] {"catFile1.txt", "catFile2.txt"};

        boolean result = catCommand.execute(context, args);

        assertTrue(result);
    }

    @Test
    public void printFile_FileNotFound_ShouldPrintAnErrorMessage() {
        catCommand.printFile("file.txt");
        String message = "cat: file.txt: No such file\n";

        assertEquals(message, output.toString());
    }

    @Test
    public void printFile_FileFound_ShouldPrintContentsOfFile() {
        catCommand.printFile("src/test/resources/test-files/catFile1.txt");
        String contents = "213123\nqwerty\nhi hi hi ; 3334\n";

        assertEquals(contents, output.toString());
    }
}
