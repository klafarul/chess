package figure;

import board.Board;
import board.Cell;

/**
*абстрактный класс, который определяет поведение шахмотной фигуры
**/


	enum Color{
		WHITE,
		BLACK
	}
public abstract class Figure {
	
	protected String name;
	// true - white; false - black;
	protected boolean color;
	
	//выполняется если  canMove = true 
	public abstract void move(Board chessBoard, Cell currentCell);
	public abstract Cell getCellCanBeEaten(Board chessBoard, Cell currentCell);	
	public abstract Cell getCellCanBeMoved(Board chessBoard, Cell currentCell);
	
	
	
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public boolean getColor(){
		return color;
	}
	public void setColor(boolean color){
		this.color = color;
	}
}