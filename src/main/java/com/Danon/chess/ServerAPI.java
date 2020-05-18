package com.Danon.chess;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerAPI {
    public static void Connect(){
         try(ServerSocket serverSocket = new ServerSocket(9991)) {
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Connected?");
         } catch (IOException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String Args[]){
        Connect();
    }
}
