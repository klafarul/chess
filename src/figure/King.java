package figure;

import board.*;

public class King extends Figure{
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public King(boolean color){
		this.name = "King";
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
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		while(!flag && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty() == false){
					if (chessBoard.getChessBoard()[i][j].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[i][j];					
						flag = true;
					}
				}
				else{
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

		
		return flag;
	
	}
}