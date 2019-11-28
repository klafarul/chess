package board;

import game.Lives;
import figure.*;





public class Board{
	private Cell[][] chessBoard;
	public Lives kingsLives;
	
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
		Figure blackPawn = new Pawn(false);
		Figure blackRook = new Rook(false);
		Figure blackBishop = new Bishop(false);
		Figure blackQueen = new Queen(false);
		Figure blackKnight = new Knight(false);
		Figure blackKing = new King(false);
		
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
		Figure whitePawn = new Pawn(true);
		Figure whiteRook = new Rook(true);
		Figure whiteBishop = new Bishop(true);
		Figure whiteQueen = new Queen(true);
		Figure whiteKnight = new Knight(true);
		Figure whiteKing = new King(true);
		
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
					if (chessBoard[i][j].getFigure().getColor()){
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
	
	
	public void startGame(){
		Board board = new Board();
		board.createBoard();
		board.createFigures();
		
		boolean turn = true;
		
		
		
		System.out.println(board.kingsLives.getWinner());
	}
	
	
}