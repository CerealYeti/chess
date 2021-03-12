package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileCoord;
    private static final Map<Integer, EmtpyTile> EMPTY_TILES = createAllPossibleEmtpyTiles();

    private Tile(final int tileCoord) {
        this.tileCoord = tileCoord;
    }

    private static Map<Integer, EmtpyTile> createAllPossibleEmtpyTiles() {
        final Map<Integer, EmtpyTile> emtpyTileMap = new HashMap<>();

        for (int i = 0; i < 64; i++){
            emtpyTileMap.put(i, new EmtpyTile(i));
        }

        return ImmutableMap.copyOf(emtpyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmtpyTile extends Tile{
        EmtpyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        OccupiedTile(final int tileCoordinate, final Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }
}
