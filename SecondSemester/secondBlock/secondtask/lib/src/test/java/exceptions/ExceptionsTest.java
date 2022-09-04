package exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static exceptions.Exceptions.*;
import static org.junit.jupiter.api.Assertions.*;

class IntegerArrayConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context)
            throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException();
        }
        try {
            String[] arrayString = ((String) source).split("\\s*,\\s*");
            Integer[] arrayInteger = new Integer[arrayString.length];
            for (int i = 0; i < arrayString.length; i++) {
                arrayInteger[i] = Integer.parseInt(arrayString[i]);
            }
            return arrayInteger;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}

public class ExceptionsTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv")
    void IntegerArrayConverter_InvalidStringFormat_ShouldReturnNull(String data) {
        var result = IntegerArrayConverter(data);

        assertNull(result);
    }

    @Test
    void readFile_FileDoesNotExist_ShouldReturnFalse() {
        boolean result = readFile();

        assertFalse(result);
    }

    @Test
    void createInstance_PrivateConstructor_ShouldReturnNull() {
        var result = createInstance();

        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData2.csv")
    void calculateFibNumber_OverflowInteger_ShouldReturnMinusOne
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var result = calculateFibNumber(data[0]);

        assertEquals(-1, result);
    }

    @Test
    void cloneInstance_NotImplementsInterfaceCloneable_ShouldReturnNull() {
        Person person = new Person("Jack");

        var result = cloneInstance(person);

        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    void addList_ImmutableList_ShouldReturnUnmodifiedList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        List list = new LinkedList();

        list.add(data[0]);
        list.add(data[1]);
        list.add(data[2]);

        List newList = List.copyOf(list);

       addList(newList, data[3]);

       assertEquals(data.length - 1, list.size());
       for (int index = 0; index < data.length - 1; index++) {
           assertEquals(list.get(index), data[index]);
       }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    void convertorObjectToString_ArrayOfIncompatibleTypes_ShouldReturnNull
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var result = convertorObjectToString(data);

        assertNull(result);
    }


    @Test
    void convertType_IncompatibleTypes_ShouldReturnNull() {
        Animal animal = new Animal();

        var result = convertType(animal);

        assertNull(result);
    }

    @Test
    void startThread_InterruptThread() {
        String consoleOutput = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(200);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            startThread();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
            System.out.println(originalOut);
        } catch (InterruptedException exc) {
            exc.printStackTrace(System.out);
        }

        assertEquals("#9 Interrupt a thread during execution.\r\n", consoleOutput);
    }
}
