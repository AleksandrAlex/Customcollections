package com.suslovalex.customcollections;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Class for testing {@link CustomArrayList}
 */

public class CustomArrayListTest {
    private CustomArrayList customArrayList;

    @Before
    public  void setUp(){
        customArrayList = new CustomArrayList();
    }

    @Test
    public void addDefaultTestEmptyList(){
        int element = 2;
        customArrayList = initEmptyList();
        customArrayList.add(element);

        int expectedElement = 2;
        int expectedSize = 1;
        int expectedElementIndex = 0;

        assertEquals(expectedSize, customArrayList.size());
        assertEquals(expectedElement, customArrayList.get(expectedElementIndex));
    }

    @Test
    public void addDefaultTestNotEmptyList(){
        customArrayList = initEmptyList();
        int element0 = 3;
        int element1 = 1;
        int element2 = 8;

        customArrayList.add(element0);
        customArrayList.add(element1);
        customArrayList.add(element2);

        int expectAmountOfNumbers = 3;

        int expectElement = 8;
        int expectedElementIndex = 2;

        assertEquals(expectAmountOfNumbers, customArrayList.size());
        assertEquals(expectElement, customArrayList.get(expectedElementIndex));
    }

    @Test
    public void addDefaultTestFullList(){

    }
    private CustomArrayList initEmptyList() {
        return new CustomArrayList<>();
    }

}
