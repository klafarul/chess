package figure;

import board.Board;
import board.Cell;
import java.awt.Color;
/**
*абстрактный класс, который определяет поведение шахмотной фигуры
**/



public abstract class Figure {
	
	protected String name;
	// true - white; false - black;
	protected Color color;
	
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
	
	public Color getColor(){
		return color;
	}
	public void setColor(Color color){
		this.color = color;
	}
}