package NumberGuessingGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class NumberGuessingGame extends Application {
	private int targetNumber;
	private int attempts;

	@Override
	public void start(Stage primaryStage) {
		// generate a random number
		Random random = new Random();
		targetNumber = random.nextInt(50) + 1;
		attempts = 0;

		Label promptLabel = new Label("Guess a number between 1 to 50:");
		TextField inputField = new TextField();
		Label resultLabel = new Label("");

		// Creating a graphic (image)
		Image img = new Image("numbergame.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(80);
		view.setPreserveRatio(true);
		// Creating a Button
		Button button = new Button();
		// Setting the location of the button
		button.setTranslateX(200);
		button.setTranslateY(25);
		// Setting the size of the button
		button.setPrefSize(80, 80);
		// Setting a graphic to the button
		button.setGraphic(view);

		button.setOnAction(event -> {
			// user input
			String userInput = inputField.getText();
			if (!userInput.isEmpty()) {
				try {
					int userGuess = Integer.parseInt(userInput);
					if (userGuess < 0) {
						showAlert("Please enter a positive number.");
						return;
					}

					attempts++;

					// check the user input and the target number
					if (userGuess == targetNumber) {
						Alert alert = new Alert(AlertType.INFORMATION);
						showCongratulationsImage();
						alert.setTitle("Congrats!");
						alert.setHeaderText(null);
						alert.setContentText("You got it! \nTotal guess: " + attempts);
						alert.showAndWait();
						resultLabel.setText("");

						// Game restart
						targetNumber = random.nextInt(50) + 1;
						attempts = 0;
						resultLabel.setText("");
					} else if (userGuess < targetNumber) {
						resultLabel.setText("Too low. Try a higher one.");
					} else {
						resultLabel.setText("Too high. Try a lower one.");
					}
				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Enter a valid number");
					alert.showAndWait();
				}
			}
		});

		VBox root = new VBox(10);
		root.setPadding(new Insets(20));
		root.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		root.getChildren().addAll(promptLabel, inputField, button, resultLabel);

		Scene scene = new Scene(root, 600, 300);

		primaryStage.setTitle("Number Guessing game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showCongratulationsImage() {
		Image congratulationsImage = new Image("congrats.png");
	    ImageView imageView = new ImageView(congratulationsImage);
	    imageView.setFitWidth(390);
	    imageView.setFitHeight(120);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.TOP_CENTER);
	  
	    vbox.setPadding(new Insets(100, 0, 0, 0));
	    vbox.getChildren().addAll(imageView);

	    Stage congratulationsStage = new Stage();
	    congratulationsStage.setTitle("Congratulations!");
	    congratulationsStage.setScene(new Scene(new Group(imageView), 390, 120));
	    congratulationsStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
