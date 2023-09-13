package Circular_LinkedList;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int numberOfAttempts = 10;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between " + minRange + " and " + maxRange + ".");

        int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;

        while (numberOfAttempts > 0) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess < minRange || userGuess > maxRange) {
                System.out.println("Please enter a valid number between " + minRange + " and " + maxRange + ".");
                continue;
            }

            numberOfAttempts--;

            if (userGuess == randomNumber) {
                score += numberOfAttempts + 1;
                System.out.println("Congratulations! You guessed the number. Your score: " + score);
                break;
            } else if (userGuess < randomNumber) {
                System.out.println("Try a higher number. Remaining attempts: " + numberOfAttempts);
            } else {
                System.out.println("Try a lower number. Remaining attempts: " + numberOfAttempts);
            }
        }

        if (numberOfAttempts == 0) {
            System.out.println("Out of attempts! The number was: " + randomNumber);
        }

        scanner.close();
    }
}
