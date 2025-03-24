package com.example;

public class Flashcard {
    private final String question;
    private final String answer;
    private int mistakes;

    // ✅ Шинэ конструктор (Энэ байхгүй байсан)
    public Flashcard(String question, String answer, int mistakes) {
        this.question = question;
        this.answer = answer;
        this.mistakes = mistakes;
    }

    // getQuestion() метод нэмж байна
    public String getQuestion() {
        return question;
    }

    //  getAnswer() метод (шаардлагатай бол)
    public String getAnswer() {
        return answer;
    }

    //  getMistakes() метод
    public int getMistakes() {
        return mistakes;
    }

    // increaseMistake() - алдаа нэмэх функц
    public void increaseMistake() {
        this.mistakes++;
    }
}
