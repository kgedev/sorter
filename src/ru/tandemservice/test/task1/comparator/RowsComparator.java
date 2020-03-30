package ru.tandemservice.test.task1.comparator;

import ru.tandemservice.test.task1.SubString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

/**
 * Класс-компоратор для сравнения массивов строк.
 * @index - номер элемента в массиве по которому предстоит сравнивать строки.
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

        return compareNotNullNotEmptyStrings(firstStr, secondStr);
    }

    /**
     * Метод позволяет сравнивать две не нулевые не пустые строки.
     * */
    private int compareNotNullNotEmptyStrings(String str1, String str2) {
        List<SubString> str1List = getSubstringsList(str1);
        List<SubString> str2List = getSubstringsList(str2);

        //определяем строку с наименьшей длиной.
        int minSize = Math.min(str1List.size(), str2List.size());
        int result = 0;

        for (int i = 0; i < minSize; i++) {
          result = str1List.get(i).compareTo(str2List.get(i));
          if (result != 0) break;
        }

        if (str1List.size() != str2List.size() && result == 0) {
            if (str1List.size() == minSize ) {
                result = -1;
            } else {
                result = 1;
            }

        }

        return result;
    }

    /**
     * Метод позволяющий разбить строку на подстроки по регулярному выражению.
     * @string - строка которую необходимо разбить на подстроки.
     * */
    private List<SubString> getSubstringsList(String string) {
        String stringDelimiter = "(\\D+)|(\\d+)";
        List<SubString> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(stringDelimiter);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            SubString subString = new SubString(matcher.group());
            result.add(subString);
        }

        return result;
    }
}
