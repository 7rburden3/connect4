package connect4;

public class FourCheck {
	
	//This class tells the Connect4 class what four counters in a row looks like
	
	public boolean fourInARow(String[] boardArray) {
		
		//declare variables
		boolean isFour;
		
		for (int loop1 = 0; loop1 < boardArray.length - 3; loop1++) {
			isFour = true;
			for (int loop2 = loop1; loop2 < loop1 + 3; loop2++) {
				if (boardArray[loop2] == "  [\u0000]  " || boardArray[loop2] != boardArray[loop2 + 1]) {
					isFour = false;					
				}//end if
			}// end for inner

			if (isFour)
				return true;
		}//end for outer

		return false;
	}//end fourInARow

}//end class
