package Ex2;

public abstract class Player extends Thread {

	private boolean turn; // X / O
	private String str;
	
	public Player(boolean turn, String str) { // true = O, false = X
		this.turn = turn;
		this.str = str;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
	
	public String getStr() {
		return this.str;
	}
	
}
