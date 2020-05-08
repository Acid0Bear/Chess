
package com.Danon.chess;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public final class Main extends javax.swing.JFrame {
    public static Piece SelectedPiece = null;
    public static Piece[][] PiecesOnBoard = new Piece[8][8];
    private final JLayeredPane lpane = new JLayeredPane();
    private final JPanel Board = new Board();
    private final JPanel Field = new JPanel();
    
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
        setPreferredSize(new Dimension(750, 700));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 650, 650);
        Board.setBounds(1, 2, 650, 650);
        Board.setOpaque(true);
        Field.setBackground(java.awt.Color.GREEN);
        Field.setBounds(32, 33, 585, 585);
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
        lpane.add(Board, new Integer(0));
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
