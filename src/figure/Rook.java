package figure;

import board.*;

public class Rook extends Figure{
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Rook(boolean color){
		this.name = "Rook";
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
	*проверка: может ли Rook сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		if (getCellCanBeEaten(chessBoard, currentCell) != null){
			return getCellCanBeEaten(chessBoard, currentCell);
		}
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
		
		
		cellCanBeEaten = findEnemyCellHorizontal(chessBoard, currentCell, 1);
		cellCanBeEaten = findEnemyCellHorizontal(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellVertical(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellVertical(chessBoard, currentCell, 1);
	
		
		return cellCanBeEaten;
	}
	/**
	* поиск фигуру соперника по горизонтали;
	*if i = 1 ищет вражеские фигуры справа, i = -1, ищет вражеские фигуры слева 
	**/
	protected Cell findEnemyCellHorizontal(Board chessBoard, Cell currentCell, int i){
		boolean end = false;
		Cell cellCanBeEaten = null;
		
		while ((cellCanBeEaten == null) && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i];
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
	* поиск фигуру соперника по вертикали;
	* if i = 1 ищет вражеские фигуры сверху, i = -1, ищет вражеские фигуры снизу 
	**/
	protected Cell findEnemyCellVertical(Board chessBoard, Cell currentCell, int i){
		boolean end = false;
		Cell cellCanBeEaten = null;
		
		while ((cellCanBeEaten == null) && !end){
			try{
				if (!chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].isEmpty()){
					if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()];
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