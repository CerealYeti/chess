package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

  protected final int piecePosition;
  protected final Alliance pieceAlliance;
  protected final boolean isFirstMove;

  Piece(int piecePosition, Alliance pieceAlliance) {
    this.piecePosition = piecePosition;
    this.pieceAlliance = pieceAlliance;
    // TODO more work here
    this.isFirstMove = false;
  }

  public int getPiecePosition() {
    return this.piecePosition;
  }

  public abstract Collection<Move> calculateLegalMoves(final Board board);

  public Alliance getAlliance() {
    return pieceAlliance;
  }

  public boolean isFirstMove() {
    return this.isFirstMove;
  }

  public enum PieceType {

    PAWN("P"),
    KNIGHT("N"),
    ROOK("R"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private String pieceName;

    PieceType( String pieceName){
      this.pieceName = pieceName;
    }

    @Override
    public String toString(){
      return this.pieceName;
    }
  }
}
