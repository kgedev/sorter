package ru.tandemservice.test.task2.test;

import org.junit.Test;
import ru.tandemservice.test.task2.ElementExampleImpl;
import ru.tandemservice.test.task2.IElement;
import ru.tandemservice.test.task2.Task2Impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class Task2ImplTest {
    private final Task2Impl task2 = (Task2Impl) Task2Impl.INSTANCE;
    private final ElementExampleImpl.Context ctx = new ElementExampleImpl.Context();

    @Test
    public void whenListIsEmpty() {
        final LinkedList elements = new LinkedList();

        int expectedOperationCount = 0;
        task2.assignNumbers(elements);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void whenElementsAlreadyHaveValuesEqualsThemIndexes() {
        final List<IElement> elements = Arrays.asList(
                new ElementExampleImpl(ctx, 0),
                new ElementExampleImpl(ctx, 1),
                new ElementExampleImpl(ctx, 2),
                new ElementExampleImpl(ctx, 3)
        );

        int expectedOperationCount = 0;
        task2.assignNumbers(elements);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void whenValuesAreInListIndexesRange() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, 1),
                new ElementExampleImpl(ctx, 4),
                new ElementExampleImpl(ctx, 0),
                new ElementExampleImpl(ctx, 2),
                new ElementExampleImpl(ctx, 3)
        );

        int expectedOperationCount = 1 + (int) list.stream().filter(Objects::nonNull).count();
        task2.assignNumbers(list);
        checkOrder(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void whenValuesAreInListIndexesRangeAndTheyHaveMirrorPair() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, 1),
                new ElementExampleImpl(ctx, 0),
                new ElementExampleImpl(ctx, 3),
                new ElementExampleImpl(ctx, 2),
                new ElementExampleImpl(ctx, 5),
                new ElementExampleImpl(ctx, 4)
        );


        int expectedOperationCount = (int) (list.stream().filter(Objects::nonNull).count() * 1.5);
        task2.assignNumbers(list);
        checkOrder(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void testWhenValuesAreOutOfListIndexesRange() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, 34),
                new ElementExampleImpl(ctx, 11),
                new ElementExampleImpl(ctx, Integer.MAX_VALUE),
                new ElementExampleImpl(ctx, 43),
                new ElementExampleImpl(ctx, 456744),
                new ElementExampleImpl(ctx, 18)
        );

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count();
        task2.assignNumbers(list);
        checkOrder(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void testWhenValuesAreOutOfListIndexesRangeAndNegatives() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, -34),
                new ElementExampleImpl(ctx, -11),
                new ElementExampleImpl(ctx, Integer.MIN_VALUE),
                new ElementExampleImpl(ctx, -43),
                new ElementExampleImpl(ctx, -456744),
                new ElementExampleImpl(ctx, -18)
        );

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count();
        task2.assignNumbers(list);
        checkOrder(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void tesNullList() {
        final List<IElement> list = Arrays.asList(null, null, null, null);

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count();
        task2.assignNumbers(list);
        checkOrder(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void testWhenValuesAreDifferent() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, 1),
                new ElementExampleImpl(ctx, 0),
                new ElementExampleImpl(ctx, Integer.MAX_VALUE ),
                new ElementExampleImpl(ctx, -43),
                new ElementExampleImpl(ctx, -456744),
                new ElementExampleImpl(ctx, Integer.MIN_VALUE),
                null,
                new ElementExampleImpl(ctx, 1234),
                new ElementExampleImpl(ctx, 8),
                new ElementExampleImpl(ctx, 6),
                new ElementExampleImpl(ctx, 12),
                new ElementExampleImpl(ctx, 3),
                new ElementExampleImpl(ctx, 13),
                new ElementExampleImpl(ctx, 2),
                new ElementExampleImpl(ctx, 11),
                new ElementExampleImpl(ctx, 4),
                new ElementExampleImpl(ctx, 7),
                null

        );

        int expectedOperationCount = 16;
        task2.assignNumbers(list);
        checkOrder(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    private void checkOrder(List<IElement> list) {
        AtomicInteger counter = new AtomicInteger(0);
        list.stream()
                .peek(element -> {
                    if (element == null) counter.getAndIncrement();
                })
                .filter(Objects::nonNull)
                .forEach(iElement -> assertEquals(counter.get(), list.get(counter.getAndIncrement()).getNumber()));
    }
}