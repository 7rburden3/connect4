package connect4;

import javax.swing.JOptionPane;

public class Board {

	public void printBoard(String boardArray[][]){

		String output = "";
		final int ARRAY_BOARD_HEIGHT = 6;
		final int ARRAY_BOARD_LENGTH = 7;

		for (int loop1=0; loop1<ARRAY_BOARD_HEIGHT; loop1++) {
			for (int loop2=0; loop2<ARRAY_BOARD_LENGTH; loop2++){				
				output = output + boardArray[loop1][loop2];
			}//end inner for
			output = output + "\n";
		}//end outer for 
		
		JOptionPane.showMessageDialog(null, output,"Connect 4",JOptionPane.INFORMATION_MESSAGE);
	}//end printBoard

}//end class
