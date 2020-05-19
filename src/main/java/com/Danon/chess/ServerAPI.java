package com.Danon.chess;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerAPI extends Thread{
    private static boolean IsServer = false;
    private static Socket socket;
    public static DataOutputStream out;
    public static DataInputStream in;
    public static boolean IsGameStarted = false;
    public static void SendMove(Vector2 From, Vector2 To){
        try {
            out.writeUTF("Move");
            out.writeInt(From.x);
            out.writeInt(From.y);
            out.writeInt(To.x);
            out.writeInt(To.y);
        } catch (IOException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ReceiveMove(){
        try{
            Vector2 From = new Vector2(in.readInt(),in.readInt());
            Vector2 To = new Vector2(in.readInt(),in.readInt());
            if(Main.PiecesOnBoard[To.x][To.y].rank.getCode() == "King"){
                if(Main.PiecesOnBoard[From.x][From.y].color == Main.PlayerColor.Black.getValue())
                    Main.Winner.setText("BLACK WINS");
                    Main.EndGame();
            }
            Main.PiecesOnBoard[To.x][To.y].Update(Main.PiecesOnBoard[From.x][From.y]);
            Main.PiecesOnBoard[From.x][From.y].Update(new Piece(Piece.Rank.Empty, 0, 0));
            Main.PiecesOnBoard[From.x][From.y].button.setVisible(false);
            Main.PiecesOnBoard[To.x][To.y].button.setVisible(true);
            Main.IsPlayerTurn = true;
            Main.PlayerTurnInfo.setText("YOUR TURN");
        }catch (IOException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
         try(ServerSocket serverSocket = new ServerSocket(9991)) {
            IsServer = true;
            socket = serverSocket.accept();
            System.out.println("Connected");
            
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("DataOutputStream  created");

            in = new DataInputStream(socket.getInputStream());
            System.out.println("DataInputStream created");
             
            Main.StartGame("WHITE");
                while(!socket.isClosed()){
                    System.out.println("Server reading from channel");            
                    String entry = in.readUTF();
                    switch(entry){
                        case "Move": ReceiveMove(); break;
                    }
                    out.flush();    
                }
         } catch (IOException ex) {
            if(!IsServer){
            try(Socket Socket = new Socket("localhost", 9991)) {
                socket = Socket;
                System.out.println("Client connected to socket.");
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                Main.StartGame("BLACK");
                while(!socket.isClosed()){
                    System.out.println("Reading from channel");            
                    String entry = in.readUTF();
                    switch(entry){
                        case "Move": ReceiveMove(); break;
                    }
                    out.flush();    
                }
            } catch (IOException ex1) {
                 Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex1);
             }
            }
                System.out.println("Client disconnected");
                System.out.println("Closing connections & channels.");
             try {
                 in.close();
                 out.close();
                 socket.close();
             } catch (IOException ex1) {
                 Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex1);
             }
                System.out.println("Closing connections & channels - DONE.");
        }
    }
}
