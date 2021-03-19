package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private static final int[] CANDIDATE_MOVE_COORDINATE = { 7, 8, 9, 16 };

    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    
    private static boolean isAttackRightException(final Pawn currentPiece, final int candidateOffset) {
        return ((candidateOffset == 7) &&
                !((BoardUtils.EIGHTH_COLUMN[currentPiece.piecePosition] && currentPiece.pieceAlliance.isWhite()) ||
                        (BoardUtils.FIRST_COLUMN[currentPiece.piecePosition] && currentPiece.pieceAlliance.isBlack())));
    }
    
    private static boolean isAttackLeftException(final Pawn currentPiece, final int candidateOffset) {
        return ((candidateOffset == 9) &&
                !((BoardUtils.FIRST_COLUMN[currentPiece.piecePosition] && currentPiece.pieceAlliance.isWhite()) ||
                        (BoardUtils.EIGHTH_COLUMN[currentPiece.piecePosition] && currentPiece.pieceAlliance.isBlack())));
    }
    
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //TODO create new move and change for pawn and deal with promotions
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack())
                            || (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite()))) {

                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);

                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }
            } else if (isAttackRightException(this, candidateDestinationCoordinate)) {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.pieceAlliance) {
                        // TODO more to do here (attack into pawn promotion)
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            } else if (isAttackLeftException(this, candidateDestinationCoordinate)){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.pieceAlliance) {
                        // TODO more to do here(attack into pawn promotion)
                        legalMoves.add(new Move.AttackMove(board, this,candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
            // TODO need to check for en passant moves
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
