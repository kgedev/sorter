package ru.tandemservice.test.task2.assigner;

import ru.tandemservice.test.task2.IElement;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class NumbersAssigner {

    public void assignNumbers(List<IElement> elements) {
        ElementsCache cache = new ElementsCache(elements);
        PredicateBuilder predicate = new PredicateBuilder(elements, cache.getCommonCache());

        /*
         * Пробегаемся по кэшу и устанавливаем в качестве значения индекс для элементов, у которых:
         *  - значение элемента не равно его индексу
         *  - значение элемента не входит в диапазон индексов коллекции элементов
         *  - в коллекции нет ни одного элемента значение которого было бы равно текущему индексу
         *
         * */
        cache.getCommonCache().entrySet().stream()
                .filter(predicate.changeNowElementNumberPredicate())
                .forEach(entry -> elements.get(entry.getKey()).setupNumber(entry.getKey()));

        if (!cache.getValuesInIndexesRange().isEmpty()) {
            assignIndexesToElementNumbers(elements, cache);
        }

        cache.getValuesOutOfIndexesRange().keySet().forEach(key -> elements.get(key).setupNumber(key));
    }

    private void assignIndexesToElementNumbers(List<IElement> elements, ElementsCache cache) {
        Map<Integer, Integer> commonCache = cache.getCommonCache();
        Map<Integer, Integer> valuesInIndexesRange = cache.getValuesInIndexesRange();

        Integer elementIndex = findIdx(valuesInIndexesRange);
        Integer tmp;

        while (valuesInIndexesRange.size() != 0) {
            if (elementIndex == null) {
                elementIndex = valuesInIndexesRange.entrySet().stream().findFirst().get().getKey();
                int tempValue = getRandomNumberNotContainsInTheCollection(commonCache);
                elements.get(elementIndex).setupNumber(tempValue);
                tmp = valuesInIndexesRange.get(elementIndex);
                valuesInIndexesRange.put(elementIndex, tempValue);
            } else {
                elements.get(elementIndex).setupNumber(elementIndex);
                tmp = valuesInIndexesRange.get(elementIndex);
                valuesInIndexesRange.remove(elementIndex);
            }

            if (!valuesInIndexesRange.containsKey(tmp) && valuesInIndexesRange.size() != 0) {
                elementIndex = findIdx(valuesInIndexesRange);
            } else {
                elementIndex = tmp;
            }
        }
    }

    /*
     * Возвращает индекс элемента из кэша ValuesInIndexesRange,
     * который не сожержится ни в одном элементе в качестве значения из общего кэша.
     * с элемента с таким индексом начнется замена.
     *
     * */
    private Integer findIdx(Map<Integer, Integer> cache) {
        return cache.keySet().stream()
                .filter(key -> !cache.containsValue(key))
                .findFirst()
                .orElse(null);
    }

    /*
     * Возвращает число, которое будет использоваться в качестве временного значения, для зеркальных элементов
     * elements.get(0).getNumber() = 1 и elements.get(1).getNumber = 0
     * */
    private int getRandomNumberNotContainsInTheCollection(Map<Integer, Integer> commonCache) {
        int random_number;

        do {
            int maxValue = commonCache.values().stream().max(Comparator.naturalOrder()).get();
            if (maxValue != Integer.MAX_VALUE) {
                random_number = maxValue + 1;
            } else {
                random_number = commonCache.size() + (int) (Math.random() * Integer.MAX_VALUE);
            }
        } while (commonCache.containsValue(random_number));

        return random_number;
    }
}
