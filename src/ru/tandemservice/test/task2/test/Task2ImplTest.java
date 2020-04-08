package ru.tandemservice.test.task2.test;

import org.junit.Test;
import ru.tandemservice.test.task2.ElementExampleImpl;
import ru.tandemservice.test.task2.IElement;
import ru.tandemservice.test.task2.Task2Impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class Task2ImplTest {
    private Task2Impl task2 = new Task2Impl();
    private ElementExampleImpl.Context ctx = new ElementExampleImpl.Context();

    @Test
    public void testA1Algorithm() {
        IElement el1 = new ElementExampleImpl(ctx, 1);
        IElement el2 = new ElementExampleImpl(ctx, 4);
        IElement el3 = new ElementExampleImpl(ctx, 0);
        IElement el4 = new ElementExampleImpl(ctx, 2);
        IElement el5 = new ElementExampleImpl(ctx, 3);

        List<IElement> list = Arrays.asList(el1, el2, el3, el4, el5);
        int expectedOperationCount = list.size() + 1;

        task2.assignNumbers(list);
        checkOrder(list);
        assertEquals(expectedOperationCount, ctx.getOperationCount());

        System.out.println("testA1Algorithm operation count - " + ctx.getOperationCount());
    }

    @Test
    public void testA1AndOtherCases() {
        IElement el1 = new ElementExampleImpl(ctx, 1);
        IElement el2 = new ElementExampleImpl(ctx, 4);
        IElement el3 = new ElementExampleImpl(ctx, 0);
        IElement el4 = new ElementExampleImpl(ctx, 2);
        IElement el5 = new ElementExampleImpl(ctx, 3);
        IElement el6 = new ElementExampleImpl(ctx, 28);
        IElement el7 = new ElementExampleImpl(ctx, 6);
        IElement el8 = new ElementExampleImpl(ctx, 8);
        IElement el9 = new ElementExampleImpl(ctx, -32);

        final List<IElement> list = Arrays.asList(el1, el2, el3, el4, el5, el6, el7, el8, el9);
        int expectedOperationCount = list.size();

        task2.assignNumbers(list);
        checkOrder(list);
        assertEquals(expectedOperationCount, ctx.getOperationCount());

        System.out.println("testA1AndOtherCases operation count - " + ctx.getOperationCount());
    }

    @Test
    public void testWhenOneElementValueIsNotInIndexRange() {
        IElement el1 = new ElementExampleImpl(ctx, 1);
        IElement el2 = new ElementExampleImpl(ctx, 2);
        IElement el3 = new ElementExampleImpl(ctx, 3);
        IElement el4 = new ElementExampleImpl(ctx, 4);
        IElement el5 = new ElementExampleImpl(ctx, 2323);

        final List<IElement> list = Arrays.asList(el1, el2, el3, el4, el5);
        int expectedOperationCount = list.size();

        task2.assignNumbers(list);
        checkOrder(list);
        assertEquals(expectedOperationCount, ctx.getOperationCount());

        System.out.println("test operation count - " + ctx.getOperationCount());
    }

    @Test
    public void testWhenListIsSorted() {
        int expectedOperationCount = 0;
        IElement el1 = new ElementExampleImpl(ctx, 0);
        IElement el2 = new ElementExampleImpl(ctx, 1);
        IElement el3 = new ElementExampleImpl(ctx, 2);
        IElement el4 = new ElementExampleImpl(ctx, 3);
        IElement el5 = new ElementExampleImpl(ctx, 4);

        final List<IElement> list = Arrays.asList(el1, el2, el3, el4, el5);

        task2.assignNumbers(list);
        checkOrder(list);
        assertEquals(expectedOperationCount, ctx.getOperationCount());

        System.out.println("test operation count - " + ctx.getOperationCount());
    }

    private void checkOrder(List<IElement> list) {
        AtomicInteger counter = new AtomicInteger(0);
        list.forEach(iElement -> assertEquals(counter.get(), list.get(counter.getAndIncrement()).getNumber()));
    }
}