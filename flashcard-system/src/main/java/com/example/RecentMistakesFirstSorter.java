package com.example;

import java.util.Comparator;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public List<Flashcard> organize(List<Flashcard> cards) {
        cards.sort(Comparator.comparingInt(Flashcard::getMistakes).reversed());
        return cards;
    }
}