package com.company;

public class Main {

    public static void main(String[] args) {
        MyList<Integer> lst = new MyList<Integer>();

        lst.offer(1);
        lst.offer(2);
        lst.offer(3);
        lst.offer(4);
        lst.offer(5);

        System.out.println(lst.mytoString());

    }
}
