package com.Danon.chess;

import java.awt.Color;
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
    private boolean IsEnemy;
    public boolean IsActivated;
    private ActionListener actionListener = new DefaultActionListener(this);
    
    public static class DefaultActionListener implements ActionListener {
        Piece piece = null;
        
        public DefaultActionListener(Piece target){
            piece = target;
        };
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!Main.IsPlayerTurn || Main.PlayerColor_.getValue() != this.piece.color) return;
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
            if(Main.SelectedPiece == null || !piece.IsActivated) return;
            if(this.piece.rank.getCode() == "King"){
                if(Main.SelectedPiece.color == Main.PlayerColor.Black.getValue())
                    Main.Winner.setText("BLACK WINS");
                Main.EndGame();
            }
            this.piece.Update(Main.SelectedPiece);
            Main.SelectedPiece.Update(new Piece(Piece.Rank.Empty, 0, 0));
            Main.SelectedPiece.button.setVisible(false);
            Main.server.SendMove(Main.FindPiece(Main.SelectedPiece),Main.FindPiece(this.piece));
            Main.SelectedPiece = null;
            Main.IsPlayerTurn = false;
            Main.PlayerTurnInfo.setText("ENEMY TURN");
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
        this.IsEnemy = target.IsEnemy;
        if(target.IsEnemy)
            actionListener = new EmptyPieceActionListener(this);
        else
            actionListener = new DefaultActionListener(this);
        this.button.addActionListener(actionListener);
    }
    
    public int Activate(){
        if(!IsEnemy) return 1;
        else if (color != 0){
            this.button.setBackground(Color.red);
            IsActivated = true;
            return -1;
        }
        else
            this.button.setBackground(Color.blue);
        this.button.setVisible(true);
        IsActivated = true;
        return 0;
    }
    
    public Piece(Piece.Rank type, int Color, int PlayerColor){
        this.rank = type;
        button = new JButton(this.rank.getCode());
        if(type != Rank.Empty && Color < 2) {button.setBackground(java.awt.Color.black);
                                                   button.setForeground(java.awt.Color.white);
                                                   boolean IsEnemy = PlayerColor == -1;
                                                   if(!IsEnemy)
                                                   actionListener = new DefaultActionListener(this);
                                                   else
                                                   actionListener = new EmptyPieceActionListener(this);
                                                   button.addActionListener(actionListener);
                                                   this.IsEnemy = IsEnemy;
                                                   this.color = 1;}
        else if (type != Rank.Empty && Color > 5) {button.setBackground(java.awt.Color.white);
                                                   button.setForeground(java.awt.Color.black);
                                                   boolean IsEnemy = PlayerColor == 1;
                                                   if(!IsEnemy)
                                                   actionListener = new DefaultActionListener(this);
                                                   else
                                                   actionListener = new EmptyPieceActionListener(this);
                                                   button.addActionListener(actionListener);
                                                   this.IsEnemy = IsEnemy;
                                                   this.color = -1;}
        else if (type == Rank.Empty) {button.setBackground(java.awt.Color.blue);
                                      actionListener = new EmptyPieceActionListener(this);
                                      button.addActionListener(actionListener);
                                      this.IsEnemy = true;}
        button.setVisible(false);
    }
}
