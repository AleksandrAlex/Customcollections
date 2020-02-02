package com.suslovalex.customcollections;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomLinkedList <E> implements List <E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public CustomLinkedList() {
        //first = new Node<>(null,null,null);
        //last = new Node<>(null,null,null);

    }
    private Node <E> getNextNode(Node<E> node){
        return node.next;
    }
    private Node<E> getPrevNode (Node<E> node){
        return node.prev;
    }
    private E getCurrentElement(Node<E> node){
        return node.element;
    }




    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return indexOf(o) != -1;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (size==0){
            Node newNode = new Node(e,null,null);
            first = newNode;
            last = newNode;
            size++;
        }
        if (size>0 && getNextNode(first)==null && getPrevNode(last)==null){
            Node<E> node = new Node<>(e,null,first);
            first.next = last;
            last = node;
            size++;
        }
        else{
            linkLast(e);
        }
        return true;
    }
    void linkLast(E e) {
        Node<E> node = last;
        Node<E> newNode = new Node<>(e,null,node);
        node.next = newNode;
        last = newNode;
        size++;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        Node<E> nodeElement = getNextNode(first);
        for (int i = 0; i< index; i++){
            nodeElement = getNextNode(nodeElement);
        }
        return nodeElement.element;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (index==size){
            linkLast(element);
        }
        else {

        }

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @NonNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }


    private static class Node<E>
    {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> next, Node<E> prev)
        {
            this.element = element;
            this.next = next;
            this.prev = prev;

        }
    }


}
