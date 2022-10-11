package parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    private CommandParser commandParser;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void cleanUpStreams() {
    }

    @Test
    public void CommandParser_LineWithConnectors_ShouldAddFoundCommandsToArrayList() {
        String inputLine = "echo 123 || cat file.txt && pwd ; exit";
        commandParser = new CommandParser(inputLine);

        assertEquals("echo 123", commandParser.getCommandsWithArgs().get(0));
        assertEquals("cat file.txt", commandParser.getCommandsWithArgs().get(1));
        assertEquals("pwd", commandParser.getCommandsWithArgs().get(2));
        assertEquals("exit", commandParser.getCommandsWithArgs().get(3));
    }

    @Test
    public void replaceWriterOrReader_WithoutRedirection_ShouldReturnInitialLine() {
        String initialLine = "wd file.txt";
        commandParser = new CommandParser(initialLine);

        String result = commandParser.replaceWriterOrReader(initialLine);

        assertEquals(initialLine, result);
    }

    @Test
    public void replaceWriterOrReader_WithRedirection_ShouldReturnLineWithOutRedirection() {
        String initialLine = "wd file.txt > file.txt";
        String expectedResult = "wd file.txt";
        commandParser = new CommandParser(initialLine);

        String result = commandParser.replaceWriterOrReader(initialLine);

        assertEquals(expectedResult, result);
    }

    @Test
    public void splitCommandWithArgs_CommandsSeparatedBySpaces_ShouldSplitIntoCommandAndArrayArgs() {
        String inputLine = "echo cat  dog    234  rabbit fish  ";
        String expectedCommand = "echo";
        String[] expectedArgs = new String[] {"cat", "dog", "234", "rabbit", "fish"};
        commandParser = new CommandParser(inputLine);
        commandParser.splitCommandWithArgs(inputLine);

        assertEquals(expectedCommand, commandParser.getCommand());
        assertEquals(expectedArgs[0], commandParser.getArgs()[0]);
        assertEquals(expectedArgs[1], commandParser.getArgs()[1]);
        assertEquals(expectedArgs[2], commandParser.getArgs()[2]);
        assertEquals(expectedArgs[3], commandParser.getArgs()[3]);
        assertEquals(expectedArgs[4], commandParser.getArgs()[4]);
    }

}