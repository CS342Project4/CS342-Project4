import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClientInfoScreen {

    Client clientConnection;


    @FXML
    ListView<String> clientlog;
    @FXML Button guessButton;
    @FXML Button guessLetter;
    @FXML TextField guessInput;
    @FXML Button c1;
    @FXML Button c2;
    @FXML Button c3;

    public void transferPortNumberAndIPAddress(String ipAddress, int port) {

        clientConnection = new Client(data -> Platform.runLater(() ->updateClientLog(data.toString())),
        ipAddress,port);
        clientConnection.start();
    }

    public void updateClientLog(String data){
        clientlog.getItems().clear();
        clientlog.getItems().add(data);
    }

    public void onGuessLetterClicked(MouseEvent mouseEvent) {
        clientConnection.game.letterGuess = true;
        clientConnection.game.wordGuess = false;
        System.out.println("debug -> "+clientConnection.game.currentWord);
        clientConnection.game.setLetterInput(guessInput.getText());
        guessInput.clear();
        try {
            System.out.println("Sending " + clientConnection.game.getLetterInput());
            clientConnection.send(clientConnection.game);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void onGuessButtonClicked(MouseEvent mouseEvent) {
        clientConnection.game.letterGuess = false;
        clientConnection.game.wordGuess = true;
        clientConnection.game.setInput(guessInput.getText());
        try {
            clientConnection.send(clientConnection.game);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void onAnimalsClicked(MouseEvent mouseEvent) {
        clientConnection.game.setAnimalSelect(true);
        clientConnection.game.setSuperheroSelect(false);
        clientConnection.game.setCountrySelect(false);
        try {
            clientConnection.send(clientConnection.game);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onCountriesClicked(MouseEvent mouseEvent) {
        clientConnection.game.setAnimalSelect(false);
        clientConnection.game.setSuperheroSelect(false);
        clientConnection.game.setCountrySelect(true);
        try {
            clientConnection.send(clientConnection.game);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onSuperHerosClicked(MouseEvent mouseEvent) {
        clientConnection.game.setAnimalSelect(false);
        clientConnection.game.setSuperheroSelect(true);
        clientConnection.game.setCountrySelect(false);
        try {
            clientConnection.send(clientConnection.game);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}
