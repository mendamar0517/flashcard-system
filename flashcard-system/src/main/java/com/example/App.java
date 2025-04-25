package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import picocli.CommandLine;

@CommandLine.Command(
    name = "flashcard",
    mixinStandardHelpOptions = true,
    description = "Flashcard system CLI"
)
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Flashcard file path")
    private File flashcardFile;

    @CommandLine.Option(names = "--order", description = "Sorting order [random, worst-first, recent-mistakes-first]", defaultValue = "random")
    private String order;

    @CommandLine.Option(names = "--repetitions", description = "Number of repetitions per card", defaultValue = "1")
    private int repetitions;

    @CommandLine.Option(names = "--invertCards", description = "Invert question and answer", defaultValue = "true")
    private boolean invertCards;

    private final Scanner scanner = new Scanner(System.in);
    private Flashcard lastIncorrectCard = null;

    @Override
    public Integer call() {
        System.out.println("\n=== Flashcard System ===");
        System.out.println("Flashcard file: " + flashcardFile);
        System.out.println("Order: " + order);
        System.out.println("Repetitions per card: " + repetitions);
        System.out.println("Invert Cards: " + invertCards);

        List<Flashcard> cards = loadFlashcards(flashcardFile);
        if ("random".equals(order)) {
            Collections.shuffle(cards);
        }

        List<Flashcard> incorrectCards = new ArrayList<>();
        int correctCount, incorrectCount;

        while (true) {
            correctCount = 0;
            incorrectCount = 0;
            incorrectCards.clear();

            for (Flashcard card : cards) {
                for (int i = 0; i < repetitions; i++) {
                    String question = invertCards ? card.getAnswer() : card.getQuestion();
                    String answer = invertCards ? card.getQuestion() : card.getAnswer();

                    System.out.println("\nQuestion: " + question);
                    System.out.print("> ");
                    String userAnswer = scanner.nextLine().trim();

                    if (userAnswer.equalsIgnoreCase(answer)) {
                        System.out.println(" Correct!");
                        correctCount++;
                    } else {
                        System.out.println(" Incorrect! Correct answer: " + answer);
                        incorrectCount++;
                        card.increaseMistakes();
                        incorrectCards.add(card);
                        lastIncorrectCard = card;
                    }
                }
            }
            

            System.out.println("\n=== Results ===");
            System.out.println(" Correct answers: " + correctCount);
            System.out.println(" Incorrect answers: " + incorrectCount);

            System.out.println("\nChoose an option:");
            System.out.println("1. Retry incorrect answers");
            System.out.println("2. Restart all questions");
            System.out.println("3. Exit");

            System.out.print("> ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1 && incorrectCount > 0) {
                cards = new ArrayList<>(incorrectCards);
                
                // Сүүлд алдсанас эхлээд асуух
                Collections.reverse(cards);
                
                // lastIncorrectCard-ыг эхэнд нь тавих
                if (lastIncorrectCard != null && cards.contains(lastIncorrectCard)) {
                    cards.remove(lastIncorrectCard);
                    cards.add(0, lastIncorrectCard); // Эхэнд нь нэмнэ
                }
            } else if (choice == 2) {
                cards = loadFlashcards(flashcardFile);
                Collections.shuffle(cards);
            } else if (choice == 3) {
                System.out.println(" Exiting...");
                break;
            } else {
                System.out.println(" Invalid choice. Try again.");
            }
        }
        return 0;
    }

    private void askSingleQuestion(Flashcard card) {
        String question = invertCards ? card.getAnswer() : card.getQuestion();
        String answer = invertCards ? card.getQuestion() : card.getAnswer();

        System.out.println("\nQuestion: " + question);
        System.out.print("> ");
        String userAnswer = scanner.nextLine().trim();

        if (userAnswer.equalsIgnoreCase(answer)) {
            System.out.println(" Correct!");
        } else {
            System.out.println(" Incorrect! Correct answer: " + answer);
            card.increaseMistakes();
            lastIncorrectCard = card;
        }
    }

    private List<Flashcard> loadFlashcards(@SuppressWarnings("unused") File file) {
        return new ArrayList<>(List.of(
            new Flashcard("What is Java?", "A programming language", 0),
            new Flashcard("What is OOP?", "Object-Oriented Programming", 0),
            new Flashcard("2+2=?", "4", 0),
            new Flashcard("Mongol?", "uls", 0),
            new Flashcard("What is Git?", "Version control system", 0)
        ));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
