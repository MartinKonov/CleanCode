#include "Gameplay.h"
#include <iostream>
#include <map>

Gameplay::Gameplay()
{
}

Gameplay::~Gameplay()
{
}

bool Gameplay::isMoveCommandValid(String move)
{
	//example command: move d2 d4 
	if (move.length() != 10) {
		std::cout << "Invalid move format!" << std::endl;
		return false;
	}
	else if (move[0] != 'm' || move[1] != 'o' || move[2] != 'v' || move[3] != 'e' || move[4] != ' ' || move[7] != ' ') {
		std::cout << "Invalid move format!" << std::endl;
		return false;
	}
	else if (move[5] < 'a' || move[5] > 'h' || move[6] < '1' || move[6] > '8' || move[8] < 'a' || move[8] > 'h' || move[9] < '1' || move[9] > '8') {
		std::cout << "Invalid move format!" << std::endl;
		return false;
	}
	return true;
}

bool Gameplay::isMoveAllowed(String move, Board board[8][8], int currentPlayer) {
    int startCol = getColumnIndex(move[5]);
    int startRow = getRowIndex(move[6]);
    int endCol = getColumnIndex(move[8]);
    int endRow = getRowIndex(move[9]);

    if (!isPieceAtStart(board, startRow, startCol)) return false;
    if (!isCorrectPlayerPiece(board, startRow, startCol, currentPlayer)) return false;
    if (isOwnPieceAtDestination(board, endRow, endCol, currentPlayer)) return false;

    return validateMove(board, startRow, startCol, endRow, endCol, currentPlayer);
}

int Gameplay::getColumnIndex(char column) {
    return column - 'a';
}

int Gameplay::getRowIndex(char row) {
    return row - '1';
}

bool Gameplay::isPieceAtStart(Board board[8][8], int row, int col) {
    if (board[row][col].piece == nullptr) {
        std::cout << "There's no piece at that location." << std::endl;
        return false;
    }
    return true;
}

bool Gameplay::isCorrectPlayerPiece(Board board[8][8], int row, int col, int currentPlayer) {
    if (board[row][col].piece->getPlayerId() != currentPlayer) {
        std::cout << "That's not your piece!" << std::endl;
        return false;
    }
    return true;
}

bool Gameplay::isOwnPieceAtDestination(Board board[8][8], int row, int col, int currentPlayer) {
    if (board[row][col].piece != nullptr && board[row][col].piece->getPlayerId() == currentPlayer) {
        std::cout << "You already have a piece there." << std::endl;
        return true;
    }
    return false;
}
bool Gameplay::validatePawnMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer, int rowDiff, int colDiff) {
    int direction = (currentPlayer == 0) ? -1 : 1;  // Assuming 0 = White, 1 = Black
    int startRowInitial = (currentPlayer == 0) ? 6 : 1;

    if (colDiff == 0 && rowDiff == direction && board[endRow][endCol].piece == nullptr) {
        return true;
    }

    if (startRow == startRowInitial && colDiff == 0 && rowDiff == 2 * direction && board[endRow][endCol].piece == nullptr) {
        return true;
    }

    if (colDiff == 1 && rowDiff == direction && board[endRow][endCol].piece != nullptr) {
        return true;
    }
    return false;
}

bool Gameplay::validateKingMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer) {
    return abs(startRow - endRow) <= 1 && abs(startCol - endCol) <= 1;
}

bool Gameplay::validateKnightMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer) {
    int rowDiff = abs(startRow - endRow);
    int colDiff = abs(startCol - endCol);
    return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
}

bool Gameplay::validateBishopMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer, int rowDiff, int colDiff) {
    if (abs(rowDiff) != abs(colDiff)) return false; 

    int rowStep = (rowDiff > 0) ? -1 : 1;
    int colStep = (colDiff > 0) ? -1 : 1;
    
    int row = startRow + rowStep;
    int col = startCol + colStep;
    
    while (row != endRow && col != endCol) {
        if (board[row][col].piece != nullptr) return false;
        row += rowStep;
        col += colStep;
    }
    
    return true;
}

bool Gameplay::validateRookMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer) {
    if (startRow != endRow && startCol != endCol) return false;

    int rowStep = (endRow > startRow) ? 1 : (endRow < startRow ? -1 : 0);
    int colStep = (endCol > startCol) ? 1 : (endCol < startCol ? -1 : 0);
    
    int row = startRow + rowStep;
    int col = startCol + colStep;
    
    while (row != endRow || col != endCol) {
        if (board[row][col].piece != nullptr) return false;
        row += rowStep;
        col += colStep;
    }
    
    return true;
}

bool Gameplay::validateQueenMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer, int rowDiff, int colDiff) {
    return validateRookMove(board, startRow, startCol, endRow, endCol, currentPlayer) ||
           validateBishopMove(board, startRow, startCol, endRow, endCol, currentPlayer, rowDiff, colDiff);
}

bool Gameplay::validateMove(Board board[8][8], int startRow, int startCol, int endRow, int endCol, int currentPlayer) {
    String pieceName = board[startRow][startCol].piece->getPieceName();
    int rowDiff = startRow - endRow;
    int colDiff = startCol - endCol;

    if (pieceName == "Pawn") {
        return validatePawnMove(board, startRow, startCol, endRow, endCol, currentPlayer, rowDiff, colDiff);
    } else if (pieceName == "King") {
        return validateKingMove(board, startRow, startCol, endRow, endCol, currentPlayer);
    } else if (pieceName == "Queen") {
        return validateQueenMove(board, startRow, startCol, endRow, endCol, currentPlayer, rowDiff, colDiff);
    } else if (pieceName == "Knight") {
        return validateKnightMove(board, startRow, startCol, endRow, endCol, currentPlayer);
    } else if (pieceName == "Bishop") {
        return validateBishopMove(board, startRow, startCol, endRow, endCol, currentPlayer, rowDiff, colDiff);
    } else if (pieceName == "Rook") {
        return validateRookMove(board, startRow, startCol, endRow, endCol, currentPlayer);
    }

    return false;
}


void Gameplay::FillBoard()
{ 
	for (int i = 0; i < 28; i++){
		for (int j = 0; j < 65; j++){
			displayGrid[i][j] = ' ';
		}
	}
}
void Gameplay::PaintBoard()
{
	for (int i = 3; i < 27; i++){
		for (int j = 7; j < 63; j++){
			if ((((i > 2 && i < 6) || (i > 8 && i < 12) || (i > 14 && i < 18) || (i > 20 && i < 24))
				&& ((j > 6 && j < 14) || (j > 20 && j < 28) || (j > 34 && j < 42) || (j > 48 && j < 56))) ||
				(((i > 5 && i < 9) || (i > 11 && i < 15) || (i > 17 && i < 21) || (i > 23 && i < 27))
					&& ((j > 13 && j < 21) || (j > 27 && j < 35) || (j > 41 && j < 49) || (j > 55 && j < 63))))
				displayGrid[i][j] = 219;
		}
	}
}
void Gameplay::PrintBorders() {
	for (int i = 3; i < 27; i++){
		displayGrid[i][5] = 186;
		displayGrid[i][64] = 186;
	}
	displayGrid[2][5] = 201;
	displayGrid[2][64] = 187;
	displayGrid[27][64] = 188;
	displayGrid[27][5] = 200;
	for (int j = 6; j < 64; j++){
		displayGrid[2][j] = 205;
		displayGrid[27][j] = 205;
	}
}
void Gameplay::PrintNumbering()
{
	int k = 0;

	for (int j = 10; j < 63; j += 7){
		displayGrid[1][j] = 65 + k;
		k++;

	}

	for (int i = 4; i < 27; i += 3){{
			displayGrid[i][3] = 41 + k;
			k++;
		}
	}
}
void Gameplay::FigureSpaceCleaning()
{
	for (int i = 4; i < 27; i += 3){
		for (int j = 10; j < 63; j += 7){
			displayGrid[i][j - 1] = '  ';
			displayGrid[i][j] = '  ';
			displayGrid[i][j + 1] = '  ';
		}
	}
}


void Gameplay::PlacePieces(Board board[8][8]) {
    const int rowOffsets[8] = {4, 7, 10, 13, 16, 19, 22, 25};
    const int colOffsets[8] = {10, 17, 24, 31, 38, 45, 52, 59};
    
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            int row = rowOffsets[i];
            int col = colOffsets[j];
            
            if (board[i][j].piece == nullptr) {
                displayGrid[row][col] = ' ';
            } else {
                char pieceSymbol = GetPieceSymbol(board[i][j].piece);
                displayGrid[row][col] = pieceSymbol;
            }
        }
    }
    
    for (int i = 0; i < 28; i++) {
        for (int j = 0; j < 65; j++) {
            std::cout << displayGrid[i][j];
        }
        std::cout << std::endl;
    }
}

char Gameplay::GetPieceSymbol(Piece* piece) {
    if (!piece) return ' ';

    String name = piece->getPieceName();
    int player = piece->getPlayerId();

    std::map<String, char> pieceMap = {
        {"Pawn", 'P'}, {"King", 'K'}, {"Queen", 'Q'},
        {"Rook", 'R'}, {"Knight", 'N'}, {"Bishop", 'B'}
    };

    char symbol = pieceMap[name];

    return (player == 2) ? std::tolower(symbol) : symbol;
}

void Gameplay::PrintBoard(Board board[8][8])
{
	FillBoard();
	PrintNumbering();
	PaintBoard();
	FigureSpaceCleaning();
	PrintBorders();
	PlacePieces(board);
}

void Gameplay::InitializePieces() {

    pieces.push_back(new Pawn("Pawn", 1));
    pieces.push_back(new Rook("Rook", 1));
    pieces.push_back(new Knight("Knight", 1));
    pieces.push_back(new Bishop("Bishop", 1));
    pieces.push_back(new Queen("Queen", 1));
    pieces.push_back(new King("King", 1));

    pieces.push_back(new Pawn("Pawn", 2));
    pieces.push_back(new Rook("Rook", 2));
    pieces.push_back(new Knight("Knight", 2));
    pieces.push_back(new Bishop("Bishop", 2));
    pieces.push_back(new Queen("Queen", 2));
    pieces.push_back(new King("King", 2));
}

void Gameplay::SetupBoard() {

    board[7][0].piece = new Rook("Rook", 1);
    board[7][1].piece = new Knight("Knight", 1);
    board[7][2].piece = new Bishop("Bishop", 1);
    board[7][3].piece = new Queen("Queen", 1);
    board[7][4].piece = new King("King", 1);
    board[7][5].piece = new Bishop("Bishop", 1);
    board[7][6].piece = new Knight("Knight", 1);
    board[7][7].piece = new Rook("Rook", 1);

    for (int i = 0; i < 8; i++) {
        board[6][i].piece = new Pawn("Pawn", 1); 
    }


    board[0][0].piece = new Rook("Rook", 2);
    board[0][1].piece = new Knight("Knight", 2);
    board[0][2].piece = new Bishop("Bishop", 2);
    board[0][3].piece = new Queen("Queen", 2);
    board[0][4].piece = new King("King", 2);
    board[0][5].piece = new Bishop("Bishop", 2);
    board[0][6].piece = new Knight("Knight", 2);
    board[0][7].piece = new Rook("Rook", 2);

    for (int i = 0; i < 8; i++) {
        board[1][i].piece = new Pawn("Pawn", 2); // Black pawns
    }
}

void Gameplay::DisplayHelpMenu() {
    std::cout << "----------------- Help Menu -----------------" << std::endl;
    std::cout << "Available Commands:" << std::endl;
    std::cout << "  - move x1y1 x2y2  : Moves a piece from (x1, y1) to (x2, y2)" << std::endl;
    std::cout << "    Example: move d2 d4" << std::endl;
    std::cout << "  - exit            : Exits the game." << std::endl;
    std::cout << "---------------------------------------------" << std::endl;
}

void Gameplay::PlayGame() {
    InitializePieces();
    SetupBoard();
    
    int currPlayer = 2;
    std::string command;
    
    while (true) {
        currPlayer = (currPlayer == 1) ? 2 : 1;
        PrintBoard(board);
        
        std::cout << "\nIt is Player " << currPlayer << "'s turn. Please enter your command:" << std::endl;
        std::cin >> command;
        
        if (command == "help") {
            DisplayHelpMenu();
            currPlayer = (currPlayer == 1) ? 2 : 1;
        } else if (command == "exit") {
            break;
        } else {
            while (!isMoveCommandValid(String(command.c_str())) || !isMoveAllowed(String(command.c_str()), board, currPlayer)) {
                std::cout << "Invalid move. Please enter your move again:" << std::endl;
                std::cin >> command;
            }
        }
        
        if (kingCaptured) {
            std::cout << "Player " << currPlayer << " wins!" << std::endl;
            break;
        }
    }
}