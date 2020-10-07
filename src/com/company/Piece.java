package com.company;

import javax.swing.*;

public class Piece extends JLabel {
    int type; //Type of piece 0=pawn,1=rook,2=knight,3=bishop,4=queen,5=king
    int locationX;
    int locationY;
    int team; //0=white, 1=black

    public Piece(int type, int locationX, int locationY, int team) {
    }
}
