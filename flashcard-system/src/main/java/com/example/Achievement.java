package com.example;

public interface Achievement {
    boolean checkAchieved(SessionStats stats);
    String getName();
}
