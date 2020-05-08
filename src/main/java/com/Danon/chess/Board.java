package com.Danon.chess;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class Board extends JPanel {
    Image img = new ImageIcon("src/main/resources/Board.jpg").getImage();
    public Board(){
    }
    
    @Override
    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img,0,0,650,650,this);
    }
}
