package Ex2;

public class Main {

	public static void main(String[] args) {
		
		SelfPlayer p1 = new SelfPlayer(true, "p1"); // plays O
		SelfPlayer p2 = new SelfPlayer(false, "p2"); // plays X
		
		SelfGame game = new SelfGame();
		
		game.printBoard();
		
		p1.start();
		p2.start();
		
		//game.gameStart();
		
		
	}

}
