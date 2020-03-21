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
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    public static void main(String[] args) {
        String s = "1234567890-www1111kht_y56?/.,44345w!@$F_V@$%1_%@#WT_W@3456w_532wr23325ter=324523t+kk222qqqq";
        String s2 = "1234567890 ";

        String[] testDataA = {"А", "А", "А", null, "А"};
        String[] testDataB = {"B", "B", "B", null, "B"};
        String[] testDataC = {"C", "C", "C", "", "C"};
        String[] testDataD = {"D", "D", "D", "", "D"};
        String[] testDataE = {"E", "E", "E", " ", "E"};
        String[] testDataF = {"F", "F", "F", "1ram16gb256", "F"};
        String[] testDataG = {"G", "G", "G", "1ram16gb512", "G"};
        String[] testDataH = {"H", "H", "H", "abra42kadabra", "H"};
        String[] testDataJ = {"J", "J", "J", "abra43kadabra", "J"};
        String[] testDataX = {"X", "X", "X", "ab", "X"};
        String[] testDataY = {"Y", "Y", "Y", "ac", "Y"};

        List<String[]> testData = Arrays.asList(
                testDataB,
                testDataA,
                testDataY,
                testDataF,
                testDataD,
                testDataJ,
                testDataC,
                testDataX,
                testDataE,
                testDataG,
                testDataH
        );

        testData.forEach(arr -> System.out.println(Arrays.asList(arr)));
        sortArraysList(testData, 3);
        System.out.println("----------------------after sort-------------------------------");
        testData.forEach(arr -> System.out.println(Arrays.asList(arr)));

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
