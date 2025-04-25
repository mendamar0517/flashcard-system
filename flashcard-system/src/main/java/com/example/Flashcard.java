package com.example;

public class Flashcard {
    private final String question;
    private final String answer;
    private int mistakes;

    public Flashcard(String question, String answer, int mistakes) {
        this.question = question;
        this.answer = answer;
        this.mistakes = mistakes;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void increaseMistakes() {
        mistakes++;
    }
}
