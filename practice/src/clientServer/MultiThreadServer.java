/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientServer;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author RN
 */
public class MultiThreadServer extends Application {

    //Text area to display contents
    protected TextArea taServer = new TextArea();
    //client number
    private int clientNum = 0;

    private List<Socket> clientList = new ArrayList<>();
    Map<String, Socket> connectedClientMap = new HashMap<>();

    @Override//override start method in the application class
    public void start(Stage primaryStage){
        //create a scene and place it in stage
        Scene scene = new Scene(new ScrollPane(taServer), 400, 200);
        primaryStage.setTitle("MultiThreadServer");//set stage title
        primaryStage.setScene(scene);//place scene in the stage
        primaryStage.setResizable(false);
        primaryStage.show();//display the stage

        new Thread(() ->{
            try{
                //create server socket
                ServerSocket serverSocket = new ServerSocket(7000);
                taServer.appendText("MultiThreadServer started at "+new Date()+"\n");

                while(true){
                    //listen for new connection request
                    Socket socket = serverSocket.accept();

                    //increse client number
                    clientNum++;

                    Platform.runLater(() ->{
                        //display client number
                        taServer.appendText("Client "+clientNum+" connected at "+ new Date()+"\n");

                        //find the client's host name and ip address
                        InetAddress inetAddress = socket.getInetAddress();
                        taServer.appendText("Client "+clientNum+"'s host name is "+inetAddress.getHostName()+"\n");
                        taServer.appendText("Client "+clientNum+"'s IP address is "+inetAddress.getHostAddress()+"\n");
                    });

                    //create and start a new thread for the connection
                    new Thread(new HandleClient(socket, "client"+clientNum)).start();
                }
            }
            catch(IOException ex){
                System.err.println(ex);
            }
        }).start();
    }
    //create thread class for handling new connection
    class HandleClient implements Runnable{
        private Socket socket;//a connected socket
        private String clientName;

        //construct a thread
        public HandleClient(Socket socket, String clientName){
            this.socket = socket;
            clientList.add(socket);
            connectedClientMap.put(clientName, socket);
        }

        //run a thread
        public void run(){
            try{
                //create data input stream and data output stream
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                //continuously serve the client
                while(true){
                    //receive the message from client
                    String messageFromClient = inputFromClient.readUTF();

                    System.out.println("messageFromClient = " + messageFromClient);
                    System.out.println("messageFromClient.startsWith(\"ECHO\") = " + messageFromClient.startsWith("ECHO"));

                    // message to all clients
                    if(messageFromClient.startsWith("ECHO")){
                        for(Socket skt : clientList){
                            System.out.println("sending to "+skt.getInetAddress().getHostAddress()+":"+skt.getPort());
                            DataOutputStream dos = new DataOutputStream(skt.getOutputStream());
                            dos.writeUTF("Message from " + skt.getInetAddress().getHostAddress() + ":" + skt.getPort() + "\n" + messageFromClient);
//                            dos.close();
                        }
                    }
                    // message specific to a client: starts with TOclient(Num)~ ; eg TOclient1~Hello - from this message Hello will be send to client 1;
                    else if(messageFromClient.startsWith("TO")){
                        String toClientName = messageFromClient.substring(2, messageFromClient.indexOf("~"));
                        String realMessage = messageFromClient.substring(messageFromClient.indexOf("~")+1);
                        Socket skt = connectedClientMap.get(toClientName);
                        DataOutputStream dos = new DataOutputStream(skt.getOutputStream());
                        dos.writeUTF("Message from " + skt.getInetAddress().getHostAddress() + ":" + skt.getPort() + "\n" + realMessage);
//                        dos.close();
                    }
                    else{
                        // send message back to client
                        outputToClient.writeUTF(messageFromClient);
                    }

                    Platform.runLater(() ->{
                        taServer.appendText("Message received from client: "+messageFromClient+"\n");
                    });
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
//}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
