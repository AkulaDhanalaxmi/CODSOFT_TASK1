# Number Game

A console-based number guessing game where the computer picks a random number within a range you choose, and you try to guess it. After each guess, the game tells you whether your guess was too high, too low, or correct.

Available in both **Python** and **Java**.

## Features

- Customizable number range for each round
- Optional limit on the number of guessing attempts
- Multiple rounds with the option to keep playing
- Scoring system based on how many attempts you used per round, plus a running total score and win count across rounds

## How to Play

1. Enter a lower and upper bound for the range.
2. Choose whether to limit the number of attempts.
3. Guess the number — you'll be told if your guess is too high, too low, or correct.
4. Keep guessing until you run out of attempts or guess correctly.
5. Choose to play another round or end the game and see your final score.

## Running the Python Version

```
python number_game.py
```

## Running the Java Version

```
javac NumberGame.java
java NumberGame
```

## Scoring

Each round starts at a base score of 100, minus 10 points for every extra attempt used, with a minimum score of 10 for a win. A round that runs out of attempts scores 0.
