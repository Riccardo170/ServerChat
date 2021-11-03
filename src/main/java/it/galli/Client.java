import java.io.*;
import java.net.*;

public class Client {
    String nomeServer ="localhost";
    int portaServer =6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    int i=0;
    


public Socket connetti(){
    System.out.println("2 CLIENT partito in esecuzione...");
    try{
        tastiera= new BufferedReader(new InputStreamReader(System.in));
        miosocket=new Socket(nomeServer,portaServer);
        outVersoServer= new DataOutputStream(miosocket.getOutputStream());
        inDalServer= new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
    }
    catch (UnknownHostException e){
        System.err.println("Host sconosciuto");}
    catch (Exception e){
        System.out.println(e.getMessage());
        System.out.println("Errore durante la connessione!");
        System.exit(1);
    }
return miosocket;
}


public void comunica(){
    for(;;){
        try{
            
            System.out.print("Inserisci nome utente: ");
            stringaUtente=tastiera.readLine();
            outVersoServer.writeBytes(stringaUtente+'\n');
            //System.out.println(nomeUtente);
            System.out.println("utente, inserisci la stringa da trasmettere al server:");
            stringaUtente=tastiera.readLine();
            //la spedisco al server
            System.out.println("5... invio la stringa al server e attendo...");
            outVersoServer.writeBytes(stringaUtente+'\n');
            //leggo la risposta del server
            stringRicevutaDalServer=inDalServer.readLine();
            System.out.println("7... risposta dal server "+'\n'+stringRicevutaDalServer);
            if(stringaUtente.equals("FINE") || stringaUtente.equals("STOP")){
                System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
                miosocket.close();      //chiudo applicazione
                break;
            }
        }
            catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione con il server!");
                System.exit(1);

            }
        }

    }

public static void main( String[] args )
{
    Client cliente=new Client();
    cliente.connetti();
    cliente.comunica();
}

}



