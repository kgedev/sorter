package ru.tandemservice.test.task1.test;

import org.junit.Test;
import ru.tandemservice.test.task1.IStringRowsListSorter;
import ru.tandemservice.test.task1.Task1Impl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Task1ImplTest {

    @Test
    public void sortNumbersTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();

        String[] testData1 = { "1" };
        String[] testData2 = { "2" };
        String[] testData3 = { "3" };
        String[] testData4 = { "4" };

        List<String[]> testData = Arrays.asList(testData2, testData4, testData3, testData1);

        assertEquals("2", testData.get(0)[0]);
        assertEquals("4", testData.get(1)[0]);
        assertEquals("3", testData.get(2)[0]);
        assertEquals("1", testData.get(3)[0]);
        task1Impl.sort(testData, 0);
        assertEquals("1", testData.get(0)[0]);
        assertEquals("2", testData.get(1)[0]);
        assertEquals("3", testData.get(2)[0]);
        assertEquals("4", testData.get(3)[0]);
    }

    @Test
    public void sortNumbersNullAndSpaceTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();

        String[] testData1 = { "1" };
        String[] testData2 = { "2" };
        String[] testData3 = { "3" };
        String[] testData4 = { "4" };
        String[] testData5 = { null };
        String[] testData6 = { " " };

        List<String[]> testData = Arrays.asList(testData2, testData4, testData3, testData1, testData6, testData5);

        assertEquals("2", testData.get(0)[0]);
        assertEquals("4", testData.get(1)[0]);
        assertEquals("3", testData.get(2)[0]);
        assertEquals("1", testData.get(3)[0]);
        assertEquals(" ", testData.get(4)[0]);
        assertNull(testData.get(5)[0]);
        task1Impl.sort(testData, 0);
        assertNull(testData.get(0)[0]);
        assertEquals(" ", testData.get(1)[0]);
        assertEquals("1", testData.get(2)[0]);
        assertEquals("2", testData.get(3)[0]);
        assertEquals("3", testData.get(4)[0]);
        assertEquals("4", testData.get(5)[0]);
    }

    @Test
    public void sortNullAndStringsTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testDataA = {"A_null", "А", "А", null };
        String[] testDataB = {"B_null", "B", "B", null };
        String[] testDataC = {"1ram16gb256", "C", "C", "1ram16gb256" };
        String[] testDataD = {"*ram16gb256", "D", "D", "*ram16gb256" };

        List<String[]> testData = Arrays.asList(testDataD, testDataC, testDataB, testDataA);

        assertEquals("*ram16gb256", testData.get(0)[0]);
        assertEquals("1ram16gb256", testData.get(1)[0]);
        assertEquals("B_null", testData.get(2)[0]);
        assertEquals("A_null", testData.get(3)[0]);
        task1Impl.sort(testData, 3);
        assertEquals("B_null", testData.get(0)[0]);
        assertEquals("A_null", testData.get(1)[0]);
        assertEquals("*ram16gb256", testData.get(2)[0]);
        assertEquals("1ram16gb256", testData.get(3)[0]);

    }

    @Test
    public void sortNullAndEmptyStringsTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testDataA = {"A_null", null };
        String[] testDataB = {"", "" };
        String[] testDataC = {"C_null", null };
        String[] testDataD = {" ", " " };

        List<String[]> testData = Arrays.asList(testDataB, testDataC, testDataD, testDataA);

        assertEquals("", testData.get(0)[0]);
        assertEquals("C_null", testData.get(1)[0]);
        assertEquals(" ", testData.get(2)[0]);
        assertEquals("A_null", testData.get(3)[0]);
        task1Impl.sort(testData, 1);
        assertEquals("C_null", testData.get(0)[0]);
        assertEquals("A_null", testData.get(1)[0]);
        assertEquals("", testData.get(2)[0]);
        assertEquals(" ", testData.get(3)[0]);

    }

    @Test
    public void sortByNumbersInSubStringTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testData1 = {"1", "!@#$% ^&*()_+1 SDSFS"};
        String[] testData2 = {"2", "!@#$% ^&*()_+2 FEASFASAS"};
        String[] testData3 = {"3", "!@#$% ^&*()_+3ewwerwe"};
        String[] testData4 = {"4", "!@#$% ^&*()_+4EDGGsew"};

        List<String[]> testData = Arrays.asList(testData2, testData4, testData1, testData3);

        assertEquals("!@#$% ^&*()_+2 FEASFASAS", testData.get(0)[1]);
        assertEquals("!@#$% ^&*()_+4EDGGsew", testData.get(1)[1]);
        assertEquals("!@#$% ^&*()_+1 SDSFS", testData.get(2)[1]);
        assertEquals("!@#$% ^&*()_+3ewwerwe", testData.get(3)[1]);
        task1Impl.sort(testData, 1);
        assertEquals("!@#$% ^&*()_+1 SDSFS", testData.get(0)[1]);
        assertEquals("!@#$% ^&*()_+2 FEASFASAS", testData.get(1)[1]);
        assertEquals("!@#$% ^&*()_+3ewwerwe", testData.get(2)[1]);
        assertEquals("!@#$% ^&*()_+4EDGGsew", testData.get(3)[1]);
        assertEquals("!@#$% ^&*()_+4EDGGsew", testData.get(3)[1]);
    }

    @Test
    public void sortAllAsStringsTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testData1 = {"1", "a123"};
        String[] testData2 = {"2", "123a"};
        String[] testData3 = {"3", "!b123a"};

        List<String[]> testData = Arrays.asList(testData2, testData1, testData3);

        assertEquals("123a", testData.get(0)[1]);
        assertEquals("a123", testData.get(1)[1]);
        assertEquals("!b123a", testData.get(2)[1]);
        task1Impl.sort(testData, 1);
        assertEquals("!b123a", testData.get(0)[1]);
        assertEquals("123a", testData.get(1)[1]);
        assertEquals("a123", testData.get(2)[1]);
    }

    @Test
    public void sortSimilarStringsTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testData1 = {"1", "b"};
        String[] testData2 = {"2", "bac"};
        String[] testData3 = {"3", "ba"};

        List<String[]> testData = Arrays.asList(testData2, testData1, testData3);

        assertEquals("bac", testData.get(0)[1]);
        assertEquals("b", testData.get(1)[1]);
        assertEquals("ba", testData.get(2)[1]);
        task1Impl.sort(testData, 1);
        assertEquals("b", testData.get(0)[1]);
        assertEquals("ba", testData.get(1)[1]);
        assertEquals("bac", testData.get(2)[1]);
    }

    @Test
    public void globalSortingTest() {
        IStringRowsListSorter task1Impl = Task1Impl.getInstance();
        String[] testDataA = {"null", "A", "A", null, "A"};
        String[] testDataB = {"null", "B", "B", null, "B"};
        String[] testDataC = {"", "C", "C", "", "C"};
        String[] testDataD = {"", "D", "D", "", "D"};
        String[] testDataE = {" ", "E", "E", " ", "E"};
        String[] testDataF = {"1ram16gb256", "F", "F", "1ram16gb256", "F"};
        String[] testDataG = {"!rpg-7", "G", "G", "!rpg-7", "G"};
        String[] testDataH = {"ПГП-12-1", "H", "H", "ПГП-12-1", "H"};
        String[] testDataJ = {"&ПГП-12-2", "J", "J", "&ПГП-12-2", "J"};
        String[] testDataX = {"ПГП-12-3", "X", "X", "ПГП-12-3", "X"};
        String[] testDataY = {" ПГ-12", "Y", "Y", " ПГ-12", "Y"};

        List<String[]> testData = Arrays.asList(testDataH, testDataA, testDataB, testDataY, testDataF,
                testDataC, testDataJ, testDataX, testDataE, testDataG, testDataD
        );


        assertEquals("ПГП-12-1", testData.get(0)[0]);
        assertEquals("null", testData.get(1)[0]);
        assertEquals("null", testData.get(2)[0]);
        assertEquals(" ПГ-12", testData.get(3)[0]);
        assertEquals("1ram16gb256", testData.get(4)[0]);
        assertEquals("", testData.get(5)[0]);
        assertEquals("&ПГП-12-2", testData.get(6)[0]);
        assertEquals("ПГП-12-3", testData.get(7)[0]);
        assertEquals(" ", testData.get(8)[0]);
        assertEquals("!rpg-7", testData.get(9)[0]);
        assertEquals("", testData.get(10)[0]);

        task1Impl.sort(testData, 3);

        assertEquals("null", testData.get(0)[0]);
        assertEquals("null", testData.get(1)[0]);
        assertEquals("", testData.get(2)[0]);
        assertEquals("", testData.get(3)[0]);
        assertEquals(" ", testData.get(4)[0]);
        assertEquals(" ПГ-12", testData.get(5)[0]);
        assertEquals("!rpg-7", testData.get(6)[0]);
        assertEquals("&ПГП-12-2", testData.get(7)[0]);
        assertEquals("1ram16gb256", testData.get(8)[0]);
        assertEquals("ПГП-12-1", testData.get(9)[0]);
        assertEquals("ПГП-12-3", testData.get(10)[0]);

    }
}