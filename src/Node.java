package ua.edu.sumdu.j2se.chikalovEugene.tasks;

import java.util.Objects;

public  class Node implements Cloneable{
    private Task element;
    private Node next;
    private Node previous;

    public Task getElement() {
        return element;
    }
    public void setElement(Task element) {
        this.element = element;
    }
    public Node(Task element, Node next, Node previous) {
        this.element = element;
        this.next = next;
        this.previous = previous;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public Node getPrevious() {
        return previous;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    public Node(Task element) {
        this.element = element;
    }
}