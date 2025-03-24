package com.example;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine;

@CommandLine.Command(name = "flashcard", mixinStandardHelpOptions = true, description = "Flashcard system CLI")
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Flashcard file path")
    private File flashcardFile;

    @CommandLine.Option(names = "--order", description = "Sorting order [random, worst-first, recent-mistakes-first]", defaultValue = "random")
    private String order;

    @CommandLine.Option(names = "--repetitions", description = "Number of repetitions per card", defaultValue = "1")
    private int repetitions;

    @CommandLine.Option(names = "--invertCards", description = "Invert question and answer", defaultValue = "false")
    private boolean invertCards;

    @Override
    public Integer call() {
        System.out.println("Flashcard file: " + flashcardFile);
        System.out.println("Order: " + order);
        System.out.println("Repetitions: " + repetitions);
        System.out.println("Invert Cards: " + invertCards);

        // Flashcard-уудыг ачаалж байна
        List<Flashcard> cards = loadFlashcards(flashcardFile);

        // Картуудын дарааллыг сонгож байна
        if ("recent-mistakes-first".equals(order)) {
            CardOrganizer organizer = new RecentMistakesFirstSorter();
            cards = organizer.organize(cards);
        }

        // Асуултуудыг хэвлэх
        for (Flashcard card : cards) {
            System.out.println("Question: " + card.getQuestion() + " (Mistakes: " + card.getMistakes() + ")");
        }
        SessionStats stats = new SessionStats(10, 10, 6, 4.5); // Түр зуурын өгөгдөл

        Achievement[] achievements = {
            new CorrectAchievement(),
        new RepeatAchievement(),
        new ConfidentAchievement()
        };

        System.out.println("\nAchievements:");
        for (Achievement achievement : achievements) {
            if (achievement.checkAchieved(stats)) {
        System.out.println("- " + achievement.getName());
        }
    }
        return 0;
    }

    // Fake card loader - Жинхэнэ файл ачаалдаг болгож сайжруулж болно
    private List<Flashcard> loadFlashcards(@SuppressWarnings("unused") File file) {
        return List.of(
            new Flashcard("What is Java?", "A programming language", 2),
            new Flashcard("What is OOP?", "Object-Oriented Programming", 1),
            new Flashcard("What is Git?", "Version control system", 3)
        );
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}