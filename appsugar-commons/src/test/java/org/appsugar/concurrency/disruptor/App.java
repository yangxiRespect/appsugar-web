package org.appsugar.concurrency.disruptor;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) {
		launch(App.class, args);
	}

	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();
		Canvas canvas = new Canvas(300, 250);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		drawShapes(gc);

	}

	private void drawShapes(GraphicsContext gc) {

		long time = System.currentTimeMillis();

		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);
		for (int i = 0; i < 10000; i++) {
			gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
		}

		System.out.println(System.currentTimeMillis() - time);
	}
}
