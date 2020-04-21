import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WordGuessClient extends Application {
	gameInfo clientGame = new gameInfo();
	Client clientConnection;
	Button c1,c2,c3,guessButton,guessLetter,connect;
	HBox categories,bottombox;
	ListView<String> clientlog;
	TextField guessInput;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {



		Parent root = FXMLLoader.load(getClass().getResource("clientWelcomeScreen.fxml"));


		primaryStage.setTitle("(Client) Word Guess!!!");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(20);
		pane.setVgap(20);
		pane.setStyle("-fx-background-color:  #475B5A");
		Scene scene = new Scene(pane, 700, 487);
		pane.add(root, 0, 0);

		primaryStage.setScene(scene);
		primaryStage.show();


	}
}
