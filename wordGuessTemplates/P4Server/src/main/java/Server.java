import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class Server{

    int count = 1;
    Random rand = new Random();
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    ArrayList<String>countryList = new ArrayList<String>();
    ArrayList<String>animalsList = new ArrayList<String>();
    ArrayList<String>superList = new ArrayList<String>();
    public ArrayList<String>sample = new ArrayList<String>();
    File file = new File("country.txt");
    Scanner sc = new Scanner(file);
    File file2 = new File("animals.txt");
    Scanner sc2 = new Scanner(file2);
    File file3 = new File("super.txt");
    Scanner sc3 = new Scanner(file3);
    TheServer server;
    String choosenWord;
    int randomNum;
    private Consumer<Serializable> callback;
    gameInfo game = new gameInfo();

    Server(Consumer<Serializable> call) throws FileNotFoundException {

        callback = call;
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread{

        public void run() {
                while(sc.hasNextLine()){
                    countryList.add(sc.nextLine());
                }
                while (sc2.hasNextLine()){
                    animalsList.add(sc2.nextLine());
                }
                while(sc3.hasNextLine()){
                    superList.add(sc3.nextLine());
                }
            try(ServerSocket mysocket = new ServerSocket(5556);){
                System.out.println("Server is waiting for a client!");
                callback.accept("Server launched, waiting for clients to connect !");
                while(true) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept("client has connected to server: " + "client #" + count);
                    clients.add(c);
                    c.start();

                    count++;

                }
            }//end of try
            catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }//end of while
    }


    class ClientThread extends Thread{


        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        public void run(){

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            while(true) {
                try {
                    game = (gameInfo)in.readObject();
                  //  callback.accept("Client said "+game.input);
                    if(game.countrySelect == true){
                        randomNum = rand.nextInt(countryList.size());
                        System.out.println("the Word at "+randomNum+" is "+countryList.get(randomNum));
                        game.setCurrentWord(countryList.get(randomNum));
                        choosenWord = countryList.get(randomNum);
                        send(game);
                        //need a game loop here
                       // game = (gameInfo)in.readObject();
                        while(game.correctGuess!=true){
                            game = (gameInfo)in.readObject();
                            if(game.letterGuess==true){
                                game.letterGuess = false;
                                String tempLetter = game.letterInput;
                                for(int i=0;i<choosenWord.length();i++){
                                    if(choosenWord.charAt(i) == tempLetter.charAt(0)){
                                        System.out.println("letter location -> "+i);
                                        game.letterLocation = i;
                                        game.currentWord = choosenWord;
                                        send(game);
                                        break;
                                    }
                                }
                            }
                            else if(game.wordGuess==true){

                            }
                        }
                    }
                    if(game.animalSelect == true){
                        randomNum = rand.nextInt(animalsList.size());
                        System.out.println("the Word at "+randomNum+" is "+animalsList.get(randomNum));
                        game.setCurrentWord(animalsList.get(randomNum));
                        send(game);
                    }
                    if(game.SuperheroSelect == true){
                        randomNum = rand.nextInt(superList.size());
                        System.out.println("the Word at "+randomNum+" is "+superList.get(randomNum));
                        game.setCurrentWord(superList.get(randomNum));
                        send(game);
                    }

                   /* char[] arr = new char[game.input.length()];
                    for(int i=0;i<game.input.length();i++){
                        arr[i] = game.input.charAt(i);
                        System.out.println(arr[i]);
                    }
                    if(game.letterGuess==true){
                       // System.out.println("h");

                    }else if(game.wordGuess==true){
                      // System.out.println("f");
                    } */
                }
                catch(Exception e) {
                    e.printStackTrace();
                    callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
                    clients.remove(this);
                    break;
                }
            }
        }//end of run

        public void send(gameInfo game) throws Exception{
            out.writeObject(game);
            out.reset();
        }


    }//end of client thread
}






