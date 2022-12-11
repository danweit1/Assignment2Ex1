package Ex2;

import java.util.Random;

public class SelfPlayer extends Player implements Runnable {

	private SelfGame game;
	
	public SelfPlayer(boolean turn, String str) {
		super(turn, str);
		this.game = new SelfGame();
	}
	
	public void run() {
		this.game.printBoard();
		if (this.game.isGameOver()) {
			if (this.game.getWhoWon() == 1) {
				System.out.println("p1 has won!");
			} else if (this.game.getWhoWon() == 2) {
				System.out.println("p2 has won!");
			} else {
				System.out.println("It's a tie.");
			}
		} else {
			Random rand = new Random();
			int randNum = rand.nextInt(3);
			int randNum2 = rand.nextInt(3);
			while (!this.game.isEmpty(randNum, randNum2)) {
				randNum = rand.nextInt(3);
				randNum2 = rand.nextInt(3);
			}
			this.game.setXO(randNum, randNum2, this.getTurn());
		}
	}
}
