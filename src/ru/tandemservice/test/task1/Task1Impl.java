package ru.tandemservice.test.task1;

import ru.tandemservice.test.task1.comparator.RowsComparator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */

public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    //public static final IStringRowsListSorter INSTANCE = new Task1Impl();
    private volatile static IStringRowsListSorter instance;

    private Task1Impl() {}

    public static IStringRowsListSorter getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (IStringRowsListSorter.class) {
                if (Objects.isNull(instance)) {
                    instance = new Task1Impl();
                }
            }
        }
        return instance;
    }

    private static void sortArraysList(final List<String[]> rows, final int columnIndex) {
        rows.sort(new RowsComparator(columnIndex));
    }

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        // напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного, документированного и понятного кода.
        rows.sort(new RowsComparator(columnIndex));
    }
}
