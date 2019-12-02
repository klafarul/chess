package figure;

import board.*;


public class Knight extends Figure{
	
	
	public Knight(Color color){
		this.name = "Knight";
		this.color = color;
	}
	
	
	
	/**
	*проверка: может ли Knight сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		Cell cellCanBeMoved = null;
		
		boolean flag = false;
		int i = 2, j = 1, countChecks = 0; 
		
		while ((cellCanBeMoved == null) && (countChecks < 4)){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j];										
				}
				
				if (chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].isEmpty()){					
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i];					
				}
				
				countChecks++; 
				if ((countChecks % 2) == 0){
					i *= -1;
					j *= -1;
				}
				else{
					j *= -1;
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
				countChecks++;
				if ((countChecks % 2) == 0){
					i *= -1;
					j *= -1;
				}
				else{
					j *= -1;
				}
			}			
		}
		return cellCanBeMoved;
	}
	
	/**
	* проверка может ли Knight съесть фигуру
	**/
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = null;
		boolean flag = false;
		int i = 2, j = 1, countChecks = 0; 
		
		while ((cellCanBeEaten == null) && (countChecks < 4)){
			try{
				if (!chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].isEmpty()){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j];
					}					
				}	
				
				if (!chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].isEmpty()){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i];
					}
				}

				countChecks++; 
				if ((countChecks % 2) == 0){
					i *= -1;
					j *= -1;
				}
				else{
					j *= -1;
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
				countChecks++;
				if ((countChecks % 2) == 0){
					i *= -1;
					j *= -1;
				}
				else{
					j *= -1;
				}
			}			
		}
		return cellCanBeEaten;
	}	
}