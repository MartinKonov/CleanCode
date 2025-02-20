#include "Piece.h"

Piece::Piece() : pieceName(""), playerId(0) {}

Piece::Piece(const String pieceName,const int playerId)
{
	this->pieceName = pieceName;
	this->playerId = playerId;
}

void Piece::setPieceName(const String pieceName) {
    this->pieceName = pieceName;
}

void Piece::setPlayerId(int playerId) {
    this->playerId = playerId;
}
String Piece::getPieceName() const {
    return pieceName;
}
int Piece::getPlayerId() const {
    return playerId;
}
