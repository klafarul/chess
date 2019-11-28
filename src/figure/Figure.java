package figure;

import board.Board;
import board.Cell;

/**
*абстрактный класс, который определяет поведение шахмотной фигуры
**/


public abstract class Figure {
	
	protected String name;
	protected Cell cellCanBeEaten;
	protected Cell cellCanBeMoved;	
	// true - white; false - black;
	protected boolean color;
	
	
	//выполняется если  canMove = true 
	public abstract void move(Board chessBoard, Cell currentCell);
	public abstract boolean canMove(Board chessBoard, Cell currentCell);	
	public abstract boolean	canEat(Board chessBoard, Cell currentCell);
	//public abstract boolean findEnemyHorizontal(Board chessBoard, Cell currentCell, int i);
	
	
	
	public String getName(){
		return this.name;
	}
	public void   setName(String name){this.name = name;}
	
	public boolean getColor(){return color;}
	public void setColor(boolean color){this.color = color;}
	
	public void setCellCanBeEaten(Cell cell){
		cellCanBeEaten = cell;
	}
	
	public boolean getCellCanBeEaten(){
		if (cellCanBeEaten != null){
			return true;			
		}
		else{
			return false;
		}
	}
}