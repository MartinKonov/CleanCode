#pragma once
#include "Piece.h"

class Bishop :public Piece {
public:
	Bishop() = default;
	Bishop(const String pieceName, const int playerId) :Piece(pieceName, playerId) {}
};
