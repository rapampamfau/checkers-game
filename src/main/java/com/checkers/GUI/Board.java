package com.checkers.GUI;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Board {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final Group tileGroup = new Group();
    public static final Group pieceGroup = new Group();
    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = createPiece(PieceColor.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = createPiece(PieceColor.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return root;
    }

    public Piece createPiece(PieceColor color, int x, int y) {
        Piece piece = new Piece(color, x, y);
        return piece;
    }
}