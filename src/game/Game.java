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
		//Color turnToMakeMove = Color.WHITE;
		
		boolean turn = true;
		boolean flag = false;
		int i = 0, j = 0;
		while (board.getLives().isKingsAlive()){
			
			for (i = 0; i < 8; i++){
				for (j = 0; j < 8; j++){
					if (board.getChessBoard()[i][j].isEmpty() == false){
						if (board.getChessBoard()[i][j].getFigure().getColor() == turn){
							if (board.getChessBoard()[i][j].getFigure().getCellCanBeMoved(board, board.getChessBoard()[i][j]) != null){
								board.getChessBoard()[i][j].getFigure().move(board, board.getChessBoard()[i][j]);
								flag = true;						
							}
						}
					}
				}
			}
			if (flag){
				turn = !turn;
				flag = false;
			}
		}
		System.out.println("==========================================================================================================");
		board.outPutBoard();	
		System.out.println(board.getLives().getWinner());	
	}
}