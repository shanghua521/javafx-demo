package com.wang.javafx.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Demo extends Application {

    double x = 0;
    double y = 0;

    double sceneX = 0;
    double sceneY = 0;

    @Override
    public void start(Stage primaryStage) {
        var anchorPane = new AnchorPane();
        var button = new Button("button");
        var background = new Background(new BackgroundFill(Paint.valueOf("#FFB5C5"), new CornerRadii(15), new Insets(0)));
        anchorPane.setBackground(background);
        anchorPane.setBorder(new Border(new BorderStroke(Color.valueOf("#912CEE"), BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(2), new Insets(0))));

        button.setOnMousePressed(event -> {
            x = event.getX();
            y = event.getY();
        });

        button.setOnMouseDragged(event -> {
            var sceneX = event.getSceneX();
            var sceneY = event.getSceneY();
            AnchorPane.setLeftAnchor(button, sceneX - x);
            AnchorPane.setTopAnchor(button, sceneY - y);
        });

        anchorPane.getChildren().add(button);
        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 100.0);
        var scene = new Scene(anchorPane);
        scene.setFill(Paint.valueOf("#ffffff00"));

        scene.setOnMousePressed(event -> {
            sceneX = event.getSceneX() - primaryStage.getX();
            sceneY = event.getSceneY() - primaryStage.getY();
        });

        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - sceneX);
            primaryStage.setY(event.getScreenY() - sceneY);
        });

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}
