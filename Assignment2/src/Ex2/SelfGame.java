package Ex2;

public class SelfGame extends Game {
	
	private SelfPlayer p1, p2;
	
	public SelfGame() {
		p1 = new SelfPlayer(true, "p1"); // plays O
		p2 = new SelfPlayer(false, "p2"); // plays X
	}
	
	public void gameStart() {
		this.p1.start();
		this.p2.start();
	}

}
