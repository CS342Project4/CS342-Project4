import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ClientWelcomeScreen {

    @FXML TextField ipAddress;
    @FXML TextField portIdField;
    int PORT;

    public void connectClient(MouseEvent mouseEvent) {

        if (ipAddress.getText().length() == 0) {
          Alert alert = new Alert(Alert.AlertType.ERROR);

          // set content text
          alert.setContentText("Please Enter IP Address to connect!");

          // show the dialog
          alert.show();
        }
        else{
            try{

                PORT = Integer.parseInt( portIdField.getText());
                FXMLLoader loadGameInfoFile = new FXMLLoader(getClass().getResource("clientInfoScreen.fxml"));
                Parent afterConnection = loadGameInfoFile.load();

                //Get controller of scene2
                ClientInfoScreen clientInfoScreen = loadGameInfoFile.getController();

                clientInfoScreen.transferPortNumberAndIPAddress(ipAddress.getText(),this.PORT);

                GridPane pane = new GridPane();
                pane.setAlignment(Pos.CENTER);
                pane.setHgap(20);
                pane.setVgap(20);
                pane.add(afterConnection, 0, 0);
                pane.setStyle("-fx-background-color: #f16350");
                Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(pane, 960, 540));
                stage.show();

            } catch (NumberFormatException | IOException exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                // set content text
                alert.setContentText("Port Input NAN");

                // show the dialog
                alert.show();
            }

        }

    }
}
