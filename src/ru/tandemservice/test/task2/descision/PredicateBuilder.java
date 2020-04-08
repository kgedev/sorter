package ru.tandemservice.test.task2.descision;

import ru.tandemservice.test.task2.IElement;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


/*
 * Класс для составления условий, по которым в дальнейшем все элементы будут разбиваться на группы
 * и распределяться по кэшам.
 * */
public class PredicateBuilder {
    private Predicate<Map.Entry<Integer, Integer>> keyEqualValue;
    private Predicate<Map.Entry<Integer, Integer>> valueIsInIndexesRange;
    private Predicate<Map.Entry<Integer, Integer>> elementsValuesContainsIndex;

    public PredicateBuilder(List<IElement> elements, Map<Integer, Integer> commonCache) {
        keyEqualValue = entry -> entry.getValue().equals(entry.getKey());
        valueIsInIndexesRange = entry -> entry.getValue() < elements.size() && entry.getValue() >= 0;
        elementsValuesContainsIndex = entry -> commonCache.containsValue(entry.getKey());
    }

    public Predicate<Map.Entry<Integer, Integer>> changeNowElementNumberPredicate() {
        return keyEqualValue.negate()
                .and(valueIsInIndexesRange.negate()
                .and(elementsValuesContainsIndex.negate()));
    }

    public Predicate<Map.Entry<Integer, Integer>> getValuesOutOfIndexesRangePredicate() {
        return keyEqualValue.negate()
                .and(valueIsInIndexesRange.negate()
                .and(elementsValuesContainsIndex));
    }

    public Predicate<Map.Entry<Integer, Integer>> getValuesInIndexesRangePredicate() {
        return keyEqualValue.negate()
                .and(valueIsInIndexesRange);
    }

}
