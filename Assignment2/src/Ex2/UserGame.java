package Ex2;

import java.util.Random;
import java.util.Scanner;

public class UserGame extends Game {

	private boolean waitForTurn = false;
	private int counter = 0;
	private int whoWon;
	private boolean isGameOver = false;
	private int first = 0;
	private Scanner input;

	public UserGame() {
		input = new Scanner(System.in);
	}

	public synchronized boolean isEmpty(int a, int b) {
		if (this.getBoard()[a][b].equals("  ")) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void setXO(UserPlayer p) {
		if (p.getTurn() == true) { // player is O
			while (!this.waitForTurn) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			if (this.isGameOver == false) {
				Random rand = new Random();
				int randNum = rand.nextInt(3);
				int randNum2 = rand.nextInt(3);
				while (!this.isEmpty(randNum, randNum2)) {
					randNum = rand.nextInt(3);
					randNum2 = rand.nextInt(3);
				}
				this.addCounter();
				this.getBoard()[randNum][randNum2] = " O";
				this.printBoard();
				this.isGameOver = this.isGameOver();
				this.waitForTurn = false;
				notify();
			}
		} else { // player X
			if (first == 0) {
				System.out.println("Let's play Tic-Tac-Toe!\nyou're playing X\n ");
				this.printBoard();
				this.first++;
			}
			while (this.waitForTurn) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			if (this.isGameOver == false) {
				System.out.println("The following are free to choose from: ");
				this.getFreeCells();
				char ch;
				int num1, num2;
				do {
					System.out.println("\nEnter a letter A, B, C: ");
					ch = input.next().charAt(0);
					System.out.println("\nEnter a number 1, 2, 3: ");
					num2 = input.nextInt() - 1;
					num1 = this.turnCharToInt(ch);
				}	
				while (num1 == -1 || !this.isEmpty(num1, num2));
				this.addCounter();
				this.getBoard()[num1][num2] = " X";
				this.printBoard();
				this.isGameOver = this.isGameOver();
				this.waitForTurn = true;
				notify();
			} else {
				this.input.close();
			}
		}

	}

	public int turnCharToInt(char ch) {
		if (ch == 'a' || ch == 'A') {
			return 0;
		} else if (ch == 'b' || ch == 'B') {
			return 1;
		} else if (ch == 'c' || ch == 'C') {
			return 2;
		}
		return -1;
	}
	
	public synchronized boolean isGameOver() {
		int count = 0;
		if (counter >= 3) {
			if (this.getBoard()[0][0].equals(this.getBoard()[1][1])
					&& this.getBoard()[0][0].equals(this.getBoard()[2][2])) {
				if (this.getBoard()[0][0].equals(" O")) {
					this.whoWon = 1;
				} else if (this.getBoard()[0][0].equals(" X")) {
					this.whoWon = 2;
				} else {
					return false;
				}
				return true;
			}

			if (this.getBoard()[0][2].equals(this.getBoard()[1][1])
					&& this.getBoard()[0][2].equals(this.getBoard()[2][0])) {
				if (this.getBoard()[0][2].equals(" O")) {
					this.whoWon = 1;
				} else if (this.getBoard()[0][2].equals(" X")) {
					this.whoWon = 2;
				} else {
					return false;
				}
				return true;
			}
			for (int i = 0; i < 3; i++) {
				String temp = this.getBoard()[i][0];
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
				}
				count = 0;
			}
			for (int i = 0; i < 3; i++) {
				String temp = this.getBoard()[0][i];
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
				}
				count = 0;
			}
		if (this.counter == 9) {
			return true;
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

	public boolean getIsGameOver() {
		return this.isGameOver;
	}

	public synchronized void addCounter() {
		this.counter++;
	}

	public synchronized int getWhoWon() {
		return this.whoWon;
	}
}
