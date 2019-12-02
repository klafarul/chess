package figure;


import board.*;


/**
*класс пешка
**/
public class Pawn extends Figure{
	/**
	*Конструктор.
	*color true = white,
	*color false = black
	*/
	public Pawn(Color color){
		
		this.name = "Pawn";
		this.color = color;
		
	}
	
	

	
	
	
	/**
	*проверка: может ли пешка сделать ход?
	**/
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		Cell cellCanBeMoved = null;
		if (getCellCanBeEaten(chessBoard, currentCell) != null){
			return getCellCanBeEaten(chessBoard, currentCell);
		}
		// color - white
		if (color == Color.WHITE){
			if ((currentCell.getPosX() != 0) && (chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY()].isEmpty()) ){
				cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY()];
				
			}
		}
		// color - black
		else{
			if ((currentCell.getPosX() != 7) && (chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY()].isEmpty())){
				cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY()];
			}
		}
		return cellCanBeMoved;
	}
	
	
	/**
	* проверка может ли пешка съесть фигуру
	**/
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = null;
		
		boolean flag = false;
		
		// color - white
		if (color == Color.WHITE){
			int i = -1;

			while (i <= 1){				
				try{
					//проверка наличия фигур, которые может съесть пешка
					if(!chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i].isEmpty()){
						// проверка цвета найденной фигуры
						if (chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i].getFigure().getColor() == Color.BLACK){
							cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i]; 						
							 							
						}						
					}
					i += 2;
				}
				catch(ArrayIndexOutOfBoundsException ex){
					i += 2;
				}			
			}
		}
		// color - black
		else{
			int i = - 1; 
			while(i <= 1){
				try{
					//проверка наличия фигур, которые может съесть пешка
					if(chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY() + i].isEmpty() == false){
						// проверка цвета найденной фигуры
						if (chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY() + i].getFigure().getColor() == Color.WHITE){
							cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY() + i]; 
							 
						}						
					}
					i += 2;
				}
				catch(ArrayIndexOutOfBoundsException ex){
					i += 2;
				}				
			}			
		}
		return cellCanBeEaten;
	}
	
}