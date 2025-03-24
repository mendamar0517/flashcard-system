package com.example;

public class RepeatAchievement implements Achievement {
    @Override
    public boolean checkAchieved(SessionStats stats) {
        return stats.getRepeatedAnswers() > 5;
    }

    @Override
    public String getName() {
        return "REPEAT: Answered a card more than 5 times!";
    }
}
