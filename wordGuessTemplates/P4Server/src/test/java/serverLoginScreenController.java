import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class serverLoginScreenController {

    int PORT;

    @FXML
    TextField portInTextField;

    public void startServer(MouseEvent mouseEvent) {

        try{
            PORT = Integer.parseInt(portInTextField.getText());

            FXMLLoader loadGameInfoFile = new FXMLLoader(getClass().getResource("fxml/serverInfoScreen.fxml"));
            Parent afterConnection = loadGameInfoFile.load();

            //Get controller of scene2
            ServerInfoScreenController gameInfoControl = loadGameInfoFile.getController();

            gameInfoControl.transferPortNumberAndInitializeServer(this.PORT);

            //Show scene 2 in a new window

            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setHgap(20);
            pane.setVgap(20);
            pane.add(afterConnection, 0, 0);
            pane.setStyle("-fx-background-color: #475B5A");

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(pane));
            stage.show();

        }catch (NumberFormatException | IOException exception){

            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Input is NAN");
            a.show();
        }
    }

}
