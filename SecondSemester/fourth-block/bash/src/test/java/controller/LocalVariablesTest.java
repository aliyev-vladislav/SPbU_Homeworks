package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocalVariablesTest {
    private LocalVariables lv;

    @BeforeEach
    public void setUp() {
        lv = new LocalVariables();
    }

    @Test
    public void add_ShouldPutLocalVariablesInDataStructure() {
        String line = "echo a=5 b=23 c=data";
        lv.add(line);

        assertEquals(lv.getLocalVariables().get("a"), "5");
        assertEquals(lv.getLocalVariables().get("b"), "23");
        assertEquals(lv.getLocalVariables().get("c"), "data");
    }

    @Test
    public void replace_ShouldReplaceLocalVariablesByTheirValue() {
        lv.add("a=5 b=-123 c=hello");
        String commandWithArgs = "echo $b $a $c";
        String expectedResult = "echo -123 5 hello";

        String result = lv.replace(commandWithArgs);

        assertEquals(expectedResult, result);
    }

}