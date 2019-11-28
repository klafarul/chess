package figure;

import board.*;
import java.awt.Color;

public class Bishop extends Figure{
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Bishop(Color color){
		this.name = "Bishop";
		this.color = color;
	}
	
	@Override
	public void move(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = getCellCanBeEaten(chessBoard, currentCell);
		Cell cellCanBeMoved = getCellCanBeMoved(chessBoard, currentCell);
		if (cellCanBeEaten != null){
			System.out.println("Popalsya razbojnik");
			if (cellCanBeEaten.getFigure().getName() == "King"){
				if (cellCanBeEaten.getFigure().getColor() == Color.WHITE ){
					chessBoard.getLives().removeWhiteKing();
				}
				else{
					chessBoard.getLives().removeBlackKing();
				}
			}
			chessBoard.getChessBoard()[cellCanBeEaten.getPosX()][cellCanBeEaten.getPosY()].setFigure(currentCell.getFigure());
			currentCell.setFigure(null);	
		}
		else{
			if (cellCanBeMoved != null){
				chessBoard.getChessBoard()[cellCanBeMoved.getPosX()][cellCanBeMoved.getPosY()].setFigure(currentCell.getFigure());
				currentCell.setFigure(null);
			}
		}
	}
	/**
	*проверка: может ли Bishop сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		Cell cellCanBeMoved = null;
		if (getCellCanBeEaten(chessBoard, currentCell) != null){
			return getCellCanBeEaten(chessBoard, currentCell);
		}
		
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
		
		cellCanBeEaten = findEnemyCellMainDiagonal(chessBoard, currentCell, 1);
		cellCanBeEaten = findEnemyCellMainDiagonal(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellSideDiagonal(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellSideDiagonal(chessBoard, currentCell, 1);
		
		return cellCanBeEaten;
		
	}
	/**
	* поиск фигуры соперника по главной диагонали относительно текущей фигуры;
	*if i = 1 ищет вражеские фигуры вправо вниз, i = -1, ищет вражеские фигуры влево вверх 
	**/
	protected Cell findEnemyCellMainDiagonal(Board chessBoard, Cell currentCell, int i){
		Cell cellCanBeEaten = null;
		
		boolean end = false;
		
		while ((cellCanBeEaten == null) && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i];
					}
					else{
						end = true;
					}
				}
				i = (i > 0)? (++i):(--i);
			}
			catch(ArrayIndexOutOfBoundsException ex){
				end = true;
					
			}
		}

		return cellCanBeEaten;
	}
	/**
	* поиск фигуру соперника побочной диагонали относительно текущей фигуры;
	* if i = 1 ищет вражеские фигуры справа вверх, i = -1, ищет вражеские фигуры слева вниз 
	**/	
	protected Cell findEnemyCellSideDiagonal(Board chessBoard, Cell currentCell, int i){
		
		Cell cellCanBeEaten = null;
		boolean end = false;
		
		while ((cellCanBeEaten == null) && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i];
					}
					else{
						end = true;
					}
				}
				i = (i > 0)? (++i):(--i);
				
			}
			catch(ArrayIndexOutOfBoundsException ex){
				end = true;
			}
		}

		return cellCanBeEaten;
		
	}	
}