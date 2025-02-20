#pragma once
#include "String.h"

class Piece {
private:
	String pieceName;
	int playerId;
public:
	Piece();
	Piece(const String pieceName, const int playerId);
	void setPieceName(const String pieceName);
	void setPlayerId(const int playerId);
	String getPieceName() const; 
	int getPlayerId() const;
};
