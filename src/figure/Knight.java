package figure;

import board.*;
import java.awt.Color;

public class Knight extends Figure{
	Cell cellCanBeMoved;
	
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Knight(Color color){
		this.name = "Knight";
		this.color = color;
	}
	
	
	/**
	*сделать ход, если canMove = true;
	**/
	@Override
	public void move(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = getCellCanBeEaten(chessBoard, currentCell);
		Cell cellCanBeMoved = getCellCanBeMoved(chessBoard, currentCell);
		if (cellCanBeEaten != null){
			System.out.println("Popalsya razbojnik");
			if (cellCanBeEaten.getFigure().getName() == "King"){
				if (cellCanBeEaten.getFigure().getColor() == Color.WHITE){
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
	*проверка: может ли Knight сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		
		if (getCellCanBeEaten(chessBoard, currentCell) != null){
			return getCellCanBeEaten(chessBoard, currentCell);
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
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j];
					}					
				}
				else{
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j];
				}
				if (chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].getFigure().getColor() != this.color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i];
					}
				}
				else{
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
		return cellCanBeEaten;
	}
	
}