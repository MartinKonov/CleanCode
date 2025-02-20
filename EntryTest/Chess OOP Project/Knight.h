#pragma once
#include "Piece.h"

class Knight :public Piece {
public:
	Knight() = default;
	Knight(const String pieceName,  const int playerId) : Piece(pieceName, playerId) {}
};