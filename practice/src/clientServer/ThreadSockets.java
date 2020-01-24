package clientServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class ThreadSockets implements Runnable{
    private Socket s;
    private ArrayList<Socket> socketList = new ArrayList<Socket>();
    public ThreadSockets(Socket incoming,ArrayList<Socket> sockets){
        s = incoming;
        socketList = sockets;
    }
    public void run(){
        try{
            InputStream input = s.getInputStream();
            OutputStream output = s.getOutputStream();
            PrintWriter printWriter = new PrintWriter(output,true);
            System.out.println("connected to a client "+s.getInetAddress()+" with port number "+s.getPort());
            printWriter.println("Port :: "+s.getPort()+" Connection to server established !!");
            printWriter.println("Connected Clients are :: ");
            for(int i=0;i<socketList.size();i++){
                Socket skt = socketList.get(i);
                printWriter.println(i + ". " +skt.getPort());
            }
            printWriter.println("To send message to all connected clients, type ECHO and write your message.");
            printWriter.println("To send message to a particular client, type client's port number along with '~' then write your message. (1245~your message) ");
            printWriter.println("To get list of all connected clients, type CLIENTS and send.");
            printWriter.println("Enter BYE to exit");
            for(int i=0;i<socketList.size();i++){
                Socket skt = socketList.get(i);
                if(skt!=s){
                    OutputStream otemp = skt.getOutputStream();
                    PrintWriter pTemp = new PrintWriter(otemp,true);
                    pTemp.println(s.getPort()+" joined the room");
                }
            }

            Scanner scanner = new Scanner(input);
            Boolean done = false;
            while(!done && scanner.hasNextLine()){
                String line = scanner.nextLine();
                String sendToClient = "";
                if(line.equals("CLIENTS")){
                    printWriter.println("Connected Clients are :: ");
                    for(int i=0;i<socketList.size();i++){
                        Socket skt = socketList.get(i);
                        printWriter.println(i + ". " +skt.getPort());
                    }
                }
                else if(line.contains("ECHO")){
                    for(int i=0;i<socketList.size();i++){
                        Socket skt = socketList.get(i);
                        if(skt!=s){
                            OutputStream otemp = skt.getOutputStream();
                            PrintWriter pTemp = new PrintWriter(otemp,true);
                            pTemp.println(s.getPort()+" says to all :: "+line.replace("ECHO",""));
                        }
                    }
                }
                else if(line.contains("~")){
                    String[] tempList = line.split("\\~");
                    sendToClient = tempList[0];
                    for(int i=0;i<socketList.size();i++){
                        Socket skt = socketList.get(i);
                        DataOutputStream otemp = new DataOutputStream(skt.getOutputStream());
                        PrintWriter pTemp = new PrintWriter(otemp,true);
                        if((""+skt.getPort()).trim().equals(sendToClient.trim())){
                            otemp.writeUTF(s.getPort()+" says :: "+tempList[1]);
                            pTemp.println(s.getPort()+" says :: "+tempList[1]);
                            break;
                        }
                    }
                }
                if(line.trim().equals("BYE")){
                    for(int i=0;i<socketList.size();i++){
                        Socket skt = socketList.get(i);
                        if(skt!=s){
                            DataOutputStream otemp = new DataOutputStream(skt.getOutputStream());
                            PrintWriter pTemp = new PrintWriter(otemp,true);
                            otemp.writeUTF(s.getPort()+" left the room");
                            pTemp.println(s.getPort()+" left the room");
                        }
                    }
                    socketList.remove(s);
                    done = true;
                }
            }
        } catch(IOException e){
            System.out.println("EXCEPTION :: "+e);
        } finally{
            try{ s.close(); }catch(IOException e){System.out.println("Exception in closing connection ");}
        }
    }
}
