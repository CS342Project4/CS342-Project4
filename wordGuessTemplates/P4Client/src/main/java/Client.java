import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class  Client extends Thread{

    StringBuilder tempString;
    Socket socketClient;
    gameInfo game = new gameInfo();
    ObjectOutputStream out;
    ObjectInputStream in;
    int numLetters;
    private Consumer<Serializable> callback;

    Client(Consumer<Serializable> call){

        callback = call;
    }

    public void run() {

        try {
            socketClient= new Socket("127.0.0.1",5556);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
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
            catch(Exception e) {}
        }

    }

    public void send(gameInfo game) throws Exception {

        try {
            //out.writeObject(data);
            out.writeObject(game);
            out.reset();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
