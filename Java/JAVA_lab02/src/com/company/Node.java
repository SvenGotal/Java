package com.company;

public class Node<T> {

    public Node next;
    public Node prev;
    public T value;

    public Node(){
        next = null;
        prev = null;
        value = null;
    }


    public Node(Node<T> next, T value, Node<T> prev){
        this.next = next;
        this.prev = prev;
        this.value = value;
    }


}
