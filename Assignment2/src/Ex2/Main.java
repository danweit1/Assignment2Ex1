package Ex2;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// SelfGame version: 
		//SelfGame game = new SelfGame();
		//SelfPlayer p1 = new SelfPlayer(true, "p1", game); // plays O
		//SelfPlayer p2 = new SelfPlayer(false, "p2", game); // plays X
		
		//p1.start();
		//p2.start();
		
		//p1.join();
		//p2.join();
		
		// UserGame version:
		UserGame game = new UserGame();
		UserPlayer p1 = new UserPlayer(true, "p1", game); // plays O
		UserPlayer p2 = new UserPlayer(false, "p2", game); // plays X
		
		p1.start();
		p2.start();
		
		p1.join();
		p2.join();
		
		game.declareWinner();

		
	}

}
