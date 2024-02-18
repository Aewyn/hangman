package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private final List<Round> rounds = new ArrayList<>();
	boolean playAgain = true;

	Scanner scanner = new Scanner(System.in);
	public void start(){
		while (playAgain){
			var round = new Round();
			round.start();
			if(round.isOver){
				showStatus();
				rounds.add(round);
			}
			System.out.print("Play again? true/false: ");
			playAgain = scanner.nextBoolean();
		}
	}

	void showStatus(){
		//TODO: Show overview of score over all rounds.
	}
}
