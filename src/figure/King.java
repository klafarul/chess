package figure;

import board.*;
import java.util.ArrayList;
import java.util.Random;


public class King extends Figure{
	
	
	public King(Color color){
		this.name = "King";
		this.color = color;
	}
	/**
	*проверка: может ли King сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		ArrayList<Cell> cells = new ArrayList<Cell>();
		Random random = new Random();
		
		Cell cellCanBeMoved = null;
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		
		while(countChecks < 9){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty() == true){					
					cellCanBeMoved = chessBoard.getChessBoard()[i][j];
					cells.add(cellCanBeMoved);						
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
		if (cells.size() > 0){
			cellCanBeMoved = cells.get(random.nextInt(cells.size()));
		}
		return cellCanBeMoved;
	}
	/**
	* проверка может ли King съесть фигуру
	**/
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		ArrayList<Cell> cells = new ArrayList<Cell>();
		Random random = new Random();
		Cell cellCanBeEaten = null;
		
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		while(countChecks < 9){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty() == false){
					if (chessBoard.getChessBoard()[i][j].getFigure().getColor() != this.color){						
						cellCanBeEaten = chessBoard.getChessBoard()[i][j];
						cells.add(cellCanBeEaten);						
					}
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
		if (cells.size() > 0){
			cellCanBeEaten = cells.get(random.nextInt(cells.size()));
		}
		
		return cellCanBeEaten;
	}	
}