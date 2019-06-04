package connect4;

public class Counter {

	public boolean locateCounter(String boardArray[][], int column, String player) {
		final int ARRAY_BOARD_BASE = 5;
		final int ARRAY_BOARD_TOP = 0;
		for (int loop = ARRAY_BOARD_BASE; loop >=ARRAY_BOARD_TOP; loop--) {
			if (boardArray[loop][column] == "  [\u0000]  ") {
				boardArray[loop][column] = player;
				return true; //counter placed on board
			}//end if
		}//end for

		return false; //counter not placed
	}//end locateCounter

}//end class
