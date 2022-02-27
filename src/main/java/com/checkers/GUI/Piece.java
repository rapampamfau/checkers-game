package com.checkers.GUI;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.checkers.GUI.Board.TILE_SIZE;

public class Piece extends StackPane {

    private PieceColor color;
    private double mouseX, mouseY;
    private double oldX, oldY;
    private boolean isKing = false;

    public Piece(PieceColor type, int x, int y) {
        this.color = type;

        move(x, y);

        if (isKinged()) {
            Circle kingPiecePart1 = new Circle(TILE_SIZE * 0.45);
            Circle kingPiecePart2 = new Circle(TILE_SIZE * 0.32);
            Circle kingPiecePart3 = new Circle(TILE_SIZE * 0.17);

            kingPiecePart1.setFill(type == PieceColor.RED ? Color.RED : Color.WHITE);
            kingPiecePart1.setStroke(Color.BLACK);
            kingPiecePart1.setStrokeWidth(TILE_SIZE * 0.03);
            kingPiecePart1.setTranslateX((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);
            kingPiecePart1.setTranslateY((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);

            kingPiecePart2.setFill(type == PieceColor.RED ? Color.RED : Color.WHITE);
            kingPiecePart2.setStroke(Color.BLACK);
            kingPiecePart2.setStrokeWidth(TILE_SIZE * 0.03);
            kingPiecePart2.setTranslateX((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);
            kingPiecePart2.setTranslateY((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);

            kingPiecePart3.setFill(type == PieceColor.RED ? Color.RED : Color.WHITE);
            kingPiecePart3.setStroke(Color.BLACK);
            kingPiecePart3.setStrokeWidth(TILE_SIZE * 0.03);
            kingPiecePart3.setTranslateX((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);
            kingPiecePart3.setTranslateY((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);

            getChildren().addAll(kingPiecePart1, kingPiecePart2, kingPiecePart3);
        } else {
            Circle piece = new Circle(TILE_SIZE * 0.45);
            piece.setFill(type == PieceColor.RED ? Color.RED : Color.WHITE);

            piece.setStroke(Color.BLACK);
            piece.setStrokeWidth(TILE_SIZE * 0.03);

            piece.setTranslateX((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);
            piece.setTranslateY((TILE_SIZE - TILE_SIZE * 0.45 * 2) / 2);

            getChildren().addAll(piece);
        }

        setOnMouseClicked(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }

    public boolean isKinged() {
        return isKing;
    }

    public void king() {
        isKing = true;
    }

    public PieceColor getColor() {
        return color;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }
}
