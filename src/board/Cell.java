package board;

import figure.Figure;
import figure.Pawn;


public class Cell{
	
	// фигура которая находится в ячейки
	private Figure figure;
	
	//получаем позицию во время инициализации поля клетками(ячейками) 
	private int posX; // положение по вертикали
	private int posY; // положение по горизонтали
	
	
	public Cell(int x, int y, Figure figure){
		posX = x;
		posY = y;
		this.figure = figure;
	}
	public Cell(int x, int y){
		posX = x;
		posY = y;	
		this.figure = null;
	}
	public boolean isEmpty(){
		if (figure != null){
			return false;			
		}
		else{
			return true;
		}
	}
	

	
	public int getPosX(){return this.posX;}
	public void setPosX(int x){this.posX = x;}
	
	public int getPosY(){return this.posY;}
	public void setPosY(int y){this.posY = y;}
	
	public Figure getFigure(){return this.figure;}
	public void setFigure(Figure figure){this.figure = figure;}
}
