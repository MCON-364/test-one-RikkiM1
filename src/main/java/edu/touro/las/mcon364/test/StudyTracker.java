package edu.touro.las.mcon364.test;

import java.util.*;

public class StudyTracker {

    private final Map<String, List<Integer>> scoresByLearner = new HashMap<>();
    private final Deque<UndoStep> undoStack = new ArrayDeque<>();
    StudyTracker st;

    // Helper methods already provided for tests and local inspection.
    public Optional<List<Integer>> scoresFor(String name) {

        return Optional.ofNullable(scoresByLearner.get(name));
    }

    public Set<String> learnerNames() {

        return scoresByLearner.keySet();
    }

    /**
     * Problem 11
     * Add a learner with an empty score list.
     * <p>
     * Return:
     * - true if the learner was added
     * - false if the learner already exists
     * <p>
     * Throw IllegalArgumentException if name is null or blank.
     */
    public boolean addLearner(String name) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException();
        }
        if (scoresByLearner.containsKey(name)) {
            return false;
        }
        scoresByLearner.put(name, new ArrayList<>());
        return true;

    }


    /**
     * Problem 12
     * Add a score to an existing learner.
     * <p>
     * Return:
     * - true if the score was added
     * - false if the learner does not exist
     * <p>
     * Valid scores are 0 through 100 inclusive.
     * Throw IllegalArgumentException for invalid scores.
     * <p>
     * This operation should be undoable.
     */

    public boolean addScore(String name, int score) {

        if (!scoresByLearner.containsKey(name)) {
            return false;
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException();
        }
        scoresByLearner.get(name).add(score);
        UndoStep us = new UndoStep() {
            @Override
            public void undo() {
                scoresByLearner.get(name).add(score);
            }
        };
        undoStack.push(us);
        return true;
    }

    /**
     * Problem 13
     * Return the average score for one learner.
     * <p>
     * Return Optional.empty() if:
     * - the learner does not exist, or
     * - the learner has no scores
     */
    public Optional<Double> averageFor(String name) {

        double grade = 0;

        List<Integer> grades = scoresByLearner.get(name);
        if (scoresByLearner.containsKey(name)) {
            for (Integer student : grades) {
                grade += student;
            }

            double average = grade / grades.size();
            return Optional.of(average);
        }
        return Optional.empty();
    }

    /**
     * Problem 14
     * Convert a learner average into a letter band.
     * <p>
     * A: 90+
     * B: 80-89.999...
     * C: 70-79.999...
     * D: 60-69.999...
     * F: below 60
     * <p>
     * Return Optional.empty() when no average exists.
     */
    public Optional<String> letterBandFor(String name) {
        if (scoresByLearner.containsKey(name)) {
            Optional<Double> avg = st.averageFor(name);
            int x = avg.get().intValue();
            String grade = switch (x) {
                case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> "A";
                case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> "B";
                case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> "C";
                case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> "D";
                default -> {
                    System.out.println("you failed!");
                    yield "F";
                }
            };
            return Optional.of(grade);
        }
        return Optional.empty();
    }

    /**
     * Problem 15
     * Undo the most recent state-changing operation.
     * <p>
     * Return true if something was undone.
     * Return false if there is nothing to undo.
     */
    public boolean undoLastChange() {

        if (undoStack.isEmpty()) {
            return false;
        }
        UndoStep action = undoStack.pop();
        action.undo();

        return true;
    }


}
