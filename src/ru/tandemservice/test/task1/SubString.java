package ru.tandemservice.test.task1;

import java.util.Objects;

public class SubString<T> implements Comparable<T>{
    private  T substring;

    public SubString(T substring) {
        this.substring = substring;
    }

    public T getSubstring() {
        return substring;
    }

    public void setSubstring(T substring) {
        this.substring = substring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubString<?> subString1 = (SubString<?>) o;
        return Objects.equals(substring, subString1.substring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(substring);
    }

    @Override
    public String toString() {
        return substring.toString();
    }

    @Override
    public int compareTo(T t) {
        if (this.substring.getClass().equals(t.getClass()) && this.substring.getClass().equals(Integer.class)) {
           return ((Integer) this.substring).compareTo((Integer) t);
        } else {
            return this.substring.toString().compareTo(t.toString());
        }
    }
}
