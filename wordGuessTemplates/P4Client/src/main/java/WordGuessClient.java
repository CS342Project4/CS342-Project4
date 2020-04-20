import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WordGuessClient extends Application {
	gameInfo clientGame = new gameInfo();
	Client clientConnection;
	Button c1,c2,c3,guessButton,guessLetter,connect;
	HBox categories,bottombox;
	ListView<String> clientlog;
	TextField guessWord;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane pane = new BorderPane();
		clientlog = new ListView<String>();
		primaryStage.setTitle("(Client) Word Guess!!!");
		clientConnection = new Client(data->{
			Platform.runLater(()->{
				clientlog.getItems().add(data.toString());
			});
			clientConnection.start();
		});
		guessButton = new Button("GUESS");
		c1 = new Button("Animals");
		c2 = new Button("Country");
		c3 = new Button("Superheroes");
		connect = new Button("Connect");
		guessWord = new TextField("Guess here");
		guessLetter = new Button("Guess Letter");
		bottombox = new HBox(10,guessWord,guessButton,guessLetter,connect);
		categories = new HBox(15,c1,c2,c3);
		pane.setTop(categories);
		pane.setCenter(clientlog);
		pane.setBottom(bottombox);
		connect.setOnAction(e->{
			clientGame.SuperheroSelect = false;
			clientGame.animalSelect=false;
			clientGame.countrySelect=false;
			clientGame.correctGuess = false;
			clientConnection = new Client(data->{
			Platform.runLater(()->{
				clientlog.getItems().add(data.toString());
			});
		});
		clientConnection.start();
		});
		guessButton.setOnAction(e->{
			clientGame.letterGuess = false;
			clientGame.wordGuess = true;
			clientGame.setInput(guessWord.getText());
			try {
				clientConnection.send(clientGame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		guessLetter.setOnAction(e->{
			clientGame.letterGuess = true;
			clientGame.wordGuess = false;
			System.out.println("debug ->"+clientGame.currentWord);
			clientGame.setLetterInput(guessWord.getText());
			try {
				clientConnection.send(clientGame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c2.setOnAction(e->{
			clientGame.setAnimalSelect(false);
			clientGame.setSuperheroSelect(false);
			clientGame.setCountrySelect(true);
			try {
				clientConnection.send(clientGame);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		c1.setOnAction(e->{
			clientGame.setAnimalSelect(true);
			clientGame.setSuperheroSelect(false);
			clientGame.setCountrySelect(false);
			try {
				clientConnection.send(clientGame);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		c3.setOnAction(e->{
			clientGame.setAnimalSelect(false);
			clientGame.setSuperheroSelect(true);
			clientGame.setCountrySelect(false);
			try {
				clientConnection.send(clientGame);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		pane.setPadding(new Insets(70));
		Scene scene = new Scene(pane,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
