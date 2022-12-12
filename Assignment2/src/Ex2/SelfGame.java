package Ex2;

public class SelfGame extends Game {
	
	private boolean waitForTurn = false;
	private int counter = 0;
	private int whoWon;
	
	public SelfGame() {

	}
	
	public synchronized void checkTurn(SelfPlayer p) {
		if (this.counter == 0) {
			if (p.getTurn() == true) { // Player O waits, X starts
				while (this.waitForTurn) {
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}
					this.waitForTurn = true;
					notify();
			}
		} else {
			while (this.waitForTurn) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
				this.waitForTurn = true;
				notify();
		}
	}

	public synchronized boolean isEmpty(int a, int b) {		
		if (this.getBoard()[a][b].equals("  ")) {
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized void setXO(int a, int b, SelfPlayer p) {
		this.counter++;
		if (p.getTurn() == true) {
			this.getBoard()[a][b] = " O";
		} else {
			this.getBoard()[a][b] = " X";
		}
	}
	
	public synchronized boolean isGameOver() {
		int count = 0;
		if (this.counter == 9) {
			return true;
		} else if (counter >= 3) {
			String temp = "";
			for (int i = 0; i < 3; i++) {
				temp = this.getBoard()[i][0];
				for (int j = 0; j < 3; j++) {
					if (temp.equals(this.getBoard()[i][j])) {
						count++;
					}
				}
				if (count == 3) {
					if (temp.equals(" O")) {
						this.whoWon = 1;
						return true;
					} else if (temp.equals(" X")) {
						this.whoWon = 2;
						return true;
					} else if (temp.equals("  ")) {
						count = 0;
					}
				} else {
					count = 0;
				}
			}
			for (int i = 0; i < 3; i++) {
				temp = this.getBoard()[0][i];
				for (int j = 0; j < 3; j++) {
					if (temp.equals(this.getBoard()[j][i])) {
						count++;
					}
				}
				if (count == 3) {
					if (temp.equals(" O")) {
						this.whoWon = 1;
						return true;
					} else if (temp.equals(" X")) {
						this.whoWon = 2;
						return true;
					} else if (temp.equals("  ")) {
						count = 0;
					}
				} else {
					count = 0;
				}
			}
			temp = this.getBoard()[0][0];
			for (int i = 0; i < 3; i++) {
				if (temp.equals(this.getBoard()[i][i])) {
					count++;
				}
				if (count == 3) {
					if (temp.equals(" O")) {
						this.whoWon = 1;
						return true;
					} else if (temp.equals(" X")) {
						this.whoWon = 2;
						return true;
					} else if (temp.equals("  ")) {
						count = 0;
					}
				}
			}
			int indexI = 0, indexJ = 2;
			temp = this.getBoard()[0][2];
			for (int i = 0; i < 3; i++) {	
				if (temp.equals(this.getBoard()[indexI][indexJ])) {
					count++;
				}
				indexJ--;
				indexI++;
				if (count == 3) {
					if (temp.equals(" O")) {
						this.whoWon = 1;
						return true;
					} else if (temp.equals(" X")) {
						this.whoWon = 2;
						return true;
					} else if (temp.equals("  ")) {
						count = 0;
					}
				}
			}
			return false;
		} else {
			return false;
		}
	}
	
	public void declareWinner() {
		if (this.getWhoWon() == 1) {
			System.out.println("p1 has won!");
		} else if (this.getWhoWon() == 2) {
			System.out.println("p2 has won!");
		} else {
			System.out.println("It's a tie.");
		}
	}
	
	public synchronized int getWhoWon() {
		return this.whoWon;
	}
}
