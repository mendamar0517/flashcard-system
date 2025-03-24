package com.example;

import java.io.File;
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
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
