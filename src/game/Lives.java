package game;

import java.util.ArrayList;
import figure.*;

public class Lives{
	
	private ArrayList<King> kings;
	
	public Lives(King whiteKing, King blackKing){
		kings = new ArrayList<King>();
		kings.add(whiteKing);
		kings.add(blackKing);		
	}
	
	public boolean isKingsAlive(){
		if ((kings.get(0) != null) && (kings.get(1) != null)){
			return true;
		}
		return false;
	}
	
	public String getWinner(){
		if (!isKingsAlive()){
			if (kings.get(0) != null){
				return "White King has wоn";
			}
			return "Black King has won";
		}
		return "The game hasnt over";
	}
	public void removeWhiteKing(){
		kings.set(0, null);		
	}
	public void removeBlackKing(){
		kings.set(1, null);
	}
	
	
	public King getWhiteKing(){
		return kings.get(0);
	}
	public King getBlackKing(){
		return kings.get(1);
	}
}