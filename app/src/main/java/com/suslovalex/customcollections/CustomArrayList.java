package com.suslovalex.customcollections;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomArrayList <E> implements List <E> {
    private int capacity = 10;
    private int size = 0;
    private Object[] array;
    private final int defaultArrayCapacity = 10;

    public CustomArrayList() {
        this.array = new Object[defaultArrayCapacity];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity>0){
            array = new Object[initialCapacity];
        }
        else if (initialCapacity==0){
            array = new Object[defaultArrayCapacity];
        }
        else{
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return indexOf(o) >= 0;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>(array);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (capacity==size){
            array = Arrays.copyOf(array, array.length*2);
            capacity = array.length;
        }
        array[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        for (int i=0; i<size; i++)
            if (o.equals(array[i])){
                E [] temp = (E[]) array;
                array = (E[]) new Object[temp.length-1];
                int index = i;
                System.arraycopy(temp,0,array,0,index);
                int amountElementsAfterIndex = temp.length - index-1;
                System.arraycopy(temp, index+1, array,index,amountElementsAfterIndex);
                size--;
                return true;
            }
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
        for (int i = 0; i < size; i++)
            array[i] = null;

        size = 0;
        capacity = defaultArrayCapacity;

    }

    @Override
    public E get(int index) {
        if (index<size){
            return (E) array[index];
        }
        else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) {
        return (E) (array[index] = element);
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            String message = "Your operation is not correct. Your index is: "+index+", Size of array is: "+size;
            throw new IndexOutOfBoundsException(message);
        }
        E [] temp = (E[]) array;
        array = (E[]) new Object[temp.length-1];
        System.arraycopy(temp,0,array,0,index);
        int amountElementsAfterIndex = temp.length - index-1;
        System.arraycopy(temp, index+1, array,index,amountElementsAfterIndex);
        size--;
        return (E) array[index];

    }

    @Override
    public int indexOf(@Nullable Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(array[i]))
                    return i;
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
}
