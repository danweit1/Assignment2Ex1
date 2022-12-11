package Ex2;

public abstract class Player extends Thread {

	private boolean turn; // X / O
	private boolean isMyTurn;
	private String str;
	
	public Player(boolean turn, String str) { // true = O, false = X
		this.turn = turn;
		if (this.turn == false) {
			this.isMyTurn = true;
		} else {
			this.isMyTurn = false;
		}
		this.str = str;
	}
	
	public void setMyTurn(boolean myTurn) {
		this.isMyTurn = myTurn;
	}
	
	public boolean getMyTurn() {
		return this.isMyTurn;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
	
	public String getStr() {
		return this.str;
	}
	
}
