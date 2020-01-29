package com.suslovalex.customcollections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    int number1;
    int number2;





    @Test
    public void addition_isCorrect() {
        inition();
        assertEquals(4,
                addition());

    }

    private void inition() {
        number1 = 2;
        number2 = 2;

    }

    private int addition() {
        return number1 + number2;
    }
}