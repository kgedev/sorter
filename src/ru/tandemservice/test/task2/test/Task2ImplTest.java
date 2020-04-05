package ru.tandemservice.test.task2.test;

import org.junit.Test;
import ru.tandemservice.test.task2.ElementExampleImpl;
import ru.tandemservice.test.task2.IElement;
import ru.tandemservice.test.task2.Task2Impl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task2ImplTest {
    @Test
    public void sortNumbersNullAndSpaceTest() {
        Task2Impl task2 = new Task2Impl();
        ElementExampleImpl.Context ctx = new ElementExampleImpl.Context();
        IElement el1 = new ElementExampleImpl(ctx, 1);
        IElement el2 = new ElementExampleImpl(ctx, 2);
        IElement el3 = new ElementExampleImpl(ctx, 3);
        IElement el4 = new ElementExampleImpl(ctx, 4);
        IElement el5 = new ElementExampleImpl(ctx, 0);

        List<IElement> list = Arrays.asList(el1, el2, el3, el4, el5);

        assertEquals(list.get(0).getNumber(), 1);
        assertEquals(list.get(1).getNumber(), 2);
        assertEquals(list.get(2).getNumber(), 3);
        assertEquals(list.get(3).getNumber(), 4);
        assertEquals(list.get(4).getNumber(), 0);
       task2.assignNumbers(list);
        assertEquals(list.get(0).getNumber(), 0);
        assertEquals(list.get(1).getNumber(), 1);
        assertEquals(list.get(2).getNumber(), 2);
        assertEquals(list.get(3).getNumber(), 3);
        assertEquals(list.get(4).getNumber(), 4);



    }
}