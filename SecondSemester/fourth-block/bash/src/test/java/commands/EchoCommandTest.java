package commands;

import controller.Context;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class EchoCommandTest {
    private EchoCommand echoCommand;
    private Context context;
    private String[] args;
    private ByteArrayOutputStream output;
    private PrintStream old;
    @BeforeEach
    public void setUp() {
        echoCommand = new EchoCommand();
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
    public void execute_NoArgs_ShouldPrintEmptyLine() {
        echoCommand.execute(context, args);
        String emptyLine = "\n";

        assertEquals(emptyLine, output.toString());
    }

    @Test
    public void execute_WithSomeArgs_ShouldPrintArgs() {
        args = new String[] {"23", "asdf", "@#echo"};
        echoCommand.execute(context, args);
        String expectedOutput = "23 asdf @#echo \n";

        assertEquals(expectedOutput, output.toString());
    }


}
