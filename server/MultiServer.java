import java.net.*;

public class MultiServer {
    public void start(){
        contenitore c = new contenitore();
        try{
            ServerSocket serverSocket = new ServerSocket(6789);
            for(;;){
                System.out.println("1 Server in attesa...");
                Socket socket = serverSocket.accept();
                System.out.println("3 Server socket"+socket);
                ServerThread serverThread= new ServerThread(socket, serverSocket,c);
                serverThread.start();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
    public static void main(String[] args){
        MultiServer tcpServer = new MultiServer();
        tcpServer.start();
    }

    
}