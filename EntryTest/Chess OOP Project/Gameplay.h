#pragma once
#include <vector>
#include "Board.h"
#include "Pawn.h"
#include "Rook.h"
#include "Knight.h"
#include "Bishop.h"
#include "Queen.h"
#include "King.h"

class Gameplay {
private:
	std::vector<Piece*> pieces;
public:
	Board board[8][8];
	char displayGrid[28][65];
	bool kingCaptured = false;
	Gameplay();
	~Gameplay();
	void PlayGame();
	bool isMoveCommandValid(String move);
	bool isMoveAllowed (String move, Board board[8][8], int currentPlayer);
	void FillBoard();
	void PaintBoard();
	void PrintBorders();
	void PrintNumbering();
	void FigureSpaceCleaning();
	void PlacePieces(Board board[8][8]);
	void PrintBoard(Board board[8][8]);
	void InitializePieces();
	void SetupBoard();
	void DisplayHelpMenu();


	char GetPieceSymbol(Piece* piece);

	int getColumnIndex(char column);
	int getRowIndex(char row);

	bool isPieceAtStart(Board board[8][8], int row, int col);
	bool isCorrectPlayerPiece(Board board[8][8], int row, int col, int currentPlayer);
	bool isOwnPieceAtDestination(Board board[8][8], int row, int col, int currentPlayer);
	bool validateMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer);
	bool validatePawnMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer, int rowDiff, int colDiff);
	bool validateKingMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer);
	bool validateKnightMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer);
	bool validateBishopMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer, int rowDiff, int colDiff);
	bool validateRookMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer);
	bool validateQueenMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer, int rowDiff, int colDiff);

};


