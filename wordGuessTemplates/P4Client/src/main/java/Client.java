import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class  Client extends Thread{


    StringBuilder tempString;
    Socket socketClient;
    public gameInfo game;
    ObjectOutputStream out;
    ObjectInputStream in;
    int numLetters;
    private Consumer<Serializable> callback;
    private String ipAddress;
    private int PORT;

    Client(Consumer<Serializable> call, String ipAddress, int PORT){
        callback = call;
        this.ipAddress = ipAddress;
        this.PORT = PORT;
    }

    public void run() {

        try {
            socketClient= new Socket(ipAddress,PORT);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
            game = new gameInfo();
        }
        catch(Exception e) {e.printStackTrace();}

        while(true) {

            try {
                game = (gameInfo)in.readObject();
                numLetters = game.currentWord.length();
                callback.accept("the word that you got has "+game.currentWord.length()+" of letters ");
                tempString = new StringBuilder(game.currentWord);
                for(int i=0;i<tempString.length();i++){
                    tempString.setCharAt(i,'*');
                }
                callback.accept("Decoded so far -> "+tempString);
                while(game.correctGuess!=true){
                    game = (gameInfo)in.readObject();
                    Character c = game.currentWord.charAt(game.letterLocation);
                    tempString.setCharAt(game.letterLocation,c);
                    callback.accept("Decoded so far -> "+tempString);
                }


            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void send(gameInfo game) throws Exception {

        try {

            out.writeObject(game);
            out.reset();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
