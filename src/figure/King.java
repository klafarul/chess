package figure;

import board.*;


public class King extends Figure{
	
	
	public King(Color color){
		this.name = "King";
		this.color = color;
	}
	/**
	*проверка: может ли King сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		
		Cell cellCanBeMoved = null;
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		
		while((cellCanBeMoved == null) && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty() == true){					
					cellCanBeMoved = chessBoard.getChessBoard()[i][j];						
				}				
				countChecks++;
				if ((countChecks % 3) == 0){
					i++;
					j -= 2;
				}
				else{
					j++;
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
				countChecks++;
				if ((countChecks % 3) == 0){
					i++;
					j -= 2;
				}
				else{
					j++;
				}
				
			}
		}
		return cellCanBeMoved;
	}
	/**
	* проверка может ли King съесть фигуру
	**/
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = null;
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		while((cellCanBeEaten == null) && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty() == false){
					if (chessBoard.getChessBoard()[i][j].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[i][j];	
					}
				}				
				countChecks++;
				if ((countChecks % 3) == 0){
					i++;
					j -= 2;
				}
				else{
					j++;
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
				countChecks++;
				if ((countChecks % 3) == 0){
					i++;
					j -= 2;
				}
				else{
					j++;
				}
				
			}
		}
		return cellCanBeEaten;
	}	
}