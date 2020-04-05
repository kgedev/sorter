package ru.tandemservice.test.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <h1>Задание №2</h1>
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по времени работы
 * с учетом скорости выполнения операции присвоения номера:
 * большим плюсом (хотя это и не обязательно) будет оценка числа операций, доказательство оптимальности
 * или указание области, в которой алгоритм будет оптимальным.</p>
 */
public class Task2Impl implements IElementNumberAssigner {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IElementNumberAssigner INSTANCE = new Task2Impl();

    @Override
    public void assignNumbers(final List<IElement> elements) {
        // напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного, документированного и понятного кода, работающего за разумное время.
        AtomicInteger index = new AtomicInteger();
        Map<Integer, Integer> mainCache = elements.stream().collect(Collectors.toMap(elem -> index.getAndIncrement(), IElement::getNumber));
        List<Integer> elementsValues = new ArrayList<>(mainCache.values());

        Map<Integer, Integer> cache1 = new HashMap<>();
        Map<Integer, Integer> cache2 = new HashMap<>();
        Map<Integer, Integer> cache3 = new HashMap<>();

       elements.forEach(element -> {
           int value = element.getNumber();
           int idx = elements.indexOf(element);

           boolean idxNotEqualValue = value != idx;
           boolean valueInElementsIdxRange = value < elements.size() && value >= 0;
           boolean elementsValuesContainsIdx = elementsValues.contains(idx);
           boolean crossMatch = value != elements.indexOf(elements.get(value));

           boolean condition1 = idxNotEqualValue && !valueInElementsIdxRange && !elementsValuesContainsIdx;
           boolean condition2 = idxNotEqualValue && !valueInElementsIdxRange && elementsValuesContainsIdx;
           boolean condition3 = idxNotEqualValue && valueInElementsIdxRange && elementsValuesContainsIdx && !crossMatch;
           boolean condition4 = idxNotEqualValue && valueInElementsIdxRange && elementsValuesContainsIdx && crossMatch;

           if(condition1) {
               element.setupNumber(idx);
           } else if (condition2) {
               cache1.put(idx, value);
           } else if (condition3) {
               cache2.put(idx, value);
           } else if (condition4) {
               cache3.put(idx, value);
           }

       });
        a1Algorithm(elements, cache2, mainCache);
    }

    //проверяет равен ли индекс элемента его значению
    private boolean isIndexMatchElementValue(int idx, IElement element) {
        return idx == element.getNumber();
    }

    private void a1Algorithm(List<IElement> elements, Map<Integer, Integer> cache2, Map<Integer, Integer> mainCache) {
        int random_number = 0;
        do {
            random_number = mainCache.size() + (int) (Math.random() * Integer.MAX_VALUE);
        } while(mainCache.values().contains(random_number));

        Integer elementIndex = cache2.entrySet().stream().findFirst().get().getKey();
        Integer tmp = elements.get(elementIndex).getNumber();
        elements.get(elementIndex).setupNumber(random_number);

        while (cache2.size() != 0) {

            elementIndex = tmp;
            tmp = cache2.get(elementIndex);
            elements.get(elementIndex).setupNumber(elementIndex);
            cache2.remove(elementIndex);
        }
    }

    private void a2Algrorithm() {

    }

}
