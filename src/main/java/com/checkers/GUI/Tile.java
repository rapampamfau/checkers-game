package com.checkers.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.checkers.GUI.Board.TILE_SIZE;


public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /* Constructor that makes tile light or dark */
    public Tile(boolean light, int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        setFill(light ? Color.valueOf("#FAEBD7") : Color.valueOf("#CD853F"));
    }
}