package BugWorld;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	static Label bottomLabel = new Label();

	private ArrayList<Bug> bugs = new ArrayList<Bug>();
	private ArrayList<Plant> plants = new ArrayList<Plant>();

	public Main() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Bug b1 = new Butterfly(800, 500, 20, -1.5f, -1.5f, "Blair", "butterfly.png");
		Bug b2 = new Ladybug(140, 200, 10, -1.5f, -1.5f, "Fairlady", "ladybug.png");
		Bug b3 = new Ant(200, 300, 10, -1.5f, -1.5f, "Andy", "ant.png");
		Bug b4 = new Spider(200, 200, 15, -1.5f, -1.5f, "Charlotte", "spider.png");

		bugs.add(b1);
		bugs.add(b2);
		bugs.add(b3);
		bugs.add(b4);

		Plant p1 = new Plant(800, 200, 200, "tree", 0.1f, "tree.png");
		Plant p2 = new Plant(600, 700, 3, "grass", 0.1f, "grass.png");
		Plant p3 = new Plant(400, 700, 8, "blueberry", 0.1f, "blueberryTree.png");
		Plant p4 = new Plant(200, 700, 5, "flower", 0.1f, "flower.png");

		plants.add(p1);
		plants.add(p2);
		plants.add(p3);
		plants.add(p4);

		BorderPane bp = new BorderPane();

		final Pane bugworld = new Pane();
		bp.setCenter(bugworld);

		Pane right = new Pane();
		VBox rightvBox = new VBox(10);
		rightvBox.setBackground(Background.fill(Color.BEIGE));
		rightvBox.setMinWidth(100);

		Timeline timeline = new Timeline();

		// start button
		Button start = new Button();
		start.setText("Start Animation");
		start.setOnAction(e -> timeline.play());

		// stop button
		Button stop = new Button();
		stop.setText("Stop Animation");
		stop.setOnAction(e -> timeline.stop());

		rightvBox.getChildren().addAll(start, stop);
		right.getChildren().add(rightvBox);
		rightvBox.setMinWidth(100);

		bp.setRight(rightvBox);
		bp.setBackground(Background.fill(Color.ALICEBLUE));

		Slider animationSpeed = new Slider(1, 15, 1);
		Label label = new Label("Animation Speed:");
		animationSpeed.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int rate = (int) animationSpeed.getValue();
				timeline.setRate(rate);
			}
		});

		rightvBox.getChildren().addAll(animationSpeed, label);
		rightvBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(bp, 1080, 1000);

		BugWorldEventHandler bHandler = new BugWorldEventHandler();

		bugworld.getChildren().addAll();
		for (Bug b : bugs) {
			bugworld.getChildren().add(b);
			b.setOnMouseClicked(bHandler);
		}
		for (Plant p : plants) {
			bugworld.getChildren().add(p);
			p.grow();

		}

		VBox bottomvBox = new VBox();
		bottomvBox.setMinHeight(30);
		bottomvBox.setAlignment(Pos.CENTER);
		bottomvBox.getChildren().add(bottomLabel);
		bottomvBox.setBackground(Background.fill(Color.BEIGE));
		bp.setBottom(bottomvBox);

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				for (Plant p : plants) {
					if (p.getRadius() < 60) {
						p.grow();
					}
				}
				for (Bug b1 : bugs) {
					if (b1.getCenterX() < b1.getRadius()
							|| b1.getCenterX() + b1.getRadius() > scene.getWidth() - rightvBox.getMinWidth() - 35) {
						b1.setDx(-b1.getDx());
					}
					if (b1.getCenterY() < b1.getRadius()
							|| b1.getCenterY() + b1.getRadius() > scene.getHeight() - bottomvBox.getMinHeight()) {
						b1.setDy(-b1.getDy());
					}
					b1.setX(b1.getX() + b1.getDx());
					b1.setCenterX(b1.getX());
					b1.setY(b1.getY() + b1.getDy());
					b1.setCenterY(b1.getY());
					
					for (Plant p1 : plants) {
						if (bugMetPlant(b1, p1)) {
							if (b1.getRadius() <= 60) {
								b1.setRadius(b1.getRadius() + 0.2);
							}
							p1.setRadius(p1.getRadius() - 0.2);
							
							}
						}
					}
				}
		});

		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);

		primaryStage.setTitle("Welcome to Sophie's Bug World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public boolean bugMetPlant(Bug bug, Plant plant) {
		double distance = Math.sqrt(Math.pow(bug.getCenterX() - plant.getCenterX(), 2)
				+ Math.pow(bug.getCenterY() - plant.getCenterY(), 2));
		double totalRadius = bug.getRadius() + plant.getRadius();
		return distance <= totalRadius;
	}

	public static void main(String[] args) {
		launch(args);
	}
}