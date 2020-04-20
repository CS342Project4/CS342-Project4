import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
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
		// TODO Auto-generated method stub
		//****
		serverConnection = new Server(data->{
			Platform.runLater(()->{
				serverlog.getItems().add(data.toString());
			});
		});
		//****


		primaryStage.setTitle("(server) Playing word guess!!!");
		BorderPane bp = new BorderPane();
		serverlog = new ListView<String>();
		bp.setCenter(serverlog);
		bp.setPadding(new Insets(80));
		bp.setStyle("-fx-background-color: crimson");
		Scene scene = new Scene(bp,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
