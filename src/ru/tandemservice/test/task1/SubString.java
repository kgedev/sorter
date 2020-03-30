package ru.tandemservice.test.task1;

import java.util.Objects;

/**
 * Класс для хранения подстроки в формате String.
 * */
public class SubString implements Comparable<SubString>{
    private  String substring;

    public SubString(String substring) {
        this.substring = substring;
    }

    public String getSubstring() {
        return substring;
    }

    @Override
    public int compareTo(SubString other) {
        String numbersRegExpTemplate = "^[0-9]+$";
        boolean thisIsNumber = this.substring.matches(numbersRegExpTemplate);
        boolean otherIsNumber = other.substring.matches(numbersRegExpTemplate);

        if(thisIsNumber && otherIsNumber) {
            return new Integer(Integer.parseInt(this.substring)).compareTo(new Integer(other.getSubstring()));
        } else {
            return this.substring.compareTo(other.getSubstring());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubString subString = (SubString) o;
        return Objects.equals(substring, subString.substring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(substring);
    }

    @Override
    public String toString() {
        return "SubString{" +
                "substring='" + substring + '\'' +
                '}';
    }

}
