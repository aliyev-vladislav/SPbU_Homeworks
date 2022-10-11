package commands;

import controller.Context;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PwdCommandTest {
    private PwdCommand pwdCommand;
    private Context context;
    private ByteArrayOutputStream output;
    private PrintStream old;

    @BeforeEach
    public void setUp() {
        pwdCommand = new PwdCommand();
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
    public void print_ShouldPrintCurrentDirectory() {
        File curDir = context.getCurrentDirectory();
        File[] files = curDir.listFiles();
        String expectedOutput = "";
        assert files != null;
        for (var item : files) {
            expectedOutput += item.getName() + "\n";
        }
        pwdCommand.print(curDir);

        assertEquals(expectedOutput, output.toString());
    }
}
