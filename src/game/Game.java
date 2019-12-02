package game;

import board.Board;
import figure.*;
import java.util.Random;

public class Game{

	
	
	public Game(){
	}
	public static void main(String[] args){
		Board board = new Board();
		board.createBoard();
		board.createFigures();
		board.outPutBoard();
		
		
		
		boolean moveDone = false;
		Color colorMove = Color.WHITE;
		
		int i = 0, j = 0;
		while (board.getLives().isKingsAlive()){
			
			for (i = 0; i < 8; i++){
				for (j = 0; j < 8; j++){
					if (board.getChessBoard()[i][j].isEmpty() == false){
						if (board.getChessBoard()[i][j].getFigure().getColor() == colorMove){
							moveDone = board.getChessBoard()[i][j].getFigure().move(board, board.getChessBoard()[i][j]);							
						}
					}
				}
			}
			if (moveDone){
				if (colorMove == Color.WHITE){
					colorMove = Color.BLACK;
				}
				else{
					colorMove = Color.WHITE; 
				}
				moveDone = false;
			}
		}
		System.out.println("==========================================================================================================");
		board.outPutBoard();	
		System.out.println(board.getLives().getWinner());	
		
	}
}