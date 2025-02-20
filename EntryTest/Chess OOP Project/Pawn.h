#pragma once
#include "Piece.h"

class Pawn :public Piece {
public:
	Pawn() = default;
	Pawn(const String pieceName, const int playerId) :Piece(pieceName, playerId) {}
};
