package com.checkers.GUI;

import com.checkers.GameLogic.MoveResult;
import com.checkers.GameLogic.MoveType;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Board {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final Group tileGroup = new Group();
    public static final Group pieceGroup = new Group();
    public static Tile[][] board = new Tile[WIDTH][HEIGHT];

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

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = tryMove(piece, newX, newY);
            }

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    if (color == PieceColor.RED && newY == 7 || color == PieceColor.WHITE && newY == 0) {
                        piece.king(piece.getColor());
                    }
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        });
        return piece;
    }

        public MoveResult tryMove(Piece piece, int newX, int newY) {
            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
                return new MoveResult(MoveType.NONE);
            }

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (piece.getColor() == PieceColor.RED) {

                if (Math.abs(newX - x0) == 1 && newY - y0 == 1) {
                    return new MoveResult(MoveType.NORMAL);
                } else if (Math.abs(newX - x0) == 2 && newY - y0 == 2) {

                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor() != piece.getColor()) {
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    }
                }

            } else if (piece.getColor() == PieceColor.WHITE) {
                if (Math.abs(newX - x0) == 1 && newY - y0 == -1) {
                    return new MoveResult(MoveType.NORMAL);

                } else if (Math.abs(newX - x0) == 2 && newY - y0 == -2) {

                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor() != piece.getColor()) {
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    }
                }
            }

            if (piece.isKinged()) {
                if (Math.abs(newX - x0) < 8 && newY - y0 < 8 || Math.abs(newX - x0) > -8 && newY - y0 > -8) {

                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor() != piece.getColor()) {
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    } else {
                        return new MoveResult(MoveType.NORMAL);
                    }
                }
            }

            return new MoveResult(MoveType.NONE);
        }

        private int toBoard(double pixel) {
            return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
        }
    }