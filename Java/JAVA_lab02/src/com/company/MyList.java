package com.company;

public class MyList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyList(){
        head = new Node<T>();
        tail = new Node<T>();

        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void Print(){
        print(head.next);
    }
    public String peek(){
        return tail.prev.value.toString();
    }
    public void push(T val){
        Node<T> newNode = new Node<T>();
        newNode.value = val;

        if(size < 1){
            newNode.prev = head;
            newNode.next = tail;

            head.next = newNode;
            tail.prev = newNode;
        }
        else{
            newNode.prev = tail.prev;
            newNode.next = tail;

            tail.prev.next = newNode;
            tail.prev = newNode;
        }

        size++;
    }
    public Node<T> pop(){

        if(size == 0)
            return null;
        Node<T> retNode = new Node<T>();
        retNode = tail.prev;

        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;

        size--;
        return retNode;
    }

    public String element(){
        return head.next.value.toString();
    }
    public void offer(T val){
        push(val);
    }
    public Node<T> poll(){

        if(size == 0)
            return null;

        Node<T> retNode = new Node<T>();
        retNode = head.next;

        head.next.next.prev = head;
        head.next = head.next.next;
        size--;

        return retNode;

    }

    public int Size(){
        return size;
    }
    public boolean isEmpty(){
        if(head.next == null || tail.prev == null)
            return true;
        return false;
    }
    public boolean contains(T val){

        Node<T> iter = new Node<T>();
        iter = head.next;

        do{
            if(iter.value == val)
                return true;

            iter = iter.next;
        }while(iter.next != null);
        return false;

    }
    public String mytoString(){
        String str = "";
        Node<T> iter = new Node<T>();
        iter = head.next;

        do{
            str += iter.value.toString();
            iter = iter.next;
        }while(iter.next != null);

        return str;
    }

    private boolean hasNext(Node<T> node){
        if(node.next == null)
            return false;
        return true;
    }
    private void print(Node<T> node){


        if(node.next != null){
            print(node.next);
        }
        if(node.value != null)
            System.out.println(node.value);
    }

}
