package circlelinkedlist;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CircleLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public CircleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public CircleLinkedList(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }

        this.size = 0;
        Node<E> savedHead = list.head;
        Node<E> currentNode = savedHead;

        do {
            addLast(currentNode.getDate());
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    private class ListIterator<E> implements Iterator<E> {
        private Node<E> currentNode;

        public ListIterator(Node<E> head) {
            currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return (currentNode != null);
        }

        @Override
        public E next() throws NoSuchElementException {
            E result = currentNode.getDate();
            currentNode = currentNode.getNext();
            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator(this.head);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E data) {

        if (isEmpty()) {
            return false;
        }
        Node<E> currentNode = head;
        do {
            if (currentNode.getDate().equals(data)) {
                return true;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != head);

        return false;
    }

    public boolean contains(Node<E> node) {
        if (isEmpty()) {
            return false;
        }

        Node<E> currentNode = head;
        do {
            if (currentNode.equals(node)) {
                return true;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != head);

        return false;
    }

    public void addAfter(Node<E> node, Node<E> newNode) {
        if (node == null || newNode == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty() || !contains(node)) {
            throw new NoSuchElementException();
        }

        Node<E> savedNext = node.getNext();
        node.setNext(newNode);
        newNode.setNext(savedNext);
        size++;
    }

    public void addAfter(Node<E> node, E data) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        Node<E> newNode = new Node<>(data);
        addAfter(node, newNode);
    }

    public void addBefore(Node<E> node, Node<E> newNode) {
        if (node == null || newNode == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty() || !contains(node)) {
            throw new NoSuchElementException();
        }

        var currentNode = head;
        var previousNode = tail;

        if (node == head) {
            addLast(newNode);
            return;
        }

        do {
            if (currentNode == node) {
                previousNode.setNext(newNode);
                newNode.setNext(currentNode);
                size++;
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        } while (currentNode != head);

        throw new IllegalStateException();
    }

    public void addFirst(Node<E> node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = node;
            tail = node;
            tail.setNext(head);
        } else {
            tail.setNext(node);
            node.setNext(head);
            head = node;
        }
        size++;
    }

    public void addFirst(E data) {
        var newNode = new Node<>(data);
        addFirst(newNode);
    }

    public void addLast(Node<E> node) {
        if (node == null) {
            throw new IllegalArgumentException();
    }
        if (head == null) {
            head = node;
            tail = node;
            tail.setNext(head);
        } else {
            node.setNext(head);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public void addLast(E data) {
        var newNode = new Node<>(data);
        addLast(newNode);
    }

    public void clear() {
        for (var currentNode = head; currentNode != null; ) {
            var nextNode = currentNode.getNext();
            currentNode.setNext(null);
            currentNode = nextNode;
        }
        head = tail = null;
        size = 0;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CircleLinkedList<E> that = (CircleLinkedList<E>) obj;
        if (this.size != that.size) {
            return false;
        }

        Iterator<E> firstList = this.iterator();
        Iterator<E> secondList= that.iterator();

        for (int count = 0; count < this.size; count++) {
            Object nodeFirstList = firstList.next();
            Object nodeSecondList = secondList.next();

            if (!(nodeFirstList == null ? nodeSecondList == null :
                    nodeFirstList.equals(nodeSecondList))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("List: ");
        Iterator<E> iter = this.iterator();
        for (int count = 0; count < size; count++) {
            if (iter.hasNext()) {
                result.append("[").append(iter.next()).append("] ");
            }
        }
        return result.toString();
    }

    public Node<E> find(E data) {
        if (isEmpty()) {
            return null;
        }

        var currentNode = head;
        do {
            if ((currentNode.getDate()).equals(data)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != head);

        return null;
    }

    public Node<E> findLast(E data) {
        if (isEmpty()) {
            return null;
        }

        var currentNode = head;
        Node<E> result = null;

        do {
            if ((currentNode.getDate()).equals(data)) {
                result = currentNode;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != head);

        return result;
    }

    public Type getType() {
        return this.getClass();
    }

    public void remove(Node<E> node) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (node == null) {
            throw new IllegalArgumentException();
        }

        var currentNode = head;
        var previousNode = tail;

        if (node == head) {
            this.removeFirst();
            return;
        }

        do {
            if (currentNode == node) {
                previousNode.setNext(currentNode.getNext());
                size--;
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        } while (currentNode != head);

        throw new IllegalStateException();
    }

    public boolean remove(E data) {
        if (isEmpty()) {
            return false;
        }
        var node = find(data);
        if (node == null) {
            return false;
        }
        remove(node);
        return true;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        var next = head.getNext();
        tail.setNext(next);
        head = next;
        size--;
        return;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        var currentNode = head;
        var previousNode = head;

        for (int count = 0; count < size - 1; count++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        previousNode.setNext(head);
        tail = previousNode;
        size--;
    }

    public void copyTo(E[] array, int index) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size > array.length) {
            throw new IllegalArgumentException();
        }

        var currentNode = head;

        do {
            array[index] = currentNode.getDate();
            index++;
            currentNode = currentNode.getNext();
        } while (currentNode != head);
    }

    public static <E> boolean any(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    @FunctionalInterface
    public interface Predicate<E> {
        boolean test(E item);
    }

    public static <E> boolean any(CircleLinkedList<E> list,
                                  Predicate<E> predicate) {
        if (predicate == null || list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            return false;
        }

        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (predicate.test(currentNode.getDate())) {
                return true;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return false;
    }



    public static <E> CircleLinkedList<E> append (CircleLinkedList<E> list, E data) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var newList = new CircleLinkedList<>(list);
        newList.addLast(data);
        return newList;
    }

    public static <E> CircleLinkedList<E> concat(CircleLinkedList<E> firstList,
                                                 CircleLinkedList<E> secondList) {
        if (firstList == null || secondList == null) {
            throw new IllegalArgumentException();
        }
        if (firstList.isEmpty()) {
            return new CircleLinkedList<>(secondList);
        } else if (secondList.isEmpty()) {
            return new CircleLinkedList<>(firstList);
        } else if (firstList.isEmpty() && secondList.isEmpty()) {
            return new CircleLinkedList<>();
        }

        var newList = new CircleLinkedList<>(firstList);
        var savedHead = secondList.head;
        var currentNode = savedHead;

        do {
            newList.addLast(currentNode.getDate());
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return newList;
    }

    public static <E> boolean contains(CircleLinkedList<E> list, E data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate().equals(data)) {
                return true;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return false;
    }

    public static <E> int count(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            return 0;
        }

        int count = 0;
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate() != null) {
                count++;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return count;
    }

    public static <E> int count(CircleLinkedList<E> list,
                                Predicate<E> predicate) {
        if (list == null || predicate == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            return 0;
        }

        int count = 0;
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate() != null &&
                    predicate.test(currentNode.getDate())) {
                count++;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return count;
    }

    public static <E> E elementAt(CircleLinkedList<E> list, int index) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (index < 0 || index >= list.getSize()) {
            throw new IndexOutOfBoundsException();
        }

        int count = 0;
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate() != null && count == index) {
                return currentNode.getDate();
            }
            count++;
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        throw new IndexOutOfBoundsException();
    }

    public static <E> E elementAtOrDefault(CircleLinkedList<E> list, int index) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        if (index < 0 || index >= list.getSize()) {
            return null;
        }

        int count = 0;
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate() != null && count == index) {
                return currentNode.getDate();
            }
            count++;
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return null;
    }

    public static <E> E first(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            throw new IllegalStateException();
        }
        return list.head.getDate();
    }

    public static <E> E firstOrDefault
            (CircleLinkedList<E> list, E defaultValue) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            return defaultValue;
        }
        return list.head.getDate();
    }

    public static <E> E last(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            throw new IllegalStateException();
        }
        return list.tail.getDate();
    }

    public static <E> E lastOfDefault
            (CircleLinkedList<E> list, E defaultValue) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        if (list.isEmpty()) {
            return defaultValue;
        }
        return list.tail.getDate();
    }

    public static <E extends Comparable> E max(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var max = first(list);
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate().compareTo(max) > 0) {
                max = currentNode.getDate();
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return max;
    }

    public static <E extends Comparable<E>> E min(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var min = CircleLinkedList.first(list);
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            if (currentNode.getDate().compareTo(min) < 0) {
                min = currentNode.getDate();
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return min;
    }

    public static <E extends Comparable<E>>
    CircleLinkedList<E> prepend(CircleLinkedList<E> list, E item) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var newList = new CircleLinkedList<>(list);
        newList.addFirst(item);

        return newList;
    }

    public static <E> CircleLinkedList<E> reverse(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var reverseList = new CircleLinkedList<E>();
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            reverseList.addFirst(currentNode.getDate());
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return reverseList;
    }

    public static <E extends  Comparable<E>>
    boolean sequenceEqual(CircleLinkedList<E> firstList,
                          CircleLinkedList<E> secondList) {
        if (firstList == null || secondList == null) {
            throw new IllegalArgumentException();
        }

        int sizeFirstList = count(firstList);
        int sizeSecondList = count(secondList);

        if (sizeFirstList == 0 && sizeSecondList == 0) {
            return true;
        }

        if (sizeFirstList != sizeSecondList) {
            return false;
        }

        int i = 0;
        var arrayFirst = new Object[sizeFirstList];
        var savedHead = firstList.head;
        var currentNode = savedHead;

        do {
            arrayFirst[i++] = currentNode.getDate();
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        i = 0;
        var arraySecond = new Object[sizeSecondList];
        savedHead = secondList.head;
        currentNode = savedHead;

        do {
            arraySecond[i++] = currentNode.getDate();
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        for (i = 0; i < sizeFirstList; i++) {
            if (!(arrayFirst[i].equals(arraySecond[i]))) {
                return false;
            }
        }
        return true;
    }

    public static <E> E single(CircleLinkedList<E> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        int sizeList = count(list);

        if (sizeList != 1) {
            throw new IllegalStateException();
        }
        return first(list);
    }

    public static <E> CircleLinkedList<E>
    take(CircleLinkedList<E> list, int count) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var newList = new CircleLinkedList<E>();

        if (list.isEmpty() || count <= 0) {
            return newList;
        }

        int number = 0;
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            number++;
            newList.addLast(currentNode.getDate());
            if (number == count) {
                return newList;
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return newList;
    }

    public static <E> CircleLinkedList<E>
    takeLast(CircleLinkedList<E> list, int count) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        var newList = new CircleLinkedList<E>();

        if (list.isEmpty() || count <= 0) {
            return newList;
        }

        int number = 0;
        var savedHead = list.head;
        var currentNode = savedHead;

        do {
            number++;
            if (number > list.size - count) {
                newList.addLast(currentNode.getDate());
            }
            currentNode = currentNode.getNext();
        } while (currentNode != savedHead);

        return newList;
    }
}

