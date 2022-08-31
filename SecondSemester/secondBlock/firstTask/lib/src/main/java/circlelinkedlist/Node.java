package circlelinkedlist;

public class Node<E> {
    private E data;
    private Node<E> next;


    Node(E data) {
        this.data = data;
    }

    public E getDate() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

}
