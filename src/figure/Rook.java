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
	*проверка: может ли Rook сделать ход?
	**/
	@Override
	public boolean canMove(Board chessBoard, Cell currentCell){
		if (canEat(chessBoard, currentCell)){return true;}
		
		int i = 1;
		boolean flag = false;
		
	
		
		while (i >= -1){
			
			try{
				
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()];
					flag = true;
				}
				
			}
			catch(ArrayIndexOutOfBoundsException ex){
			}
			try{
				
				if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].isEmpty()){
						cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i];
						
						flag = true;
					}			
			}
			catch(ArrayIndexOutOfBoundsException ex){			
			}
			i = i -2;	
		}
		if (cellCanBeMoved != null){
			
		}
		return flag;
		
	}
	/**
	* проверка может ли Rook съесть фигуру
	**/
	@Override
	public boolean canEat(Board chessBoard, Cell currentCell){
		boolean flag = false;
		
		while (!flag){
			flag = findEnemyHorizontal(chessBoard, currentCell, 1);
			flag = findEnemyHorizontal(chessBoard, currentCell, -1);
			flag = findEnemyVertical(chessBoard, currentCell, -1);
			flag = findEnemyVertical(chessBoard, currentCell, 1);
		break;
		}
		
		if (cellCanBeEaten != null){
			
			flag = true;
		}
		return flag;
	}
	/**
	* поиск фигуру соперника по горизонтали;
	*if i = 1 ищет вражеские фигуры справа, i = -1, ищет вражеские фигуры слева 
	**/
	protected boolean findEnemyHorizontal(Board chessBoard, Cell currentCell, int i){
		boolean flag = false;
		boolean end = false;
		while (!flag && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX()][currentCell.getPosY() + i];
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
		return flag;
	}
	/**
	* поиск фигуру соперника по вертикали;
	* if i = 1 ищет вражеские фигуры сверху, i = -1, ищет вражеские фигуры снизу 
	**/
	protected boolean findEnemyVertical(Board chessBoard, Cell currentCell, int i){
		boolean flag = false;
		boolean end = false;
		while (!flag && !end){
			try{
				if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].isEmpty() == false){
					if (chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()].getFigure().getColor() != color){
						cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() + i][currentCell.getPosY()];
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
		return flag;
		
	}

}