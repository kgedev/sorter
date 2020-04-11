package ru.tandemservice.test.task2.assigner;

import ru.tandemservice.test.task2.IElement;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Класс для кэширования элементов. Разбивает элементы по общим признакам на группы.
 * Каждой группе (кэшу) соответствует HashMap<Integer, Integer>, где в качестве:
 *  - key - используется индекс элемента IElement из списка List<IElement> elements
 *  - value - значение поля number элемента IElement.
 * */
public class ElementsCache {
    private final List<IElement> elements;
    private Map<Integer, Integer> actualElementsOrder;
    private Map<Integer, Integer> expectedElementsOrder;

    public ElementsCache(List<IElement> elements) {
        this.elements = elements;
        actualElementsOrder = getActualOrderForAssignNumbers();
        expectedElementsOrder = getExpectedOrder();
    }

    private Map<Integer, Integer> getActualOrder() {
        AtomicInteger index = new AtomicInteger();
        return elements.stream()
                .peek(element -> {
                    if (element == null) index.getAndIncrement();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(elem -> index.getAndIncrement(), IElement::getNumber));
    }

    private Map<Integer, Integer> getExpectedOrder() {
        AtomicInteger index = new AtomicInteger(0);
        Map<Integer, Integer> result = getActualOrder();

        List<Integer> expectedValuesOrderList = elements.stream()
                .filter(Objects::nonNull)
                .map(IElement::getNumber)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        result.entrySet().forEach(entry -> entry.setValue(expectedValuesOrderList.get(index.getAndIncrement())));

        return result;
    }

    private Map<Integer, Integer> getActualOrderForAssignNumbers() {
        return getActualOrder().entrySet().stream()
                .filter(entry -> !entry.getValue().equals(getExpectedOrder().get(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Integer> getActualElementsOrder() {
        return actualElementsOrder;
    }

    public Map<Integer, Integer> getExpectedElementsOrder() {
        return expectedElementsOrder;
    }

    /**
     * Возвращает ключ для пееданного значения.
     * @param value - значение, для которого нужно получить ключ;
     * */
    public Integer getKeyByValueFromExpectedMap(Integer value) {
        return expectedElementsOrder.entrySet().stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }

    /**
     * Возвращает ключ первого элемента в коллекции.
     *  key - индекс IElement в коллекции List<IElement>
     *  value - значение поля number IElement
     * */
    public Integer getFirstElementIndexFromActualMap() {
        return actualElementsOrder.keySet().stream()
                .findFirst()
                .orElse(null);
    }
}
