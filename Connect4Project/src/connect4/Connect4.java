package connect4;

import javax.swing.JOptionPane;

public class Connect4 {

	public static void main(String[] args) {

		//declare variables
		String usersNumber;
		int num;

		//create Do While loop for Play Again
		do{
			//create boardArray
			final int ARRAY_BOARD_HEIGHT = 6;
			final int ARRAY_BOARD_LENGTH = 7;
			String boardArray[][] = new String [ARRAY_BOARD_HEIGHT][ARRAY_BOARD_LENGTH];

			//create Play Again message
			String message = "1 - Play Again? \n 2 - Exit";

			//initial start of game sets condition that variable playerWin is false
			boolean playerWin = false;

			//call Connect4Board class to create board
			Connect4Board Connect4BoardObject = new Connect4Board();
			//create empty board
			Connect4BoardObject.createBoard(boardArray);

			//call Board class to print Board
			Board BoardObject = new Board();

			//while loop to allow game to play until win or draw conditions are met
			while (playerWin == false){
				BoardObject.printBoard(boardArray);

				// Red player uses playerTurn method to take their turn		
				playerTurn("  R  ", boardArray);

				//if else statement that creates conditions for red player to win
				if (aWin(boardArray)) {
					playerWin = true;
					JOptionPane.showMessageDialog(null, "The red player won", "", JOptionPane.INFORMATION_MESSAGE);
				}

				//game is not finished so yellow player takes turn
				if (playerWin == false) {				
					BoardObject.printBoard(boardArray);

					// Yellow player uses playerTurn method to take their turn            
					playerTurn("  Y  ", boardArray);

					//if else statement that creates conditions for yellow player to win
					//or to create a draw
					if (aWin(boardArray)) {
						playerWin = true;
						JOptionPane.showMessageDialog(null, "The yellow player won", "", JOptionPane.INFORMATION_MESSAGE);
					} else if (aDraw(boardArray)) {
						playerWin = true;
						JOptionPane.showMessageDialog(null, "The game was a draw", "", JOptionPane.INFORMATION_MESSAGE);
					}//end if aDraw 

				}//end if playerWin

			}//end while

			//create message to allow user to play another game
			usersNumber = JOptionPane.showInputDialog(message);
			num = Integer.parseInt(usersNumber);

		}while(num!=2);

	}//end main

	//method to allow player to take their turn and choose a column to drop their counter in
	public static void playerTurn(String player, String boardArray[][]) {

		//declare variables
		boolean done = false;
		int column;
		String columnChoice;

		//call class Counter 
		Counter CounterObject = new Counter();

		do {
			if(player=="  R  ") {
				columnChoice = "red";
			}else{
				columnChoice = "yellow";
			}
			columnChoice = JOptionPane.showInputDialog("Place a " + columnChoice + " counter in a column between 0 and 6");  

			column = Integer.parseInt(columnChoice);

			while (column < 0 || column > 6) {
				if(player == "  R  "){
					columnChoice = "red";
				}else{
					columnChoice = "yellow";
				}
				columnChoice = JOptionPane.showInputDialog("Invalid column choice, place a " + columnChoice
						+ " counter in a column between 0 and 6");

				column = Integer.parseInt(columnChoice);
			}//end  while                 

			//Class Counter locates the counter in the chosen column at lowest available row
			//If column is full player is prompted to try again in a different column
			if (CounterObject.locateCounter(boardArray, column, player)) {
				done = true;                          

			} else
				JOptionPane.showMessageDialog(null, "This column is full. Please place counter in a different column");
		} while (!done);

	}//end playerTurn

	//create draw method - looks for null spaces on board
	public static boolean aDraw(String boardArray[][]) {		
		for (int loop1=0; loop1 < boardArray.length; loop1++){
			for (int loop2=0; loop2 < boardArray.length; loop2++){
				if (boardArray[loop1][loop2] == "  [\u0000]  "){
					return false;
				}
			}
		}
		return true;
	}//end aDraw

	//create method to return a winning game
	public static boolean aWin(String boardArray[][]) {
		return fourInARow(boardArray);
	}	

	//Method to create checks for fourInARow row, column, diagonals
	public static boolean fourInARow(String[][] fourCount) {
		
		//declare variables		
		int numberOfRows = fourCount.length;
		int numberOfColumns = fourCount[0].length;

		//Call FourCheck class
		FourCheck FourCheckObject = new FourCheck();

		// check rows
		for (int loop = 0; loop < numberOfRows; loop++) {

			if (FourCheckObject.fourInARow(fourCount[loop]))
				return true;
		}//end check rows

		// check columns
		for (int loop1 = 0; loop1 < numberOfColumns; loop1++) {
			String[] column = new String[numberOfRows];
			// Create a column array
			for (int loop2 = 0; loop2 < numberOfRows; loop2++)
				column[loop2] = fourCount[loop2][loop1];

			if (FourCheckObject.fourInARow(column))
				return true;
		}//end check columns
		
		// Check first diagonal (upper left to lower right)
		for (int loop1 = 0; loop1 < numberOfRows - 3; loop1++) {
			int numberOfCountersInDiagonal = Math.min(numberOfRows - loop1, numberOfColumns);
			String[] diagonal = new String[numberOfCountersInDiagonal];
			for (int loop2 = 0; loop2 < numberOfCountersInDiagonal; loop2++)
				diagonal[loop2] = fourCount[loop2 + loop1][loop2];

			if (FourCheckObject.fourInARow(diagonal))
				return true;
		}//end first diagonal

		// Check second diagonal (upper right to lower left)
		for (int loop1 = 1; loop1 < numberOfRows - 3; loop1++) {
			int numberOfCountersInDiagonal = Math.min(numberOfRows - loop1, numberOfColumns);
			String[] diagonal = new String[numberOfCountersInDiagonal];
			for (int loop2 = 0; loop2 < numberOfCountersInDiagonal; loop2++)
				diagonal[loop2] = fourCount[loop2 + loop1][numberOfColumns - loop2 - 1];

			if (FourCheckObject.fourInARow(diagonal))
				return true;
		}//end second diagonal

		// Check third diagonal (lower right to upper left)
		for (int loop1 = 1; loop1 < numberOfColumns - 3; loop1++) {
			int numberOfCountersInDiagonal = Math.min(numberOfColumns - loop1, numberOfRows);
			String[] diagonal = new String[numberOfCountersInDiagonal];
			for (int loop2 = 0; loop2 < numberOfCountersInDiagonal; loop2++)
				diagonal[loop2] = fourCount[loop2][loop2 + loop1];

			if (FourCheckObject.fourInARow(diagonal))
				return true;
		}//end third diagonal

		// Check fourth diagonal (lower left to upper right)
		for (int loop1 = 3; loop1 < numberOfColumns; loop1++) {
			int numberOfCountersInDiagonal = Math.min(loop1 + 1, numberOfRows);
			String[] diagonal = new String[numberOfCountersInDiagonal];
			for (int loop2 = 0; loop2 < numberOfCountersInDiagonal; loop2++)
				diagonal[loop2] = fourCount[loop2][loop1 - loop2];

			if (FourCheckObject.fourInARow(diagonal))
				return true;
		}//end fourth diagonal		

		return false;
	}//end check row, column, diagonals


}//end class
