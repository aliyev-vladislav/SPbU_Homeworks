package exceptions;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.addExact;

class Person {
    private String name;

    private Person() {

    }
    public Person(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class MyThread extends Thread {
    MyThread(String name) {
        super(name);
    }

    // thread in standby mode
    public void run() {
        System.out.printf("%s started... \n",
                getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: " + getName()
                    + " has been interrupted");
        }
    }
}

class Animal {

}

class Dog extends Animal {

}

/*
 * In this class implements methods in
 * which non-trivial exceptional situations
 * occur, each of the methods contains
 * special blocks for handling these situations.
 */
public class Exceptions {
    public static int[] IntegerArrayConverter(String str) {
        System.out.println("#1 Convert an invalid format " +
                "string to an array of integers.");
        String[] strArray = str.split(" ");
        int[] intArray = new int[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            try{
                intArray[i] = Integer.parseInt(strArray[i]);
            } catch (NumberFormatException exc) {
                exc.printStackTrace(System.out);
                System.out.println("Details: invalid conversion");
                System.out.println("Result of the call: " + null);
                return null;
            }
        }
        return intArray;
    }

    public static boolean readFile() {
        System.out.println("#2 Read from a" +
                "non-existent file");
        File file = new File("file.txt");
        try (FileReader reader = new FileReader(file)) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                System.out.println((char) symbol);
            }
        } catch (FileNotFoundException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: "
                    + exc.getMessage());
            return false;
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
            System.err.println("IOException: "
                    + exc.getMessage());
            return false;
        }
        return true;
    }

    public static Person createInstance() {
        System.out.println("#3 Create an instance of a class " +
                "with private constructor using reflection.");
        Person person = null;
        try {
            person = Person.class.newInstance();
        } catch (InstantiationException
                 | IllegalAccessException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: attempt " +
                    "to create an instance of a class " +
                    "with a private constructor");
        }
        System.out.println("Result of the call: " + person);
        return person;
    }

    public static int calculateFibNumber(int n) {
        System.out.println("#4 Calculate Fibonacci numbers " +
                "overflow the size of int.");
        if (n < 0) {
            return 0;
        }
        int first = 0;
        int second = 1;
        int result = n;

        for (int i = 1; i < n; i++) {
            try {
                result = addExact(first, second);
            } catch (ArithmeticException exc) {
                exc.printStackTrace(System.out);
                System.out.println("Details: " +
                        exc.getMessage());
                result = -1;
                System.out.println("Result of the call: " + result);
                return result;
            }
            first = second;
            second = result;
        }
        result = -1;
        System.out.println("Result of the call: " + result);
        return result;
    }

    public static Person cloneInstance(Person obj) {
        System.out.println("#5 Clone an instance of a class that " +
                "does not implement the Cloneable interface.");
        Person clonePerson = null;
        try {
            clonePerson = (Person) obj.clone();
        } catch (CloneNotSupportedException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: the object's class " +
                    "does not implement the Cloneable interface");
        }
        System.out.println("Result of the call: " + clonePerson);
        return clonePerson;
    }

    public static void addList(List list, int item) {
        System.out.println("#6 Add an item to an immutable list");
        try {
            list.add(item);
        } catch (UnsupportedOperationException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: immutable list, cant modify");
        }
    }

    public static String[] convertorObjectToString(Object[] arr) {
        System.out.println("#7 Save the elements of an array " +
                "of objects to an array of strings.");
        String[] arrStr = null;
        try {
            arrStr = Arrays.copyOf(arr, arr.length, String[].class);;
        } catch (ArrayStoreException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: attempt to save an object " +
                    "of the wrong type to an array of strings");
        }
        System.out.println("Result of the call: " + arrStr);
        return arrStr;
    }

    public static Dog covertType(Object animal) {
        System.out.println("#8 Convert a superclass object to " +
                "a subclass object.");
        Dog result = null;
        try {
            result = (Dog) animal;
        } catch (ClassCastException exc) {
            exc.printStackTrace(System.out);
            System.out.println("Details: attempt to cast an object " +
                    "to a subclass of which it is not an instance");
        }
        System.out.println("Result of the call: " + result);
        return result;
    }


    public static void startThread()
            throws InterruptedException {
        MyThread thread = new MyThread("JThread");
        System.out.println("#9 Interrupt a thread during execution.");
        thread.start();
        thread.interrupt();
    }
}

