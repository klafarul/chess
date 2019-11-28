package figure;

import board.*;

public class King extends Figure{
	
	private Cell cellCanBeMoved;
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
		Cell cellCanBeEaten = getCellCanBeEaten(chessBoard, currentCell);
		Cell cellCanBeMoved = getCellCanBeMoved(chessBoard, currentCell);
		if (cellCanBeEaten != null){
			System.out.println("Popalsya razbojnik");
			if (cellCanBeEaten.getFigure().getName() == "King"){
				if (cellCanBeEaten.getFigure().getColor()){
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
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		while((cellCanBeEaten == null) && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty() == false){
					if (chessBoard.getChessBoard()[i][j].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[i][j];	
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

		
		return cellCanBeEaten;
	
	}
}