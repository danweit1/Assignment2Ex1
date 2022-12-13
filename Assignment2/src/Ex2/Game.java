package Ex2;

public abstract class Game {

	private String[][] gameBoard;
	private boolean turn;

	
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
	
	public String[][] getBoard() {
		return this.gameBoard;
	}
	
	public synchronized void printBoard() {
	    System.out.println("   1    2   3");
	    System.out.println("A  " + gameBoard[0][0] + "| " + gameBoard[0][1] + " | " + gameBoard[0][2]);  
	    System.out.println("  ------------");
	    System.out.println("B  " + gameBoard[1][0] + "| " + gameBoard[1][1] + " | " + gameBoard[1][2]);  
	    System.out.println("  ------------");
	    System.out.println("C  " + gameBoard[2][0] + "| " + gameBoard[2][1] + " | " + gameBoard[2][2]);
	    System.out.println();
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

	public synchronized void setPlayerType(boolean turn) { // true = O, false = X
		this.turn = turn;
	}
	
	public synchronized boolean getTurn() {
		return this.turn;
	}
}


