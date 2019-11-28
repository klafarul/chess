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
	
	@Override
	public boolean canMove(Board chessBoard, Cell currentCell){
		
		if (canEat(chessBoard, currentCell)){return true;}
		
		boolean flag = false;
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		while(!flag && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[i][j];					
					flag = true;
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
	@Override
	public boolean canEat(Board chessBoard, Cell currentCell){
		boolean flag = false;
		
		while (!flag){
			flag = findEnemyMainDiagonal(chessBoard, currentCell, 1);
			flag = findEnemyHorizontal(chessBoard, currentCell, 1);
			flag = findEnemyMainDiagonal(chessBoard, currentCell, -1);
			flag = findEnemyHorizontal(chessBoard, currentCell, -1);			
			flag = findEnemySideDiagonal(chessBoard, currentCell, -1);
			flag = findEnemyVertical(chessBoard, currentCell, -1);
			flag = findEnemySideDiagonal(chessBoard, currentCell, 1);
			flag = findEnemyVertical(chessBoard, currentCell, 1);
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