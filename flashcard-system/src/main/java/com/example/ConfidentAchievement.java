package com.example;

public class ConfidentAchievement implements Achievement {
    @Override
    public boolean checkAchieved(SessionStats stats) {
        return stats.getCorrectAnswers() >= 3;
    }

    @Override
    public String getName() {
        return "CONFIDENT: Answered a card correctly at least 3 times!";
    }
}

