package com.suslovalex.customcollections;

import java.util.Iterator;

public class CustomIterator<E> implements Iterator<E> {
    private int index;
    private E [] array;
    public CustomIterator(Object[] array) {
        index = 0;
        this.array = (E[]) array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public E next() {
        return array[index++];
    }
}
