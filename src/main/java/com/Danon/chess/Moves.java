package com.Danon.chess;

import java.awt.Color;
import java.util.ArrayList;

public class Moves {
    public static final ArrayList<Piece> Selected = new ArrayList<Piece>();
    
    public static void CheckMoves(Piece piece){
        Vector2 pos = Main.FindPiece(piece);
        switch(piece.rank){
            case Pawn:{ 
                    for(int i = 1;i<=2;i++)
                        if(Main.PiecesOnBoard[pos.x + (i*piece.color)][pos.y].Activate() < 1)
                            Selected.add(Main.PiecesOnBoard[pos.x + (i*piece.color)][pos.y]);
                        else break;
                break;
                }
            
            case Knight:{
                for(int i = -1;i<2;i+=2){
                    for(int j = -1;j<2;j+=2 ){
                        if(checkBounds(pos.x + i) && checkBounds(pos.y + 2*j))
                            if(Main.PiecesOnBoard[pos.x + i][pos.y + 2*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i][pos.y + 2*j]);
                        if(checkBounds(pos.x + 2*i) && checkBounds(pos.y + j))
                            if(Main.PiecesOnBoard[pos.x + 2*i][pos.y + j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + 2*i][pos.y + j]);
                    }
                }
                break;
            }
            
            case Rook:{
                Vector2 LockedDirectionsXAxis = new Vector2(0,0);
                Vector2 LockedDirectionsYAxis = new Vector2(0,0);
                for(int i = 1; i<8;i++){
                    for(int j = -1;j<2;j+=2){
                    if(checkBounds(pos.x + i*j) && checkBounds(pos.y) && LockedDirectionsXAxis.x != j && LockedDirectionsXAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x + i*j][pos.y].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i*j][pos.y]);
                            else{
                                if(j < 0) LockedDirectionsXAxis.x = j;
                                else LockedDirectionsXAxis.y = j;
                            }
                    if(checkBounds(pos.x) && checkBounds(pos.y + i*j) && LockedDirectionsYAxis.x != j && LockedDirectionsYAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x][pos.y + i*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x][pos.y + i*j]);
                            else{
                                if(j < 0) LockedDirectionsYAxis.x = j;
                                else LockedDirectionsYAxis.y = j;
                            }
                    }
                }
                break;
            }
            
            case Bishop:{
                Vector2 LockedDirectionsXAxis = new Vector2(0,0);
                Vector2 LockedDirectionsYAxis = new Vector2(0,0);
                for(int i = 1; i<8;i++){
                    for(int j = -1;j<2;j+=2){
                    if(checkBounds(pos.x + i*j) && checkBounds(pos.y + i*j) && LockedDirectionsXAxis.x != j && LockedDirectionsXAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x + i*j][pos.y + i*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i*j][pos.y + i*j]);
                            else{
                                if(j < 0) LockedDirectionsXAxis.x = j;
                                else LockedDirectionsXAxis.y = j;
                            }
                    if(checkBounds(pos.x + i*j) && checkBounds(pos.y - i*j) && LockedDirectionsYAxis.x != j && LockedDirectionsYAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x + i*j][pos.y - i*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i*j][pos.y - i*j]);
                            else{
                                if(j < 0) LockedDirectionsYAxis.x = j;
                                else LockedDirectionsYAxis.y = j;
                            }
                    }
                }
                break;
            }
            
            case Queen:{
                Vector2 LockedDirectionsXAxis = new Vector2(0,0);
                Vector2 LockedDirectionsYAxis = new Vector2(0,0);
                Vector2 LockedSideWaysXAxis = new Vector2(0,0);
                Vector2 LockedSideWaysYAxis = new Vector2(0,0);
                for(int i = 1; i<8;i++){
                    for(int j = -1;j<2;j+=2){
                    if(checkBounds(pos.x + i*j) && checkBounds(pos.y) && LockedDirectionsXAxis.x != j && LockedDirectionsXAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x + i*j][pos.y].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i*j][pos.y]);
                            else{
                                if(j < 0) LockedDirectionsXAxis.x = j;
                                else LockedDirectionsXAxis.y = j;
                            }
                    if(checkBounds(pos.x) && checkBounds(pos.y + i*j) && LockedDirectionsYAxis.x != j && LockedDirectionsYAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x][pos.y + i*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x][pos.y + i*j]);
                            else{
                                if(j < 0) LockedDirectionsYAxis.x = j;
                                else LockedDirectionsYAxis.y = j;
                            }
                    if(checkBounds(pos.x + i*j) && checkBounds(pos.y + i*j) && LockedSideWaysXAxis.x != j && LockedSideWaysXAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x + i*j][pos.y + i*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i*j][pos.y + i*j]);
                            else{
                                if(j < 0) LockedSideWaysXAxis.x = j;
                                else LockedSideWaysXAxis.y = j;
                            }
                    if(checkBounds(pos.x + i*j) && checkBounds(pos.y - i*j) && LockedSideWaysYAxis.x != j && LockedSideWaysYAxis.y != j)
                            if(Main.PiecesOnBoard[pos.x + i*j][pos.y - i*j].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + i*j][pos.y - i*j]);
                            else{
                                if(j < 0) LockedSideWaysYAxis.x = j;
                                else LockedSideWaysYAxis.y = j;
                            }
                    }
                }
                break;
            }
            
            case King:{
                for(int i = -1;i<2;i++)
                    for(int j = -1;j<2;j++){
                        if(checkBounds(pos.x + j) && checkBounds(pos.y + i))
                            if(Main.PiecesOnBoard[pos.x + j][pos.y + i].Activate() < 1)
                                Selected.add(Main.PiecesOnBoard[pos.x + j][pos.y + i]);
                    }
                break;
            }
        }
    }
    
    private static boolean checkBounds(int value){
        return value<8&&value>-1;
    }
    
    public static void Deselect(){
        Selected.forEach((piece) -> {
            switch (piece.color) {
                case 0:
                    piece.button.setVisible(false);
                    break;
                case -1:
                    piece.button.setBackground(Color.white);
                    break;
                case 1:
                    piece.button.setBackground(Color.black);
                    break;
                default:
                    break;
            }
        });
        Selected.clear();
    }
}
