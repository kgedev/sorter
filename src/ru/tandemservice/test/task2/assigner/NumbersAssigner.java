package ru.tandemservice.test.task2.assigner;

import ru.tandemservice.test.task2.IElement;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class NumbersAssigner {

    public void assignNumbers(List<IElement> elements) {
        ElementsCache cache = new ElementsCache(elements);
        Map<Integer, Integer> expectedOrder = cache.getExpectedElementsOrder();
        Map<Integer, Integer> actualOrder = cache.getActualElementsOrder();

        if (actualOrder.isEmpty()) return;

        int stub = getStub(expectedOrder);
        int tmp = stub;
        int elementIdx;

        while(!actualOrder.isEmpty()) {
            if (tmp == stub) {
                elementIdx = cache.getFirstElementIndexFromActualMap();
                tmp = elements.get(elementIdx).getNumber();
                elements.get(elementIdx).setupNumber(stub);
                actualOrder.put(elementIdx, stub);
            } else {
                Integer expectedPosition = cache.getKeyByValueFromExpectedMap(tmp);
                tmp = actualOrder.get(expectedPosition);
                elements.get(expectedPosition).setupNumber(expectedOrder.get(expectedPosition));
                actualOrder.remove(expectedPosition);
            }
        }

    }
    /*
     * Возвращает число, которое будет использоваться в качестве временного значения
     * @param elementsMap - коллекция для которой нужно получить временное значение.
     * полученное значение не будет содержаьбся в elementsMap.
     * */
    private int getStub(Map<Integer, Integer> elementsMap) {
        int random_number;

        do {
            int maxValue = elementsMap.values().stream().max(Comparator.naturalOrder()).get();
            if (maxValue != Integer.MAX_VALUE) {
                random_number = maxValue + 1;
            } else {
                random_number = elementsMap.size() + (int) (Math.random() * Integer.MAX_VALUE);
            }
        } while (elementsMap.containsValue(random_number));

        return random_number;
    }

}
