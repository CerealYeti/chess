package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {

  private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9};

  Bishop(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  private static boolean isFirstColumnExclusion(final int currentPosition,
      final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9
        || candidateOffset == 7);
  }

  private static boolean isEighthColumnExclusion(final int currentPosition,
      final int candidateOffset) {
    return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7
        || candidateOffset == 9);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {

    int candidateDestinationCoordinate;
    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {

      candidateDestinationCoordinate = this.piecePosition;

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(this.piecePosition, candidateDestinationCoordinate)
            || isEighthColumnExclusion(this.piecePosition, candidateDestinationCoordinate)) {
          break;
        }

        candidateDestinationCoordinate += candidateCoordinateOffset;

        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

          if (!candidateDestinationTile.isTileOccupied()) {

            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

          } else {

            final Piece pieceAtDestination = candidateDestinationTile.getPiece();
            final Alliance pieceAlliance = pieceAtDestination.getAlliance();

            if (this.pieceAlliance != pieceAlliance) {
              legalMoves.add(
                  new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
            }

            break;
          }
        }
      }
    }
    return ImmutableList.copyOf(legalMoves);
  }
}