package figure;

import board.*;


public class Rook extends Figure{
	
	public Rook(Color color){
		this.name = "Rook";
		this.color = color;
		
	}
	
	/**
	*проверка: может ли Rook сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		
		Cell cellCanBeMoved = null;
		int i = 1;
	
		while (i >= -1){
			
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()];
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
			}
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].isEmpty()){
						cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i];
					}			
			}
			catch(ArrayIndexOutOfBoundsException ex){			
			}
			i = i -2;	
		}
		
		return cellCanBeMoved;
	}
	/**
	* проверка может ли Rook съесть фигуру
	**/
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = null;
		
		
		int counter = 0;
		int j = 1;
				

		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellHorizontal(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}	
		counter = 0;
		j = 1;
		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellVertical(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}
		return cellCanBeEaten;
	}
}