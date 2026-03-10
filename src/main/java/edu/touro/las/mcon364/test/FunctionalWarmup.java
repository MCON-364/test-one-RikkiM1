package edu.touro.las.mcon364.test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalWarmup {

    /**
     * Problem 1
     * Return a Supplier that gives the current month number (1-12).
     */
    public static Supplier<Integer> currentMonthSupplier() {

        Supplier<Integer> currentMonthSupplier = () -> LocalDate.now().getMonthValue();
        return currentMonthSupplier;
    }

    /**
     * Problem 2
     * Return a Predicate that is true only when the input string
     * has more than 5 characters.
     */
    public static Predicate<String> longerThanFive() {
        return s -> s.length() > 5;
    }

    /**
     * Problem 3
     * Return a Predicate that checks whether a number is both:
     * - positive
     * - even
     * <p>
     * Prefer chaining smaller predicates.
     */
    public static Predicate<Integer> positiveAndEven() {

        Predicate<Integer> Even = x -> x % 2 == 0;
        Predicate<Integer> positive = x -> x > 0;
        return positive.and(Even);
    }

    /**
     * Problem 4
     * Return a Function that counts words in a string.
     * <p>
     * Notes:
     * - Trim first.
     * - Blank strings should return 0.
     * - Words are separated by one or more spaces (use can use regex "\\s+")
     *
     */
    public static Function<String, Integer> wordCounter() {
        Function<String, String> trim = String::trim;
        ;
        Function<String, Integer> l = s -> {

            int counter = 0;
            for (int i = 0; i < s.length(); i++) {
                trim.apply(s).split("\\s+");
                counter++;
            }
            return counter;
        };

        return l;

    }


    /**
     * Problem 5
     * Process the input labels as follows:
     * - remove blank strings
     * - trim whitespace
     * - convert to uppercase
     * - return the final list in the same relative order
     * <p>
     * Example:
     * ["  math ", "", " java", "  "] -> ["MATH", "JAVA"]
     */
    public static List<String> cleanLabels(List<String> labels) {
        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;
        Predicate<String> longerThan0 = s -> s.length() > 0;

        for (String label : labels) {

            if (longerThan0.test(label)) {
                String result = upper.apply(label);
                trim.apply(result);
            }

        }
        return labels;
    }
}


