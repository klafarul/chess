package figure;

import board.Board;
import board.Cell;

/**
*абстрактный класс, который определяет поведение шахмотной фигуры
**/



public abstract class Figure {
	
	protected String name;
	// true - white; false - black;
	protected Color color;
	
	//true - ход сделан, false - ход не сделан
	public boolean move(Board chessBoard, Cell currentCell){
		
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
			return true;
		}
		if (cellCanBeMoved != null){
			chessBoard.getChessBoard()[cellCanBeMoved.getPosX()][cellCanBeMoved.getPosY()].setFigure(currentCell.getFigure());
			currentCell.setFigure(null);
			return true;
		}
		
		return false;
	}
	public abstract Cell getCellCanBeEaten(Board chessBoard, Cell currentCell);	
	public abstract Cell getCellCanBeMoved(Board chessBoard, Cell currentCell);
	
	
	/**
	* поиск фигуру соперника по вертикали;
	* if i = 1 ищет вражеские фигуры сверху, i = -1, ищет вражеские фигуры снизу 
	**/
	public Cell findEnemyCellVertical(Board chessBoard, Cell currentCell, int i){
		boolean end = false;
		Cell cellCanBeEaten = null;
		
		while ((cellCanBeEaten == null) && !end){
			try{
				if (!chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].isEmpty()){
					if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].getFigure().getColor() != this.color){
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
	
	/**
	* поиск фигуру соперника по горизонтали;
	*if i = 1 ищет вражеские фигуры справа, i = -1, ищет вражеские фигуры слева 
	**/
	public Cell findEnemyCellHorizontal(Board chessBoard, Cell currentCell, int i){
		boolean end = false;
		Cell cellCanBeEaten = null;
		
		while ((cellCanBeEaten == null) && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].getFigure().getColor() != this.color){
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
	* поиск фигуры соперника по главной диагонали относительно текущей фигуры;
	*if i = 1 ищет вражеские фигуры вправо вниз, i = -1, ищет вражеские фигуры влево вверх 
	**/
	public Cell findEnemyCellMainDiagonal(Board chessBoard, Cell currentCell, int i){
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
	public Cell findEnemyCellSideDiagonal(Board chessBoard, Cell currentCell, int i){
		
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
	
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public Color getColor(){
		return color;
	}
	public void setColor(Color color){
		this.color = color;
	}
}