package ru.tandemservice.test.task2.test;

import org.junit.Test;
import ru.tandemservice.test.task2.ElementExampleImpl;
import ru.tandemservice.test.task2.IElement;
import ru.tandemservice.test.task2.Task2Impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
                new ElementExampleImpl(ctx, 10),
                new ElementExampleImpl(ctx, 11),
                new ElementExampleImpl(ctx, 12),
                new ElementExampleImpl(ctx, 13)
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

        assertEquals(expectedOperationCount, ctx.getOperationCount());

        assertEquals(0, list.get(0).getNumber());
        assertEquals(1, list.get(1).getNumber());
        assertEquals(2, list.get(2).getNumber());
        assertEquals(3, list.get(3).getNumber());
        assertEquals(4, list.get(4).getNumber());
    }

    @Test
    public void whenValuesAreInListIndexesRangeAndTheyHaveMirrorPair() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, 11),
                new ElementExampleImpl(ctx, 10),
                new ElementExampleImpl(ctx, 13),
                new ElementExampleImpl(ctx, 12),
                new ElementExampleImpl(ctx, 15),
                new ElementExampleImpl(ctx, 14)
        );


        int expectedOperationCount = (int) (list.stream().filter(Objects::nonNull).count() * 1.5);
        task2.assignNumbers(list);

        assertEquals(10, list.get(0).getNumber());
        assertEquals(11, list.get(1).getNumber());
        assertEquals(12, list.get(2).getNumber());
        assertEquals(13, list.get(3).getNumber());
        assertEquals(14, list.get(4).getNumber());
        assertEquals(15, list.get(5).getNumber());

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void testWhenValuesAreOutOfListIndexesRange() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, 34),
                new ElementExampleImpl(ctx, 43),
                new ElementExampleImpl(ctx, 11),
                new ElementExampleImpl(ctx, Integer.MAX_VALUE),
                new ElementExampleImpl(ctx, 456744),
                new ElementExampleImpl(ctx, 18)
        );

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count() + 1;
        task2.assignNumbers(list);

        assertEquals(11, list.get(0).getNumber());
        assertEquals(18, list.get(1).getNumber());
        assertEquals(34, list.get(2).getNumber());
        assertEquals(43, list.get(3).getNumber());
        assertEquals(456744, list.get(4).getNumber());
        assertEquals(Integer.MAX_VALUE, list.get(5).getNumber());

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void testWhenValuesAreOutOfListIndexesRangeAndNegatives() {
        final List<IElement> list = Arrays.asList(
                new ElementExampleImpl(ctx, -43),
                new ElementExampleImpl(ctx, -34),
                new ElementExampleImpl(ctx, -11),
                new ElementExampleImpl(ctx, Integer.MIN_VALUE),
                new ElementExampleImpl(ctx, -456744),
                new ElementExampleImpl(ctx, -18)
        );

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count() + 1;
        task2.assignNumbers(list);

        assertEquals(Integer.MIN_VALUE, list.get(0).getNumber());
        assertEquals(-456744, list.get(1).getNumber());
        assertEquals(-43, list.get(2).getNumber());
        assertEquals(-34, list.get(3).getNumber());
        assertEquals(-18, list.get(4).getNumber());
        assertEquals(-11, list.get(5).getNumber());

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void tesNullList() {
        final List<IElement> list = Arrays.asList(null, null, null, null);

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count();
        task2.assignNumbers(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

    @Test
    public void testWhenValuesAreDifferent() {
        final List<IElement> list = Arrays.asList(
                null,
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
                null,
                null,
                new ElementExampleImpl(ctx, 13),
                new ElementExampleImpl(ctx, 2),
                new ElementExampleImpl(ctx, 4),
                new ElementExampleImpl(ctx, 11),
                new ElementExampleImpl(ctx, 7),
                null

        );

        int expectedOperationCount = (int) list.stream().filter(Objects::nonNull).count() + 1;
        task2.assignNumbers(list);

        assertEquals(expectedOperationCount, ctx.getOperationCount());
    }

}