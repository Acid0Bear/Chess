package com.Danon.chess;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Piece {
    public static enum Rank {Empty(""),Pawn("Pawn"), Rook("Rook"), Knight("Knight"), Bishop("Bishop"), Queen("Queen"), King("King");
    private final String name;
    Rank(String name){
        this.name = name;
    }
    public String getCode(){ return name;}};
    
    public Rank rank = null;
    public int color = 0;
    public JButton button;
    private ActionListener actionListener = new DefaultActionListener(this);
    
    public static class DefaultActionListener implements ActionListener {
        Piece piece = null;
        
        public DefaultActionListener(Piece target){
            piece = target;
        };
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Moves.Deselect();
            if(Main.SelectedPiece == piece) {Main.SelectedPiece = null; return;}
            Main.SelectedPiece = piece;
            Moves.CheckMoves(piece);
        }
    }
    
    public static class EmptyPieceActionListener implements ActionListener {
        public Piece piece = null;
        
        public EmptyPieceActionListener(Piece target){
            piece = target;
        };
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Main.SelectedPiece == null) return;
            this.piece.Update(Main.SelectedPiece);
            Main.SelectedPiece.Update(new Piece(Piece.Rank.Empty, 0));
            Main.SelectedPiece.button.setVisible(false);
            Main.SelectedPiece = null;
            Moves.Deselect();
        }
    }
    
    public void Update(Piece target){
        this.button.setBackground(target.button.getBackground());
        this.button.removeActionListener(actionListener);
        this.button.setForeground(target.button.getForeground());
        this.button.setText(target.button.getText());
        this.color = target.color;
        this.rank = target.rank;
        if(this.color == 0)
            actionListener = new EmptyPieceActionListener(this);
        else
            actionListener = new DefaultActionListener(this);
        this.button.addActionListener(actionListener);
    }
    
    public int Activate(){
        if(this.color != 0) return 1;
        this.button.setVisible(true);
        return 0;
    }
    
    public Piece(Piece.Rank type, int Color){
        this.rank = type;
        button = new JButton(this.rank.getCode());
        if(type != Rank.Empty && Color < 2) {button.setBackground(java.awt.Color.black); 
                                             button.setForeground(java.awt.Color.white);
                                             actionListener = new DefaultActionListener(this);
                                             button.addActionListener(actionListener);
                                             this.color = 1;}
        else if (type != Rank.Empty && Color > 5) {button.setBackground(java.awt.Color.white);
                                                   actionListener = new DefaultActionListener(this);
                                                   button.addActionListener(actionListener);
                                                   this.color = -1;}
        else if (type == Rank.Empty) {button.setBackground(java.awt.Color.blue);
                                      actionListener = new EmptyPieceActionListener(this);
                                      button.addActionListener(actionListener);}
        button.setVisible(false);
    }
}
