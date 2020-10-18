package com.company;

import javax.swing.*;
import java.awt.*;

public class AI {
    JPanel board;
    int aiScore = 0;
    Piece[] aiPieces = new Piece[16];

    public AI (JPanel board) {
        this.board = board;
    }

    public void scanPieces () {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                aiPieces[GUI.boardTracker[i][j].id] = GUI.boardTracker[i][j];
            }
        }
    }

    private void Capture (Piece movingPiece, int xMove, int yMove) {
        Piece checkPiece = GUI.boardTracker[xMove][yMove];

        movingPiece.setVisible(false);
        Component c = board.findComponentAt(movingPiece.locationX*100, movingPiece.locationY*100);
        Container parent = (Container) c;
        parent.add(movingPiece);
        movingPiece.setVisible(true);
        //If the piece belongs to the AI we will add to their score
        if (checkPiece.team == 0) {
            switch (checkPiece.type) {
                case 0:
                    aiScore = aiScore + 1;
                    break;
                case 1:
                    aiScore = aiScore + 2;
                    break;
                case 2:
                    aiScore = aiScore + 3;
                    break;
                case 3:
                    aiScore = aiScore + 4;
                    break;
                case 4:
                    aiScore = aiScore + 5;
                    break;
            }
        }
    }
}
