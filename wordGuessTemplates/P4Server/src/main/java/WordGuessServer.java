import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;

public class WordGuessServer extends Application {
	//********
	ListView<String> serverlog;
	Server serverConnection;
	//********
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("fxml/serverLoginScreen.fxml"));


		primaryStage.setTitle("(server) Playing word guess!!!");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(20);
		pane.setVgap(20);
		pane.setStyle("-fx-background-color:  #475B5A");
		Scene scene = new Scene(pane, 723, 440);
		pane.add(root, 0, 0);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
