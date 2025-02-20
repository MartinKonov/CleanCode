#pragma once
#include "Piece.h"

class King :public Piece {
public:
	King() = default;
	King(const String pieceName, const int playerId) :Piece(pieceName,  playerId) {}
};