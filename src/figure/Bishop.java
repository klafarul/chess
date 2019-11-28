package figure;

import board.*;

public class Bishop extends Figure{
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Bishop(boolean color){
		this.name = "Bishop";
		this.color = color;
	}
	
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
	*проверка: может ли Bishop сделать ход?
	**/
	@Override
	public boolean canMove(Board chessBoard, Cell currentCell){
		if (canEat(chessBoard, currentCell)){return true;}
		
		int i = 1;
		boolean flag = false;
		
	
		
		while (i >= -1){
			
			try{
				
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i];
					flag = true;
				}
				
			}
			catch(ArrayIndexOutOfBoundsException ex){
			}
			try{
				
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].isEmpty()){
						cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i];					
						flag = true;
					}			
			}
			catch(ArrayIndexOutOfBoundsException ex){			
			}
			i = i -2;	
		}
		
		return flag;
		
		
	}
	/**
	* проверка может ли Bishop съесть фигуру
	**/
	@Override
	public boolean canEat(Board chessBoard, Cell currentCell){
		boolean flag = false;
		
		while (!flag){
			flag = findEnemyMainDiagonal(chessBoard, currentCell, 1);
			flag = findEnemyMainDiagonal(chessBoard, currentCell, -1);
			flag = findEnemySideDiagonal(chessBoard, currentCell, -1);
			flag = findEnemySideDiagonal(chessBoard, currentCell, 1);
			break;
		}
		
		if (cellCanBeEaten != null){
			flag = true;
		}
		return flag;
		
	}
	/**
	* поиск фигуры соперника по главной диагонали относительно текущей фигуры;
	*if i = 1 ищет вражеские фигуры вправо вниз, i = -1, ищет вражеские фигуры влево вверх 
	**/
	protected boolean findEnemyMainDiagonal(Board chessBoard, Cell currentCell, int i){
		boolean flag = false;
		boolean end = false;
		
		while (!flag && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY() + i];
						flag = true;
					
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
		if (cellCanBeEaten != null){
			
		}
		return flag;
	}
	/**
	* поиск фигуру соперника побочной диагонали относительно текущей фигуры;
	* if i = 1 ищет вражеские фигуры справа вверх, i = -1, ищет вражеские фигуры слева вниз 
	**/	
	protected boolean findEnemySideDiagonal(Board chessBoard, Cell currentCell, int i){
		boolean flag = false;
		boolean end = false;
		
		while (!flag && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - i][currentCell.getPosY() + i];
						flag = true;
						
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
		if (cellCanBeEaten != null){
			
		}
		return flag;
		
	}	
}