
package com.Danon.chess;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public final class Main extends javax.swing.JFrame {
    public enum PlayerColor{ Black(-1), Null(0),White(1);
    private final int num;
    PlayerColor(int num){this.num = num;}
    public int getValue(){ return num;}
    }
    
    public static PlayerColor PlayerColor_ = PlayerColor.Null;
    public static boolean IsPlayerTurn = false;
    public static Piece SelectedPiece = null;
    public static Piece[][] PiecesOnBoard = new Piece[8][8];
    
    private final JLayeredPane lpane = new JLayeredPane();
    private final JPanel InfoPanel = new JPanel();
    private final JPanel NavMenu = new JPanel();
    private final JPanel Board = new Board();
    private final JPanel Field = new JPanel();
    private final JLabel Label = new JLabel(" You playing -");
    private final JLabel PlayerColorInfo = new JLabel("WHITE");
    private final JLabel PlayerTurnInfo = new JLabel("  ENEMY TURN");
    
    public Main() {
        InitForm();
    }
    
    public static Vector2 FindPiece(Piece piece){
        for(int i = 0;i<8;i++){
            for(int j = 0; j < 8;j++){
                if (PiecesOnBoard[i][j] == piece) return new Vector2(i,j);
            }
        }
        return new Vector2(-1,-1);
    }
    
    public void InitForm(){
        setTitle("Crappy Chess");
        setPreferredSize(new Dimension(770, 750));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 850, 700);
        InfoPanel.setBounds(0,0,650,50);
        InfoPanel.setBackground(java.awt.Color.CYAN);
        NavMenu.setBounds(650,0,100,700);
        NavMenu.setBackground(java.awt.Color.CYAN);
        Board.setBounds(1, 49, 650, 650);
        Board.setOpaque(true);
        Field.setBounds(32, 80, 585, 585);
        Field.setOpaque(false);
        Field.setLayout(new GridLayout(8,8));
        for(int i = 0;i<8;i++)
            for(int j = 0;j<8;j++){
                if(i == 1 || i == 6) PiecesOnBoard[i][j] = new Piece(Piece.Rank.Pawn, i);
                else if ((i == 0 || i == 7) && (j == 0 || j==7)) PiecesOnBoard[i][j] = new Piece(Piece.Rank.Rook, i);
                else if ((i == 0 || i == 7) && (j == 1 || j==6)) PiecesOnBoard[i][j] = new Piece(Piece.Rank.Knight, i);
                else if ((i == 0 || i == 7) && (j == 2 || j==5)) PiecesOnBoard[i][j] = new Piece(Piece.Rank.Bishop, i);
                else if ((i == 0 || i == 7) && j == 3) PiecesOnBoard[i][j] = new Piece(Piece.Rank.King, i);
                else if ((i == 0 || i == 7) && j == 4) PiecesOnBoard[i][j] = new Piece(Piece.Rank.Queen ,i);
                else PiecesOnBoard[i][j] = new Piece(Piece.Rank.Empty, i);
                Field.add(PiecesOnBoard[i][j].button);
                if(i < 2 || i > 5) PiecesOnBoard[i][j].button.setVisible(true);
            }
        InfoPanel.setLayout(new GridLayout(1,3));
        PlayerColorInfo.setFont(new Font("Consolas",Font.PLAIN, 25));
        Label.setFont(new Font("Consolas",Font.PLAIN, 25));
        PlayerTurnInfo.setFont(new Font("Consolas",Font.PLAIN, 25));
        InfoPanel.add(Label);
        InfoPanel.add(PlayerColorInfo);
        InfoPanel.add(PlayerTurnInfo);
        lpane.add(Board, new Integer(0));
        lpane.add(InfoPanel, new Integer(1));
        lpane.add(NavMenu, new Integer(1));
        lpane.add(Field, new Integer(1));
        pack();
        setVisible(true);
    }
    
    public static void main(String Args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
}
