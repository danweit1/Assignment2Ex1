package Ex2;

public class SelfPlayer extends Player implements Runnable {

	private SelfGame game;
	
	public SelfPlayer(boolean turn, String str, SelfGame game) {
		super(turn, str);
		this.game = game;
	}
	
	public void run() {
		while (!this.game.getIsGameOver()) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			this.game.setXO(this);
		}
	}
}
