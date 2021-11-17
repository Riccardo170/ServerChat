import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Listener implements Runnable{
    BufferedReader indalserver;
    String messaggio;
    Socket miosocket;

    public Listener(BufferedReader indalserver,Socket miosocket){
        this.indalserver=indalserver;
        this.miosocket=miosocket;
    }

    public void run(){
        for(;;){
            try {
                messaggio=indalserver.readLine();
                System.out.println(messaggio + '\n');
                if(messaggio.equals("FINE") || messaggio.equals("STOP")){
                    System.out.println("CLIENT: termina elaborazione e chiude connessione");
                    miosocket.close();      //chiudo applicazione
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    
}
