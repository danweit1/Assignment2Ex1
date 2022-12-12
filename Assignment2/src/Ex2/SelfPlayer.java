package Ex2;

import java.util.Random;

public class SelfPlayer extends Player implements Runnable {

	private SelfGame game;
	private int counter = 0;
	
	public SelfPlayer(boolean turn, String str, SelfGame game) {
		super(turn, str);
		this.game = game;
	}
	
	public void run() {
		while (!this.game.isGameOver()) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			if (this.counter == 0) {
				this.game.checkTurn(this);
				counter++;
			}
			Random rand = new Random();
			int randNum = rand.nextInt(3);
			int randNum2 = rand.nextInt(3);
			while (!this.game.isEmpty(randNum, randNum2)) {
				randNum = rand.nextInt(3);
				randNum2 = rand.nextInt(3);
			}
			this.game.setXO(randNum, randNum2, this);
			this.game.printBoard();
		}
	}
}
