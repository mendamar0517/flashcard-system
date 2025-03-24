package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionStats {
    private final int totalQuestions;
    private final int correctAnswers;
    private int repeatedAnswers;
    private final double avgTimePerQuestion;

    public SessionStats(int totalQuestions, int correctAnswers, int repeatedAnswers, double avgTimePerQuestion) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.repeatedAnswers = repeatedAnswers;
        this.avgTimePerQuestion = avgTimePerQuestion;
    }

    public SessionStats(double avgTimePerQuestion, int correctAnswers, int totalQuestions) {
        this.avgTimePerQuestion = avgTimePerQuestion;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
    }

    public int getRepeatedAnswers() {
        return repeatedAnswers;
    }

    public void setRepeatedAnswers(int repeatedAnswers) {
        this.repeatedAnswers = repeatedAnswers;
    }

    public double getAvgTimePerQuestion() {
        return avgTimePerQuestion;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}