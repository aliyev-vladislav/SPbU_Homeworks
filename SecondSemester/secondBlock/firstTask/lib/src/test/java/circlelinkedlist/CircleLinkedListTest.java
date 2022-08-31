package circlelinkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static circlelinkedlist.CircleLinkedList.*;

class StringArrayConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {
        if (source instanceof String && String[].class.isAssignableFrom(targetType)) {
            return ((String) source).split("\\s*,\\s*");
        } else {
            throw new IllegalArgumentException();
        }
    }
}

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

public class CircleLinkedListTest {

    private CircleLinkedList<Integer> list;
    private Predicate<Integer> predicate;

    @BeforeEach
    public void setUp() {
        list = new CircleLinkedList<>();
    }

    @AfterEach
    public void tearDown() {
        list = null;
    }

    @ParameterizedTest
    @NullSource
    public void CircleLinkedList_InvalidArgument_ShouldThrowException
            (CircleLinkedList<?> list) {
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    var newList  = new CircleLinkedList<>(list);
                });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void CircleLinkedList_InitNewList_ShouldReturnListWithCopyElementsSpecifiedList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        for (var item : data) {
            list.addLast(item);
        }

        var newList = new CircleLinkedList<>(list);

        assertEquals(data.length, newList.getSize());

        for (var item : data) {
            assertTrue(newList.contains(item));
        }
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/testData2.csv", delimiter = '|')
    public void addAfter_addElementToEmptyList_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        Node<Integer> node = new Node<>(data[0]);
        Node<Integer> newNode = new Node<>(data[1]);

        NoSuchElementException thrown =
                Assertions.assertThrows(NoSuchElementException.class,
                        () -> list.addAfter(node, newNode));
    }

    @Test
    public void addAfter_InvalidArguments_ShouldThrowException() {
        Node<Integer> node = null;
        Node<Integer> newNode = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, () -> list.addAfter(node, newNode));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void addAfter_NotEmptyList_ShouldAddNewNodeAfterSpecifiedNode
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node1 = new Node<>(data[1]);
        var node2 = new Node<>(data[2]);
        var node3 = new Node<>(data[3]);

        list.addLast(data[0]);
        list.addAfter(list.getHead(), node1);
        list.addAfter(list.getHead().getNext(), node2);
        list.addAfter(list.getHead().getNext().getNext(), node3);

        assertEquals(data.length, list.getSize());

        var currentNode = list.getHead();
        for (var item : data) {
            assertEquals(item, currentNode.getDate());
            currentNode = currentNode.getNext();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    public void addAfter_InvalidArgument_ShouldThrowException(Integer data) {
        Node<Integer> node = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, () -> list.addAfter(node, data));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void addAfter_NotEmptyList_ShouldAddNewNodeWithSetDataAfterSpecifiedNode
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {

        list.addLast(data[0]);
        list.addAfter(list.getHead(), data[1]);
        list.addAfter(list.getHead().getNext(), data[2]);
        list.addAfter(list.getHead().getNext().getNext(), data[3]);

        assertEquals(data.length, list.getSize());

        var currentNode = list.getHead();
        for (var item : data) {
            assertEquals(item, currentNode.getDate());
            currentNode = currentNode.getNext();
        }
    }

    @Test
    public void addBefore_InvalidArgument_ShouldThrowException() {
        Node<Integer> node = null;
        Node<Integer> newNode = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, () -> list.addBefore(node, newNode));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData2.csv", delimiter = '|')
    public void addBefore_addElementToEmptyList_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node = new Node<>(data[0]);
        var newNode = new Node<>(data[1]);
        NoSuchElementException thrown =
                Assertions.assertThrows(NoSuchElementException.class, () -> list.addBefore(node, newNode));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void addBefore_NotEmptyList_ShouldAddNewNodeBeforeSpecifiedNode
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newNode = new Node<>(data[1]);

        list.addLast(data[0]);
        list.addBefore(list.getHead(), newNode);

        assertEquals(newNode, list.getTail());
    }

    @Test
    public void addFirstNode_InvalidArgument_ShouldThrowException() {
        Node<Integer> node = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, () -> list.addFirst(node));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void addFirstNode_ShouldAddNewNodeToStartList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node0 = new Node<>(data[0]);
        var node1 = new Node<>(data[1]);
        var node2 = new Node<>(data[2]);

        list.addFirst(node0);
        list.addFirst(node1);
        list.addFirst(node2);

        var reverseList = CircleLinkedList.reverse(list);
        var currentNode = reverseList.getHead();
        for (var item : data) {
            assertEquals(item, currentNode.getDate());
            currentNode = currentNode.getNext();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void addFirstData_ShouldAddNewNodeToStartList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addFirst(data[0]);

        assertEquals(data[0], list.getHead().getDate());
    }

    @Test
    public void addLastNode_InvalidArgument_ShouldThrowException() {
        Node<Integer> node = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, () -> list.addLast(node));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void addLastNode_ShouldAddNewNodeToEndList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node0 = new Node<>(data[0]);
        var node1 = new Node<>(data[1]);
        var node2 = new Node<>(data[2]);

        list.addLast(node0);
        list.addLast(node1);
        list.addLast(node2);

        var currentNode = list.getHead();
        for (var item : data) {
            assertEquals(item, currentNode.getDate());
            currentNode = currentNode.getNext();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void addLastData_ShouldAddNewNodeToEndList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);

        assertEquals(data[0], list.getHead().getDate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void clear_NotEmptyList_ShouldReturnEmptyList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node0 = new Node<>(data[0]);
        var node1 = new Node<>(data[1]);
        var node2 = new Node<>(data[2]);

        list.addLast(node0);
        list.addLast(node1);
        list.addLast(node2);
        list.clear();

        assertEquals(0, list.getSize());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void equals_InvalidArgument_ShouldReturnFalse() {
        var newList = new LinkedList<Integer>();

        boolean result1 = list.equals(newList);
        boolean result2 = list.equals(null);

        assertFalse(result1);
        assertFalse(result2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData5.csv", delimiter = '|')
    public void equals_ComparisonListsWithDifferentElements_ShouldReturnFalse
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data1,
             @ConvertWith(IntegerArrayConverter.class) Integer[] data2) {
        var newList = new CircleLinkedList<Integer>();

        list.addLast(data1[0]);
        list.addLast(data1[1]);
        list.addLast(data1[2]);
        newList.addLast(data2[0]);
        newList.addLast(data2[1]);
        newList.addLast(data2[2]);

        boolean result = list.equals(newList);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData5.csv", delimiter = '|')
    public void equals_ComparisonListsWithSameElements_ShouldReturnTrue
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newList = new CircleLinkedList<Integer>();

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        newList.addLast(data[0]);
        newList.addLast(data[1]);
        newList.addLast(data[2]);

        boolean result = list.equals(newList);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv")
    public void contains_EmptyList_ShouldReturnFalse
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        boolean containsResult = list.contains(data[0]);

        assertFalse(containsResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv")
    public void contains_NotEmptyList_ShouldReturnTrue
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {

        list.addLast(data[0]);
        boolean containsResult = list.contains(data[0]);

        assertTrue(containsResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void copyTo_InvalidFirstArgument_ShouldThrowIllegalArgumentException
            (@ConvertWith(StringArrayConverter.class) String[] data) {

        var newList = new CircleLinkedList<String>();
        String[] array = null;

        newList.addLast(data[0]);
        newList.addLast(data[1]);
        newList.addLast(data[2]);

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> newList.copyTo(array, 1));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void copyTo_InvalidSecondArgument_ShouldThrowIndexOutOfBoundsException
            (@ConvertWith(StringArrayConverter.class) String[] data) {

        var newList = new CircleLinkedList<String>();

        newList.addLast(data[0]);
        newList.addLast(data[1]);
        newList.addLast(data[2]);

        IndexOutOfBoundsException thrown =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> newList.copyTo(data, -1));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void copyTo_InvalidArgument_ShouldThrowIllegalArgumentException
            (@ConvertWith(StringArrayConverter.class) String[] data) {

        var newList = new CircleLinkedList<String>();

        newList.addLast(data[0]);
        newList.addLast(data[1]);
        newList.addLast(data[2]);
        newList.addLast(data[2]);

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> newList.copyTo(data, 1));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void copyTo_NotEmptyList_CopiedArray
            (@ConvertWith(StringArrayConverter.class) String[] data) {

        var newList = new CircleLinkedList<String>();
        var array = new String[data.length];

        array[0] = data[0];
        newList.addLast(data[1]);
        newList.addLast(data[2]);
        newList.addLast(data[3]);
        newList.copyTo(array, 1);

        for (int count = 0; count < data.length; count++) {
            assertEquals(data[count], array[count]);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void hashCode_ShouldReturnIdenticalCodesOfLists
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list1 = new CircleLinkedList<String>();
        var list2 = new CircleLinkedList<String>();
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);

        list1.addLast(node1);
        list1.addLast(node2);
        list1.addLast(node3);
        list2.addLast(node1);
        list2.addLast(node2);
        list2.addLast(node3);

        assertEquals(list1.hashCode(), list2.hashCode());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void find_EmptyList_ShouldReturnNull
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list = new CircleLinkedList<String>();

        var result = list.find(data[0]);

        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData2.csv", delimiter = '|')
    public void find_NotEmptyListWithAbsentElement_ShouldReturnNull
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list = new CircleLinkedList<String>();
        list.addLast(data[0]);

        var result = list.find(data[1]);

        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void find_NotEmptyListWithIdenticalElement_ShouldReturnNode
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list = new CircleLinkedList<String>();
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        var result = list.find(data[1]);

        assertEquals(list.getHead().getNext(), result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void findLast_EmptyList_ShouldReturnNull
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list = new CircleLinkedList<String>();

        var result = list.findLast(data[0]);

        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData2.csv", delimiter = '|')
    public void findLast_NotEmptyListWithAbsentElement_ShouldReturnNull
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list = new CircleLinkedList<String>();
        list.addLast(data[0]);

        var result = list.findLast(data[1]);

        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void findLast_NotEmptyListWithLastIdenticalElement_ShouldReturnNode
            (@ConvertWith(StringArrayConverter.class) String[] data) {
        var list = new CircleLinkedList<String>();
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);
        var node4 = new Node<>(data[1]);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        var result = list.findLast(data[1]);

        assertEquals(node4, result);
    }

    @Test
    public void getType_ShouldReturnListType() {
        var result = list.getType();
        var actual = "class circlelinkedlist.CircleLinkedList";

        assertEquals(actual, result.toString());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void removeNode_InvalidArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node = new Node<>(data[0]);
        Node<Integer> newNode = null;
        list.addLast(node);

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> list.remove(newNode));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void removeNode_EmptyList_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node = new Node<>(data[0]);

        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> list.remove(node));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void removeNode_NodeAbsentInlist_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);
        var node4 = new Node<>(data[3]);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);


        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> list.remove(node4));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void removeNode_NotEmptyList_ShouldRemoveSpecifiedNode
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);
        var node4 = new Node<>(data[3]);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        list.remove(node3);

        assertFalse(list.contains(node3));
        assertEquals(data.length - 1, list.getSize());
        assertEquals(list.getHead(), node1);
        assertEquals(list.getHead().getNext(), node2);
        assertEquals(list.getHead().getNext().getNext(), node4);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void removeData_EmptyList_ShouldReturnFalse
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        boolean result = list.remove(data[0]);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void removeData_ElementNotFound_ShouldReturnFalse
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        boolean result = list.remove(data[3]);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void removeData_NotEmptyList_ShouldReturnTrue
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);
        var node4 = new Node<>(data[3]);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        boolean result = list.remove(data[2]);

        assertTrue(result);
        assertFalse(list.contains(node3));
        assertEquals(data.length - 1, list.getSize());
        assertEquals(list.getHead(), node1);
        assertEquals(list.getHead().getNext(), node2);
        assertEquals(list.getHead().getNext().getNext(), node4);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void removeFirst_EmptyList_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node = new Node<>(data[0]);

        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> list.removeFirst());
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void removeFirst_NotEmptyList_ShouldRemoveHeadOfList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);
        var node4 = new Node<>(data[3]);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        list.removeFirst();

        assertFalse(list.contains(node1));
        assertEquals(data.length - 1, list.getSize());
        assertEquals(list.getHead(), node2);
        assertEquals(list.getHead().getNext(), node3);
        assertEquals(list.getTail(), node4);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void removeLast_EmptyList_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node = new Node<>(data[0]);

        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> list.removeLast());
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void removeLast_NotEmptyList_ShouldRemoveTailOfList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var node1 = new Node<>(data[0]);
        var node2 = new Node<>(data[1]);
        var node3 = new Node<>(data[2]);
        var node4 = new Node<>(data[3]);

        list.addLast(node1);
        list.addLast(node2);
        list.addLast(node3);
        list.addLast(node4);

        list.removeLast();

        assertFalse(list.contains(node4));
        assertEquals(data.length - 1, list.getSize());
        assertEquals(list.getHead(), node1);
        assertEquals(list.getHead().getNext(), node2);
        assertEquals(list.getTail(), node3);
    }

    @Test
    public void any_InvalidArgument_ShouldThrowException() {
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> any(list));
    }

    @Test
    public void any_EmptyList_ShouldReturnFalse() {
        boolean result = any(list);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void any_NotEmptyList_ShouldReturnTrue
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);

        boolean result = any(list);

        assertTrue(result);
    }

    @Test
    public void anyWithPredicate_InvalidFirstArgument_ShouldThrowException() {
        Predicate<Integer> predicate = item -> item < 0;
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> any(list, predicate));
    }

    @Test
    public void anyWithPredicate_InvalidSecondArgument_ShouldThrowException() {
        predicate = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> any(list, predicate));
    }

    @Test
    public void anyWithPredicate_EmptyList_ShouldTReturnFalse() {
        predicate = item -> item < 0;

        boolean result = any(list, predicate);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void any_NoElementSatisfiesPredicate_ShouldTReturnFalse
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        predicate = item -> item < 0;
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        boolean result = any(list, predicate);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void any_AtLeastOneElementSatisfiesPredicate_ShouldTReturnTrue
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        predicate = item -> item > 0;
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        boolean result = any(list, predicate);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void append_InvalidArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> append(list, data[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void append_EmptyList_ShouldReturnListWithOneElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newList = append(list, data[0]);

        assertEquals(data.length, newList.getSize());
        assertEquals(data[0], newList.getTail().getDate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void append_NotEmptyList_ShouldReturnListWithSpecifiedElementAtEnd
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        var newList = append(list, data[3]);

        assertEquals(data.length, newList.getSize());
        assertEquals(data[3], newList.getTail().getDate());
    }

    @Test
    public void count_InvalidArgument_ShouldThrowException() {
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> count(list));
    }

    @Test
    public void count_EmptyList_ShouldReturnZero() {
        int result = count(list);

        assertEquals(0, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void count_NotEmptyList_ShouldReturnNumberOfElementsInList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = count(list);

        assertEquals(list.getSize(), result);
        assertEquals(data.length, result);
    }

    @Test
    public void countWithPredicate_InvalidFirstArgument_ShouldThrowException() {
        predicate = item -> item > 0;
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> count(list, predicate));
    }

    @Test
    public void countWithPredicate_InvalidSecondArgument_ShouldThrowException() {
        predicate = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> count(list, predicate));
    }


    @Test
    public void countWithPredicate_EmptyList_ShouldReturnZero() {
        predicate = item -> item > 0;
        int result = count(list, predicate);

        assertEquals(0, result);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void countWithPredicate_NotEmptyList_ShouldReturnNumberOfElementsInList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        predicate = item -> item >= 0;
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = count(list, predicate);

        assertEquals(data.length, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void countWithPredicate_NotEmptyList_ShouldReturnZero
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        predicate = item -> item < 0;
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = count(list, predicate);

        assertEquals(0, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void elementAt_InvalidFirstArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> elementAt(list, data[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData6.csv", delimiter = '|')
    public void elementAt_InvalidSecondArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {

        IndexOutOfBoundsException thrown =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> elementAt(list, data[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void elementAt_NotEmptyList_ShouldReturnElementAtSpecifiedIndex
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = elementAt(list, 2);

        assertEquals(data[2], result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void elementAtOrDefault_InvalidFirstArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> elementAtOrDefault(list, data[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData6.csv", delimiter = '|')
    public void elementAtOrDefault_InvalidSecondArgument_ShouldReturnNull
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var result = elementAtOrDefault(list, data[0]);
        assertNull(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void elementAtOrDefault_NotEmptyList_ShouldReturnElementAtSpecifiedIndex
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = elementAtOrDefault(list, 2);

        assertEquals(data[2], result);
    }

    @Test
    public void first_InvalidArgument_ShouldThrowException() {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> first(list));
    }

    @Test
    public void first_EmptyList_ShouldThrowException() {
        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> first(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void first_NotEmptyList_ShouldReturnFirstElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = first(list);

        assertEquals(data[0], result);
    }

    @Test
    public void firstOrDefault_InvalidArgument_ShouldThrowException() {
        list = null;
        int defaultValue = 0;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> firstOrDefault(list, defaultValue));
    }

    @Test
    public void firstOrDefault_EmptyList_ShouldReturnDefaultValue() {
        int defaultValue = 0;
        int result = firstOrDefault(list, 0);

        assertEquals(defaultValue, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void firstOrDefault_NotEmptyList_ShouldReturnFirstElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        int defaultValue = 0;

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = firstOrDefault(list, defaultValue);

        assertEquals(data[0], result);
    }

    @Test
    public void last_InvalidArgument_ShouldThrowException() {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> last(list));
    }

    @Test
    public void last_EmptyList_ShouldThrowException() {
        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> last(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void last_NotEmptyList_ShouldReturnLastElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = last(list);

        assertEquals(data[3], result);
    }

    @Test
    public void lastOrDefault_InvalidArgument_ShouldThrowException() {
        list = null;
        int defaultValue = 0;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> lastOfDefault(list, defaultValue));
    }

    @Test
    public void lastOrDefault_EmptyList_ShouldReturnDefaultValue() {
        int defaultValue = 0;
        int result = firstOrDefault(list, defaultValue);

        assertEquals(defaultValue, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void lastOrDefault_NotEmptyList_ShouldReturnFirstElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        int defaultValue = 0;

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = lastOfDefault(list, defaultValue);

        assertEquals(data[3], result);
    }

    @Test
    public void max_InvalidArgument_ShouldThrowException() {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> max(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void max_NotEmptyList_ShouldReturnMaxElementInList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        int maxValue = 99999999;

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(maxValue);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = max(list);

        assertEquals(maxValue, result);
    }

    @Test
    public void min_InvalidArgument_ShouldThrowException() {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> min(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void min_NotEmptyList_ShouldReturnMaxElementInList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        int minValue = -99999999;

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(minValue);
        list.addLast(data[2]);
        list.addLast(data[3]);

        int result = min(list);

        assertEquals(minValue, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void prepend_InvalidArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> prepend(list, data[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData4.csv", delimiter = '|')
    public void prepend_EmptyList_ShouldReturnListWithOneElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newList = prepend(list, data[0]);

        assertEquals(data.length, newList.getSize());
        assertEquals(data[0], newList.getHead().getDate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void prepend_NotEmptyList_ShouldReturnListWithSpecifiedElementAtStart
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        var newList = prepend(list, data[3]);

        assertEquals(data.length, newList.getSize());
        assertEquals(data[3], newList.getHead().getDate());
    }

    @Test
    public void reverse_InvalidArgument_ShouldThrowException() {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> reverse(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void reverse_NotEmptyList_ShouldReturnListOfElementsInReverseOrder
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        var newList = reverse(list);

        assertEquals(list.getSize(), newList.getSize());
        for (var item : data) {
            assertTrue(newList.contains(item));
        }
    }

    @Test
    public void sequenceEqual_InvalidArgument_ShouldThrowException() {
        CircleLinkedList<Integer> newList = null;
        IllegalArgumentException thrown1 =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> sequenceEqual(newList, list));
        IllegalArgumentException thrown2 =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> sequenceEqual(list, newList));
    }

    @Test
    public void sequenceEqual_BothSequencesEmpty_ShouldReturnTrue() {
        var newList = new CircleLinkedList<Integer>();

        boolean result = sequenceEqual(list, newList);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void sequenceEqual_DifferentSequences_ShouldReturnFalse
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newList = new CircleLinkedList<Integer>();

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        newList.addLast(data[0]);
        newList.addLast(data[1]);
        newList.addLast(data[1]);

        boolean result = sequenceEqual(list, newList);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void sequenceEqual_IdenticalSequences_ShouldReturnTrue
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newList = new CircleLinkedList<Integer>();

        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        newList.addLast(data[0]);
        newList.addLast(data[1]);
        newList.addLast(data[2]);

        boolean result = sequenceEqual(list, newList);

        assertTrue(result);
    }

    @Test
    public void single_InvalidArgument_ShouldThrowException() {
        list = null;
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> single(list));
    }

    @Test
    public void single_EmptyList_ShouldThrowException() {
        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> single(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void single_ListWithMoreThanOneElement_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);
        list.addLast(data[1]);
        list.addLast(data[2]);

        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class,
                        () -> single(list));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData3.csv", delimiter = '|')
    public void single_ListWithOneElement_ShouldReturnSingleListElement
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list.addLast(data[0]);

        int result = single(list);

        assertEquals(list.getHead().getDate(), result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData6.csv", delimiter = '|')
    public void take_InvalidArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> take(list, data[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData6.csv", delimiter = '|')
    public void take_EmptyList_ShouldReturnNewEmptyList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] count) {

        CircleLinkedList<Integer> newList = take(list, count[0]);

        assertEquals(0, newList.getSize());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData7.csv", delimiter = '|')
    public void take_NotEmptyList_ShouldCopyListWithSpecifiedNumberOfElements
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data,
             @ConvertWith(IntegerArrayConverter.class) Integer[] count) {

        var lists = new CircleLinkedList[count.length];
        for (var item : data) {
            list.addLast(item);
        }
        for (int i = 0; i < count.length; i++) {
            lists[i] = take(list, count[i]);
        }
        for (int i = 0; i < count.length; i++) {
            assertEquals(count[i], lists[i].getSize());
            assertEquals(data[0], lists[i].getHead().getDate());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData6.csv", delimiter = '|')
    public void takeLast_InvalidArgument_ShouldThrowException
            (@ConvertWith(IntegerArrayConverter.class) Integer[] count) {
        list = null;

        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> takeLast(list, count[0]));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData6.csv", delimiter = '|')
    public void takeLast_EmptyList_ShouldReturnNewEmptyList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] count) {

        CircleLinkedList<Integer> newList = takeLast(list, count[0]);

        assertEquals(0, newList.getSize());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData7.csv", delimiter = '|')
    public void takeLast_NotEmptyList_ShouldCopyListWithSpecifiedNumberOfElements
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data,
             @ConvertWith(IntegerArrayConverter.class) Integer[] count) {

        var lists = new CircleLinkedList[count.length];
        for (var item : data) {
            list.addLast(item);
        }
        for (int i = 0; i < count.length; i++) {
            lists[i] = takeLast(list, count[i]);
        }
        for (int i = 0; i < count.length; i++) {
            assertEquals(count[i], lists[i].getSize());
            assertEquals(data[3], lists[i].getTail().getDate());
        }
    }

    @Test
    public void concat_InvalidArgument_ShouldThrowException() {
        CircleLinkedList<Integer> newList = null;
        IllegalArgumentException thrown1 =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> concat(newList, list));
        IllegalArgumentException thrown2 =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> concat(list, newList));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData5.csv", delimiter = '|')
    public void concat_EmptyLists_ShouldReturnEmptyList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data1,
             @ConvertWith(IntegerArrayConverter.class) Integer[] data2) {
        var newList = new CircleLinkedList<Integer>();

        var resultList = concat(list, newList);

        assertEquals(0, resultList.getSize());
        assertNull(resultList.getHead());
        assertNull(resultList.getTail());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData1.csv", delimiter = '|')
    public void concat_FirstListIsEmpty_ShouldReturnCopyOfSecondList
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data) {
        var newList = new CircleLinkedList<Integer>();

        for (var item : data) {
            newList.addLast(item);
        }

        var resultList = concat(list, newList);

        assertEquals(newList.getSize(), resultList.getSize());
        assertEquals(newList.getHead().getDate(),
                resultList.getHead().getDate());
        assertEquals(newList.getTail().getDate(),
                resultList.getTail().getDate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData5.csv", delimiter = '|')
    public void concat_NotEmptyLists_ShouldReturnConcatedLists
            (@ConvertWith(IntegerArrayConverter.class) Integer[] data1,
             @ConvertWith(IntegerArrayConverter.class) Integer[] data2) {
        var newList = new CircleLinkedList<Integer>();

        for (var item : data1) {
            list.addLast(item);
        }
        for (var item : data2) {
            newList.addLast(item);
        }

        var resultList = concat(list, newList);

        assertEquals(data1.length + data2.length,
                resultList.getSize());
        assertEquals(list.getHead().getDate(),
                resultList.getHead().getDate());
        assertEquals(newList.getTail().getDate(),
                resultList.getTail().getDate());
    }
}
