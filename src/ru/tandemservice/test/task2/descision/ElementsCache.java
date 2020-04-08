package ru.tandemservice.test.task2.descision;

import ru.tandemservice.test.task2.IElement;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Класс для кэширования элементов. Разбивает элементы по общим признакам на группы.
 * Каждой группе (кэшу) соответствует HashMap<Integer, Integer>, где в качестве:
 *  - key - используется индекс элемента IElement из списка List<IElement> elements
 *  - value - значение поля number элемента IElement.
 * */
public class ElementsCache {
    private Map<Integer, Integer> commonCache;
    private PredicateBuilder predicateBuilder;

    public ElementsCache(List<IElement> elements) {
        AtomicInteger index = new AtomicInteger();
        commonCache = elements.stream().collect(Collectors.toMap(elem -> index.getAndIncrement(), IElement::getNumber));
        predicateBuilder = new PredicateBuilder(elements, commonCache);
    }

    /**
     * Кэш всех элементов из списка List<IElement> elements в виде Map<Integer, Integer> где
     * - key - индекс элемента в списке elements
     * - value - значение поле number элемента IElement
     *
     **/

    public Map<Integer, Integer> getCommonCache() {
        return commonCache;
    }

    /**
     * Формируем кэш элементов, в который попадают элементы у которых:
     *  - значение элемента не равно его индексу
     *  - значение не входит в диапазон индексов коллекции элементов 0 > val >= arr.length
     *  - в коллекции есть элемент, значение которого равно текущему индексу
     *  list[12 8 16 0 14] - попадет только 0-й элемент.
     * */
    public  Map<Integer, Integer> getValuesOutOfIndexesRange() {
        return commonCache.entrySet().stream()
                .filter(predicateBuilder.getValuesOutOfIndexesRangePredicate())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Формируем кэш элементов, в который попадают элементы у которых:
     *  - значение элемента не равно его индексу
     *  - значение входит в диапазон индексов коллекции элементов 0 <= val < arr.length
     *  list[1 0 16 2 14] - попадут 0, 1 и 3-й элементы.
     * */
    public Map<Integer, Integer> getValuesInIndexesRange() {
        return commonCache.entrySet().stream()
                .filter(predicateBuilder.getValuesInIndexesRangePredicate())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
