package figure;

import board.*;

public class Knight extends Figure{
	
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Knight(boolean color){
		this.name = "Knight";
		this.color = color;
	}
	
	/**
	*сделать ход, если canMove = true;
	**/
	@Override
	public void move(Board chessBoard, Cell currentCell){
		if (canEat(chessBoard, currentCell)){
			System.out.println("Popalsya razbojnik");
			if (cellCanBeEaten.getFigure().getName() == "King"){
				if (cellCanBeEaten.getFigure().getColor()){
					chessBoard.kingsLives.removeWhiteKing();
				}
				else{
					chessBoard.kingsLives.removeBlackKing();
				}
			}
			chessBoard.getChessBoard()[cellCanBeEaten.getPosX()][cellCanBeEaten.getPosY()].setFigure(currentCell.getFigure());
			currentCell.setFigure(null);			
		}
		else{
			chessBoard.getChessBoard()[cellCanBeMoved.getPosX()][cellCanBeMoved.getPosY()].setFigure(currentCell.getFigure());
			currentCell.setFigure(null);
		}
		cellCanBeEaten = null;
		cellCanBeMoved = null;	
	}
	
	/**
	*проверка: может ли Knight сделать ход?
	**/
	@Override
	public boolean canMove(Board chessBoard, Cell currentCell){
		boolean flag = false;
		if (canEat(chessBoard, currentCell)){
			return true;
		}
		if (cellCanBeMoved != null){
			flag = true;
		}
		return flag;
	}
	
	/**
	* проверка может ли Knight съесть фигуру
	**/
	@Override
	public boolean canEat(Board chessBoard, Cell currentCell){
		boolean flag = false;
		int i = 2, j = 1, countChecks = 0; 
		
		while (!flag && (countChecks < 4)){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j];
						flag = true;					
					}					
				}
				else{
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() - j];
				}
				if (chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - j][currentCell.getPosY() - i];
						flag = true;					
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
		return flag;
	}
	
}