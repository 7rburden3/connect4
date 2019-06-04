package connect4;

public class Connect4Board {
	
	public void createBoard(String boardArray[][]) {
		final int ARRAY_BOARD_HEIGHT = 6;
		final int ARRAY_BOARD_LENGTH = 7;
		
		for (int loop1=0; loop1<ARRAY_BOARD_HEIGHT; loop1++) {
			for (int loop2=0; loop2<ARRAY_BOARD_LENGTH; loop2++){
				
				boardArray[loop1][loop2] = "  [\u0000]  ";
			}//end for loop2
		}//end for loop1
		
		
		return;
	}//end createBoard
	
}//end Connect4Board