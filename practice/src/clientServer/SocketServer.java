package clientServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer{
    public static void main(String[] args){
        try{
            ArrayList<Socket> sockets = new ArrayList<Socket>();
            ServerSocket server = new ServerSocket(8080);
            System.out.println("Server started on port 8080 !!");
            while(true){
                Socket s = server.accept();
                sockets.add(s);
                Runnable r = new ThreadSockets(s,sockets);
                Thread socketThread = new Thread(r);
                socketThread.start();
            }
        }catch(Exception e){
            System.out.println ("ERROR :: "+e);
        }
    }
}
