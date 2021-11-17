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

public Socket connetti(){
    System.out.println("CLIENT partito in esecuzione...");
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
    try{
        Thread l = new Thread(new Listener(inDalServer, miosocket));
        l.start();
    System.out.print("Inserisci nome utente: ");
    stringaUtente=tastiera.readLine();
    outVersoServer.writeBytes("A:"+ stringaUtente+'\n');
    System.out.println("inserisci la stringa da trasmettere al server:");
    for(;;){
            //System.out.println(nomeUtente);
            //'P:' manda il messaggio ad un unico client
            //'ALL:' manda il messaggio a tutti
            
            stringaUtente=tastiera.readLine();
            //la spedisco al server
            //System.out.println("... invio la stringa al server e attendo...");
            outVersoServer.writeBytes(stringaUtente+'\n');
            //leggo la risposta del server
        }
        }
            catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione con il server!");
                System.exit(1);

            }
        }

public static void main( String[] args )
{
    Client cliente=new Client();
    cliente.connetti();
    cliente.comunica();

}

}



