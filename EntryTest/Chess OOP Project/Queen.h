#pragma once
#include "Piece.h"


class Queen : public Piece {
public:
	Queen() = default;
	Queen(const String pieceName, const int playerId) :Piece(pieceName, playerId) {}
};