package figure;

import board.*;

public class Queen extends Figure{
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Queen(boolean color){
		this.name = "Queen";
		this.color = color;
	}
	
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
	
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		
		if (getCellCanBeEaten(chessBoard, currentCell) != null){
			return getCellCanBeEaten(chessBoard, currentCell);
		}
		
		Cell cellCanBeMoved = null;
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		
		while ((cellCanBeMoved == null) && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty()){
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
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = null;
		
		
		cellCanBeEaten = findEnemyCellMainDiagonal(chessBoard, currentCell, 1);
		cellCanBeEaten = findEnemyCellHorizontal(chessBoard, currentCell, 1);
		cellCanBeEaten = findEnemyCellMainDiagonal(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellHorizontal(chessBoard, currentCell, -1);			
		cellCanBeEaten = findEnemyCellSideDiagonal(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellVertical(chessBoard, currentCell, -1);
		cellCanBeEaten = findEnemyCellSideDiagonal(chessBoard, currentCell, 1);
		cellCanBeEaten = findEnemyCellVertical(chessBoard, currentCell, 1);

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
					if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].getFigure().getColor() != color){
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
					if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].getFigure().getColor() != color){
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