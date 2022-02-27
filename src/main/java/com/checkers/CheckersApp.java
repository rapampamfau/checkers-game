package com.checkers;

import com.checkers.GUI.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckersApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Board board = new Board();
        Scene scene = new Scene(board.createContent());

        primaryStage.setResizable(false);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}