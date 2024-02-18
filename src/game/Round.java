package game;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Round {
	private String word;
	private StringBuilder currentState;
	private int wrongGuessesLeft = 7;
	private final Set<Character> correctGuesses = new HashSet<>();
	private final Set<Character> wrongGuesses = new HashSet<>();
	public boolean isOver = false;
	public final Scanner scanner = new Scanner(System.in);

	void start() {
		List<String> options = WordListCreator.getWords();
		word = options.get((int) ((Math.random()) * options.size()));
		currentState = new StringBuilder(word);
		showCurrentState();
		while (gameOver() == 0) {
			var guess = askGuess();
			if (isValidGuess(guess)) {
				guessLetter(String.valueOf(guess.charAt(0)));
			}
		}
		isOver = true;
	}

	private String askGuess() {
		System.out.print("Guess: ");
		return scanner.next().toLowerCase();
	}

	private int gameOver() {
		if (currentState.toString().equals(word)) {
			System.out.println("A WINNER IS YOU!");
			return 1;
		} else if (wrongGuessesLeft == 0) {
			System.out.println("OH NO, YOU LOSE!");
			System.out.println("The word was: " + word);
			return -1;
		} else {
			return 0;
		}
	}

	private void showCurrentState() {
		currentState.replace(0, word.length(), "_".repeat(word.length()));
		for (int i = 0; i < word.length(); i++) {
			if (correctGuesses.contains(word.charAt(i))) {
				currentState.replace(i, i + 1, String.valueOf(word.charAt(i)));
			}
		}
		System.out.println(currentState);
	}

	private void guessLetter(String letter) {
		if (word.contains(letter)) {
			correctGuesses.add(letter.charAt(0));
			showCurrentState();
		} else {
			wrongGuessesLeft--;
			System.out.println("Oops, wrong letter! Only " + wrongGuessesLeft + " guesses left.");
			wrongGuesses.add(letter.charAt(0));
			showCurrentState();
		}
	}

	private boolean isValidGuess(String guess) {
		if (guess.isBlank()) {
			System.out.println("Guess can't be blank.");
			return false;
		} else if (guess.length() > 1) {
			System.out.println("Guess can't be longer than 1 character.");
			return false;
		}
		var letter = guess.charAt(0);
		if (wrongGuesses.contains(letter) || correctGuesses.contains(letter)) {
			System.out.println("This letter has already been guessed");
			return false;
		} else {
			return true;
		}
	}
}
