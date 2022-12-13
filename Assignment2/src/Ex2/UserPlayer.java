package Ex2;

public class UserPlayer extends Player implements Runnable {

	private UserGame game;
	
	public UserPlayer(boolean turn, String str, UserGame game) {
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
