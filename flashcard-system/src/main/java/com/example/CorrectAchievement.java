package com.example;

public class CorrectAchievement implements Achievement {
    @Override
    public boolean checkAchieved(SessionStats stats) {
        return stats.getTotalQuestions() == stats.getCorrectAnswers();
    }

    @Override
    public String getName() {
        return "CORRECT: All questions were answered correctly!";
    }
}
