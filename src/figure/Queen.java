package figure;

import board.*;


public class Queen extends Figure{
	
	public Queen(Color color){
		this.name = "Queen";
		this.color = color;
	}
	
	
	
	@Override
	public Cell getCellCanBeMoved(Board chessBoard, Cell currentCell){
		
		
		
		Cell cellCanBeMoved = null;
		
		int i = currentCell.getPosX() - 1, countChecks = 0;
		int j = currentCell.getPosY() - 1;
		
		while ((cellCanBeMoved == null) && (countChecks < 9)){
			try{
				if (chessBoard.getChessBoard()[i][j].isEmpty()){
					cellCanBeMoved = chessBoard.getChessBoard()[i][j];
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
		return cellCanBeMoved;
	}
	@Override
	public Cell getCellCanBeEaten(Board chessBoard, Cell currentCell){
		Cell cellCanBeEaten = null;
		
		int counter = 0;
		int j = 1;
				

		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellHorizontal(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}	
		counter = 0;
		j = 1;
		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellVertical(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}
		counter = 0;
		j = 1;
		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellMainDiagonal(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}	
		counter = 0;
		j = 1;
		while ((cellCanBeEaten == null) && (counter < 2)){
			cellCanBeEaten = findEnemyCellSideDiagonal(chessBoard, currentCell, j);
			j = -1;	
			counter ++;
		}

		return cellCanBeEaten;		
	}
}