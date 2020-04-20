import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;


public class ServerInfoScreenController {

    Server serverConnection;

    @FXML
    ListView serverInfo;

    public void transferPortNumberAndInitializeServer(int port) throws FileNotFoundException {
        serverConnection = new Server(
                data -> Platform.runLater(() -> serverInfo.getItems().add(data.toString())),
                port
                );
    }

}
