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
	*проверка: может ли пешка сделать ход?
	**/
	@Override
	public boolean canMove(Board chessBoard, Cell currentCell){

		if (canEat(chessBoard, currentCell)){return true;}
		
		// color - white
		if (color){
			if ((currentCell.getPosX() != 0) && (chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY()].isEmpty()) ){
				cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY()];
				return true;
			}
		}
		
		// color - black
		else{
			if ((currentCell.getPosX() != 7) && (chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY()].isEmpty())){
				cellCanBeMoved = chessBoard.getChessBoard()[currentCell.getPosX() + 1][currentCell.getPosY()];
				return true;
			}
		}
		
		
		return false;
				
	}
	/**
	* проверка может ли пешка съесть фигуру
	**/
	@Override
	public boolean canEat(Board chessBoard, Cell currentCell){
		
		boolean flag = false;
		
		// color - white
		if (color){
			int i = -1;

			while (i <= 1){				
				try{
					//проверка наличия фигур, которые может съесть пешка
					if(chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i].isEmpty() == false){
						// проверка цвета найденной фигуры
						if (chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i].getFigure().getColor() == false){
							cellCanBeEaten = chessBoard.getChessBoard()[currentCell.getPosX() - 1][currentCell.getPosY() + i]; 						
							flag = true; 							
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
							flag = true; 
						}						
					}
					i += 2;
				}
				catch(ArrayIndexOutOfBoundsException ex){
					i += 2;
				}				
			}			
		}
		return flag;
	}
}