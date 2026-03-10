package edu.touro.las.mcon364.test;

import java.util.*;
import java.util.stream.Collectors;

public class BasicStreamsQuiz {

    private final Map<String, List<Integer>> scoresByCourse;

    public BasicStreamsQuiz() {
        scoresByCourse = new LinkedHashMap<>();
        scoresByCourse.put("Algorithms", List.of(91, 84, 96, 88));
        scoresByCourse.put("Databases", List.of(77, 81, 79, 85));
        scoresByCourse.put("Networks", List.of(68, 73, 70, 75));
        scoresByCourse.put("Java", List.of(95, 92, 90, 98));
    }

    /**
     * Problem 6
     * Return all course names sorted alphabetically.
     */
    public List<String> getSortedCourseNames() {
        // sorted() - natural order (must see all elements)
        return getSortedCourseNames().stream()
                .sorted()
                .toList();

    }


    /**
     * Problem 7
     * Across all courses, count how many scores are greater than or equal to threshold.
     */
    public long countScoresAtLeast(int threshold) {

        return scoresByCourse.entrySet()
                .stream()
                .filter(entry -> entry.getValue().stream()
                        .count() >= threshold).count();

    }

    /**
     * Problem 8
     * Return the first word whose length is greater than minLength.
     * If none exists, return Optional.empty().
     */
    public Optional<String> firstLongWord(List<String> words, int minLength) {
        boolean longer = words.stream()
                .anyMatch(n -> n.length() > minLength);
        return longer ? Optional.of(words.get(words.size() - 1)) : Optional.empty();
    }



    /**
     * Problem 9
     * Return a new list containing the square of each number.
     * Use streams.
     */
    public List<Integer> squareAll(List<Integer> numbers) {
        return numbers.stream()
                .filter(n ->  n * n)
                .collect(Collectors.toList());

    }

    /**
     * Problem 10
     * Return the average of all passing scores across all courses.
     * A passing score is 70 or above.
     *
     * Return 0.0 if there are no passing scores.
     */
    public double averagePassingScore() {

  return scoresByCourse.entrySet().stream()
          .filter(av -> av >= 70 )
          .flatMap(r -> score().stream())
                .average()
                .orElse(0.0);  }

}
