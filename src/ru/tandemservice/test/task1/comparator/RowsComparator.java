package ru.tandemservice.test.task1.comparator;

import ru.tandemservice.test.task1.SubString;

import java.util.*;

import static java.util.Objects.isNull;

/**
 * Класс-компоратор для сравнения подстрок.
 * */
public class RowsComparator implements Comparator<String[]> {
    private int index;

    public RowsComparator(int index) {
        this.index = index;
    }

    @Override
    public int compare(String[] first, String[] second) {
        String firstStr = first[index];
        String secondStr = second[index];

        if (isNull(firstStr) && isNull(secondStr)) return 0;
        if (isNull(firstStr)) return -1;
        if (isNull(secondStr)) return 1;

        if (firstStr.trim().isEmpty() && secondStr.isEmpty()) return 0;
        if (firstStr.trim().isEmpty() ) return -1;
        if (secondStr.trim().isEmpty() ) return 1;

        return compareStrings(firstStr, secondStr);
    }

    /**
     * Метод позволяющий сравнить две ненулевые не пустые строки.
     *
     * */
    private int compareStrings(String str1, String str2) {
        List<SubString> str1List = getSubstringsList(str1);
        List<SubString> str2List = getSubstringsList(str2);

        int minSize = Math.min(str1List.size(), str2List.size());
        int result = 0;

        for (int i = 0; i < minSize; i++) {
          result = str1List.get(i).compareTo(str2List.get(i));
          if (result != 0) break;
        }

        return result;
    }

    /**
     * Метод позволяющий разбить строку на подстроки следующим образом:
     * */
    public static List<SubString> getSubstringsList(String string) {
        List<SubString> result = new ArrayList<>();
        List<Integer> digitChars = Arrays.asList(48, 49, 50, 51, 52, 53, 54, 55, 56, 57);
        StringBuilder sb = new StringBuilder();
        final Integer[] previous = {null};

        string.chars().forEach(symbol -> {
            if (!isNull(previous[0])
                    && (!digitChars.contains(symbol) || !digitChars.contains(previous[0]))
                    && (digitChars.contains(symbol) || digitChars.contains(previous[0]))) {
                addSubstringToCollection(sb.toString(), result);
                sb.setLength(0);
            }
            sb.append((char) symbol);
            previous[0] = symbol;

        });
        addSubstringToCollection(sb.toString(), result);

        return result;
    }

    private static void addSubstringToCollection(String string, List<SubString> list) {
        try {
            list.add(new SubString<java.io.Serializable>(Integer.parseInt(string)));
        } catch (NumberFormatException e) {
            list.add(new SubString<java.io.Serializable>(string));
        }
    }
}
