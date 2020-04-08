package ru.tandemservice.test.task2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
        ElementsCache cache = new ElementsCache(elements);
        PredicateBuilder predicate = new PredicateBuilder(elements, cache.getCommonCache());

        /**
         * пробегаемся по кэшу и устанавливаем в качестве значения индекс для элементов, у которых:
         *  - значение элемента не равно его индексу
         *  - значение элемента не входит в диапазон индексов коллекции элементов
         *  - в коллекции нет ни одного элемента значение которого было бы равно текущему индексу
         *
         * */
        cache.getCommonCache().entrySet().stream()
                .filter(predicate.changeNowElementNumberPredicate())
                .forEach(entry -> elements.get(entry.getKey()).setupNumber(entry.getKey()));

        if (!cache.getCache2().isEmpty()) {
            a1Algorithm(elements, cache);
        }
        if (!cache.getCache3().isEmpty()) {
            a2Algorithm();
        }
        cache.getCache1().keySet().forEach(key -> elements.get(key).setupNumber(key));
    }

    private void a1Algorithm(List<IElement> elements, ElementsCache cache) {
        Map<Integer, Integer> commonCache = cache.getCommonCache();
        Map<Integer, Integer> cache2 = cache.getCache2();

        Integer elementIndex = findIdx(cache);
        Integer tmp = null;

        while (cache2.size() != 0) {

            if (elementIndex == null) {
                elementIndex = cache2.entrySet().stream().findFirst().get().getKey();
                Integer tempValue = getRandomNumberNotContainsInTheCollection(commonCache);
                elements.get(elementIndex).setupNumber(tempValue);
                tmp = cache2.get(elementIndex);
                cache2.put(elementIndex, tempValue);
            } else {
                elements.get(elementIndex).setupNumber(elementIndex);
                tmp = cache2.get(elementIndex);
                cache2.remove(elementIndex);
            }

            if (!cache2.containsKey(tmp) && cache2.size() != 0) {
                elementIndex = null;
            } else {
                elementIndex = tmp;
            }
        }
    }

    private void a2Algorithm() {

    }

    private Integer findIdx(ElementsCache cache) {
        return cache.getCache2().keySet().stream()
                .filter(key -> !cache.getCache2().containsValue(key))
                .findFirst()
                .orElse(null);
    }

    private int getRandomNumberNotContainsInTheCollection(Map<Integer, Integer> commonCache) {
        int random_number = 0;
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
