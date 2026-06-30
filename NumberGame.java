import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      WELCOME TO THE NUMBER GAME");
        System.out.println("========================================");

        int totalScore = 0;
        int roundsWon = 0;
        int roundNum = 0;

        while (true) {
            roundNum++;

            int low = getIntInput("\nEnter the lower bound of the range: ");
            int high = getIntInput("Enter the upper bound of the range: ");
            while (high <= low) {
                System.out.println("Upper bound must be greater than lower bound.");
                high = getIntInput("Enter the upper bound of the range: ");
            }

            System.out.print("Limit the number of attempts? (y/n): ");
            String attemptsChoice = scanner.nextLine().trim().toLowerCase();
            int maxAttempts = -1; // -1 means unlimited
            if (attemptsChoice.equals("y")) {
                maxAttempts = getIntInput("Enter the maximum number of attempts: ");
            }

            int attemptsUsed = playRound(roundNum, low, high, maxAttempts);
            int roundScore = calculateRoundScore(attemptsUsed);
            totalScore += roundScore;

            if (attemptsUsed != -1) {
                roundsWon++;
            }
            System.out.println("Score for this round: " + roundScore);

            System.out.println("\nRounds won so far: " + roundsWon + "/" + roundNum);
            System.out.println("Total score: " + totalScore);

            System.out.print("\nWould you like to play another round? (y/n): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("y")) {
                break;
            }
        }

        System.out.println("\n========================================");
        System.out.println("           GAME OVER - SUMMARY");
        System.out.println("========================================");
        System.out.println("Total rounds played: " + roundNum);
        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Final score: " + totalScore);
        System.out.println("Thanks for playing!");

        scanner.close();
    }

    /**
     * Plays a single round of the guessing game.
     * Returns the number of attempts used if the user wins, or -1 if they lose.
     */
    private static int playRound(int roundNum, int low, int high, int maxAttempts) {
        int target = low + random.nextInt(high - low + 1);
        int attemptsUsed = 0;

        System.out.println("\n--- Round " + roundNum + " ---");
        System.out.println("I'm thinking of a number between " + low + " and " + high + ".");
        if (maxAttempts > 0) {
            System.out.println("You have " + maxAttempts + " attempts to guess it.");
        }

        while (true) {
            if (maxAttempts > 0 && attemptsUsed >= maxAttempts) {
                System.out.println("\nOut of attempts! The number was " + target + ".");
                return -1;
            }

            int guess = getIntInput("Enter your guess: ");
            attemptsUsed++;

            if (guess < low || guess > high) {
                System.out.println("Please guess a number within " + low + "-" + high + ".");
                continue;
            }

            if (guess == target) {
                System.out.println("Correct! You guessed it in " + attemptsUsed + " attempt(s).");
                return attemptsUsed;
            } else if (guess < target) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }

            if (maxAttempts > 0) {
                int remaining = maxAttempts - attemptsUsed;
                if (remaining > 0) {
                    System.out.println("Attempts remaining: " + remaining);
                }
            }
        }
    }

    /**
     * Scores a round: fewer attempts = higher score.
     * Base score of 100, minus 10 per attempt, minimum of 10.
     * If the round was lost (attemptsUsed == -1), score is 0.
     */
    private static int calculateRoundScore(int attemptsUsed) {
        if (attemptsUsed == -1) {
            return 0;
        }
        int score = 100 - (attemptsUsed - 1) * 10;
        return Math.max(score, 10);
    }

    /**
     * Keeps asking until the user enters a valid integer.
     */
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
