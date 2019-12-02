package board;

import game.Lives;
import figure.*;

public class Board{
	private Cell[][] chessBoard;
	private Lives kingsLives;
	
	public Lives getLives(){
		return kingsLives;
	}
	public void setLives(Lives kingsLives){
		this.kingsLives = kingsLives;
	}
	
	public void createBoard(){
		chessBoard = new Cell[8][8];
		for (int i = 0;i < 8; i++){
            for (int j = 0; j < 8; j++){
                chessBoard[i][j] = new Cell(i,j, null);
				chessBoard[i][j].setFigure(null);
            }
        }		
	}
	public void createFigures(){
		
		//black figures
		Figure blackPawn = new Pawn(Color.BLACK);
		Figure blackRook = new Rook(Color.BLACK);
		Figure blackBishop = new Bishop(Color.BLACK);
		Figure blackQueen = new Queen(Color.BLACK);
		Figure blackKnight = new Knight(Color.BLACK);
		Figure blackKing = new King(Color.BLACK);
		
		for (int i = 0; i < 8; i++){			
			chessBoard[1][i].setFigure(blackPawn);
		}
		chessBoard[0][0].setFigure(blackRook);
		chessBoard[0][7].setFigure(blackRook);
		chessBoard[0][2].setFigure(blackBishop);
		chessBoard[0][5].setFigure(blackBishop);
		chessBoard[0][3].setFigure(blackQueen);
		chessBoard[0][1].setFigure(blackKnight);
		chessBoard[0][6].setFigure(blackKnight);
		chessBoard[0][4].setFigure(blackKing);
		
		//white figures
		Figure whitePawn = new Pawn(Color.WHITE);
		Figure whiteRook = new Rook(Color.WHITE);
		Figure whiteBishop = new Bishop(Color.WHITE);
		Figure whiteQueen = new Queen(Color.WHITE);
		Figure whiteKnight = new Knight(Color.WHITE);
		Figure whiteKing = new King(Color.WHITE);
		
		for (int i = 0; i < 8; i++){
			chessBoard[6][i].setFigure(whitePawn);			
		}
	
		chessBoard[7][0].setFigure(whiteRook);
		chessBoard[7][7].setFigure(whiteRook);
		chessBoard[7][2].setFigure(whiteBishop);
		chessBoard[7][5].setFigure(whiteBishop);
		chessBoard[7][3].setFigure(whiteQueen);
		chessBoard[7][1].setFigure(whiteKnight);
		chessBoard[7][6].setFigure(whiteKnight);
		chessBoard[7][4].setFigure(whiteKing);
		
		kingsLives = new Lives((King)whiteKing, (King)blackKing);
	}
	
	public void outPutBoard(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (!chessBoard[i][j].isEmpty()){
					if (chessBoard[i][j].getFigure().getColor() == Color.WHITE){
						System.out.print("W" + chessBoard[i][j].getFigure().getName() + "      ");
					}
					else{
						System.out.print("B" + chessBoard[i][j].getFigure().getName() + "      ");
					}
					
				}
				else{
					System.out.print(chessBoard[i][j].isEmpty() + "      ");					
				}
			}
			System.out.println();
		}
	}
	
	public Cell[][] getChessBoard(){
		return chessBoard;
	}
}