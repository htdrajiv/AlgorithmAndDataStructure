/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientServer;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author RN
 */
public class Client extends Application {
    //input and output streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    int clientNum = 0;

    //overrides start method in application class
    public void start(Stage primaryStage){
        //panel to hold the label and text field
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5,5,5,5));
        paneForTextField.setStyle("-fx-border-color: blue");
        paneForTextField.setLeft(new Label("Enter your message: "));

        TextField tfForMessage = new TextField();
        tfForMessage.setAlignment(Pos.CENTER_LEFT);
        paneForTextField.setCenter(tfForMessage);

        BorderPane mainPane = new BorderPane();
        //text area to display contents
        TextArea taForContent = new TextArea();
        taForContent.setEditable(false);
        mainPane.setCenter(new ScrollPane(taForContent));
        mainPane.setTop(paneForTextField);

        //create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 400, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        tfForMessage.setOnAction(e ->{
            //get message from textfield
            String messageToServer = tfForMessage.getText();
            try {
                //send the message to the server
                toServer.writeUTF(messageToServer);
                toServer.flush();

                // added: clear text field after sending message
                tfForMessage.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        try{
            //create a socket to connect to the server
            Socket socket = new Socket("localhost", 7000);

            //create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            //create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException ex){
            taForContent.appendText(ex.toString()+"\n");
        }

        // added: in thread to continuously receive message and update client on new new message.
        new Thread(() -> {
            try {
                while(true) {
                    //get message from the server
                    String messageFromServer = fromServer.readUTF();

                    //display to the text area
                    taForContent.appendText("Message received from server: " + messageFromServer + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}