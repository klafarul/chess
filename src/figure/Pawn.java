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
	public Pawn(boolean color){
		
		this.name = "Pawn";
		this.color = color;
		
	}
	
	

	/**
	*сделать ход, если canMove = true;
	**/
	@Override
	public void move(Board chessBoard, Cell currentCell){
		
		Cell cellCanBeEaten = getCellCanBeEaten(chessBoard, currentCell);
		Cell cellCanBeMoved = getCellCanBeMoved(chessBoard, currentCell);
		if (cellCanBeEaten != null){
			System.out.println("Popalsya razbojnik");
			if (cellCanBeEaten.getFigure().getName() == "King"){
				if (cellCanBeEaten.getFigure().getColor()){
					chessBoard.getLives().removeWhiteKing();
				}
				else{
					chessBoard.getLives().removeBlackKing();
				}
			}
			chessBoard.getChessBoard()[cellCanBeEaten.getPosX()][cellCanBeEaten.getPosY()].setFigure(currentCell.getFigure());
			currentCell.setFigure(null);	
		}
		else{
			if (cellCanBeMoved != null){
				chessBoard.getChessBoard()[cellCanBeMoved.getPosX()][cellCanBeMoved.getPosY()].setFigure(currentCell.getFigure());
				currentCell.setFigure(null);
			}
		}
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
		if (color){
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
		if (color){
			int i = -1;

			while (i <= 1){				
				try{
					//проверка наличия фигур, которые может съесть пешка
					if(!chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i].isEmpty()){
						// проверка цвета найденной фигуры
						if (chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i].getFigure().getColor() == false){
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
						if (chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY() + i].getFigure().getColor() == true){
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