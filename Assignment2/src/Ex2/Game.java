package Ex2;

public abstract class Game {

	private String[][] gameBoard;
	private boolean turn;
	private int counter = 0;
	private int whoWon;
	
	public Game() {
		this.initializeBoard();
	}
	
	public void initializeBoard() {
		this.gameBoard = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.gameBoard[i][j] = "  ";
			}
		}
	}
	
	public void printBoard() {
	    System.out.println("   1    2   3");
	    System.out.println("A  " + gameBoard[0][0] + "| " + gameBoard[0][1] + " | " + gameBoard[0][2]);  
	    System.out.println("  ------------");
	    System.out.println("B  " + gameBoard[1][0] + "| " + gameBoard[1][1] + " | " + gameBoard[1][2]);  
	    System.out.println("  ------------");
	    System.out.println("C  " + gameBoard[2][0] + "| " + gameBoard[2][1] + " | " + gameBoard[2][2]); 
	}
	
	public void getFreeCells() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.gameBoard[i][j].equals("  ")) {
					if (i == 0) { // row A
						System.out.print("A, " + (j + 1)+ "|");
					} else if(i == 1) { // row B
						System.out.print("B, " + (j + 1)+ "|");
					} else { // row C
						System.out.print("C, " + (j + 1)+ "|");
					}
				}
			}
		}
	}

	public boolean isEmpty(int a, int b) {
		if (this.gameBoard[a][b].equals("  ")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setXO(int a, int b, boolean turn) {
		this.counter++;
		if (turn == true) {
			this.gameBoard[a][b] = " O";
		} else {
			this.gameBoard[a][b] = " X";
		}
	}
	
	public boolean isGameOver() {
		int count = 0;
		if (this.counter == 9) {
			return true;
		} else if (counter >= 3) {
			String temp = "";
			for (int i = 0; i < 3; i++) {
				temp = this.gameBoard[i][0];
				for (int j = 0; j < 3; j++) {
					if (temp.equals(this.gameBoard[i][j])) {
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
				temp = this.gameBoard[0][i];
				for (int j = 0; j < 3; j++) {
					if (temp.equals(this.gameBoard[j][i])) {
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
			temp = this.gameBoard[0][0];
			for (int i = 0; i < 3; i++) {
				if (temp.equals(this.gameBoard[i][i])) {
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
			temp = this.gameBoard[0][2];
			for (int i = 0; i < 3; i++) {	
				if (temp.equals(this.gameBoard[indexI][indexJ])) {
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
	
	public int getWhoWon() {
		return this.whoWon;
	}
	
	public void setPlayerType(boolean turn) { // true = O, false = X
		this.turn = turn;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
}


