package com.suslovalex.customcollections;



public class CustomLinkedList {
    Node head;
    Node tail;
    int size;

    private static class Node<E>
    {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element)
        {
            this.element = element;
            this.next = next;
            this.prev = prev;

        }
    }
    public void add(int element) {
        Node newNode = new Node(element);
        if (head == null) {
            newNode.next = null;
            newNode.prev = null;
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void add (int index, int element){

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        if (index==size){
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        if (index == 0) {
            add(element);
        }
        Node oldNode = head;
        for (int i = 0; i<index; i++){
            newNode.prev = oldNode;
            newNode.next = oldNode.next;

        }
    }

}
