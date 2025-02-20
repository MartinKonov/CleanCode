#pragma once
#include "Piece.h"

class Rook :public Piece {
public:
	Rook() = default;
	Rook(const String pieceName, const int playerId) :Piece(pieceName, playerId) {}
};
