package figure;

import board.*;
import java.util.ArrayList;
import java.util.Random;


public class Bishop extends Figure{
	
	public Bishop(Color color){
		this.name = "Bishop";
		this.color = color;
	}
	
	
	/**
	*проверка: может ли Bishop сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		Cell cellCanBeMoved = null;
		
		
		int i = 1;
		while (i >= -1){
			
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i];
				}
			}
			catch(ArrayIndexOutOfBoundsException ex){
			}
			
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].isEmpty()){
						cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i];	
					}			
			}
			catch(ArrayIndexOutOfBoundsException ex){			
			}
			i = i -2;	
		}
		
		return cellCanBeMoved;
		
		
	}
	/**
	* проверка может ли Bishop съесть фигуру
	**/
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		
		Cell cellCanBeEaten = null;
		
		
		int counter = 0;
		int j = 1;
				

		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellMainDiagonal(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}	
		counter = 0;
		j = 1;
		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellSideDiagonal(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}
		
		
		return cellCanBeEaten;
		
	}
}