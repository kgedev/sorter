package ru.tandemservice.test.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * одна из возможных реализаций {@link IElement}
 */
public final class ElementExampleImpl implements IElement {

    public static class Context {

        // количество операций присвоения
        private int operationCount = 0;
        public int getOperationCount() { return this.operationCount; }

        // проверка на уникальность номеров элементов
        private Map<Integer, ElementExampleImpl> uniqueMap = new HashMap<>();

    }


    private final Context context;
    private final long id;
    private Integer number = null;

    /**
     * @param number номер элемента
     */
    public ElementExampleImpl(final Context context, final int number) {
        this.context = context;
        this.id = System.identityHashCode(this);
        this.setNumber(number);
    }

    @Override
    public long getId() { return this.id; }

    @Override
    public int getNumber() { return this.number.intValue(); }

    private void setNumber(final int number) {
        if (null != this.number) {
            if (this != this.context.uniqueMap.remove(this.number)) {
                throw new IllegalStateException("Unexpected situation.");
            }
        }

        final ElementExampleImpl old = this.context.uniqueMap.put(this.number = number, this);
        if (null == old) { return; }
        if (this == old) { return; }
        throw new IllegalStateException("Duplicate numbers.");
    }

    @Override
    public void setupNumber(final int number) {
        this.setNumber(number);

        // число таких операций фиксируется
        this.context.operationCount++;

        // по условию задачи, данная операция «долгая»
        // try { Thread.sleep(10); }
        // catch (final Throwable t) {}
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new UnsupportedOperationException(); // клонировать элементы нельзя
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementExampleImpl that = (ElementExampleImpl) o;
        return id == that.id &&
                Objects.equals(context, that.context) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, id, number);
    }
}