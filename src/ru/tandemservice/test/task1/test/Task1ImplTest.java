package ru.tandemservice.test.task1.test;

import org.junit.Assert;
import org.junit.Test;
import ru.tandemservice.test.task1.IStringRowsListSorter;
import ru.tandemservice.test.task1.Task1Impl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Task1ImplTest {

    @Test
    public void testSort() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();

        String[] testDataA = {"А", "А", "А", null, "А"};
        String[] testDataB = {"B", "B", "B", null, "B"};
        String[] testDataC = {"C", "C", "C", "", "C"};
        String[] testDataD = {"D", "D", "D", "", "D"};
        String[] testDataE = {"E", "E", "E", " ", "E"};
        String[] testDataF = {"F", "F", "F", "1ram16gb256", "F"};
        String[] testDataG = {"G", "G", "G", "PGP-12-0", "G"};
        String[] testDataH = {"H", "H", "H", "ПГП-12-1", "H"};
        String[] testDataJ = {"J", "J", "J", "ПГП-12-2", "J"};
        String[] testDataX = {"X", "X", "X", "ПГП-12-3", "X"};
        String[] testDataY = {"Y", "Y", "Y", "ПГ-12", "Y"};

        List<String[]> testData = Arrays.asList(
                testDataH,
                testDataA,
                testDataB,
                testDataY,
                testDataF,
                testDataC,
                testDataJ,
                testDataX,
                testDataE,
                testDataG,
                testDataD
        );

        assertTrue(testData.get(0)[3].equals("ПГП-12-1"));
        task1Impl.sort(testData, 3);
        assertTrue(testData.get(0)[0].equals(testDataA[0]));
    }

    @Test
    public void whenBothIsNullThanNonSort() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testDataA = {"А", "А", "А", null, "А"};
        String[] testDataB = {"B", "B", "B", null, "B"};

        List<String[]> testData = Arrays.asList(testDataB, testDataA);

        assertTrue(testData.get(0)[0].equals(testDataB[0]));
        task1Impl.sort(testData, 3);
        assertTrue(testData.get(0)[0].equals(testDataB[0]));

    }

    @Test
    public void whenSortNullAndEmptyStringThanFirsNull() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testDataA = {"А", "А", "А", null, "А"};
        String[] testDataB = {"B", "B", "B", "", "B"};

        List<String[]> testData = Arrays.asList(testDataB, testDataA);

        assertTrue(testData.get(0)[0].equals(testDataB[0]));
        task1Impl.sort(testData, 3);
        assertTrue(testData.get(0)[0].equals(testDataA[0]));

    }
}