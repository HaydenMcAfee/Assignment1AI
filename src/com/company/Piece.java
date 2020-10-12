package com.company;

import javax.swing.*;

public class Piece extends JLabel {
    static Piece [][] boardtracker;
    int type; //Type of piece 0=pawn,1=rook,2=knight,3=bishop,4=queen,5=king
    int locationX;
    int locationY;
    int team; //0=white, 1=black

    public Piece(int type, int locationX, int locationY, int team) {
        this.type = type;
        this.locationX = locationX;
        this.locationY = locationY;
        this.team = team;
    }

    public boolean checkMovement(int xMove, int yMove) {
        switch (type) {
            case 0: if(checkPawn(xMove, yMove)){return true;}
                    break;
            case 1: if(checkRook(xMove, yMove)){return true;}
                    break;
            case 2: if (checkKnight(xMove, yMove)){return true;}
                    break;
            case 3: if (checkBishop(xMove, yMove)){return true;}
                    break;
            case 4: if (checkQueen(xMove, yMove)){return true;}
                    break;
            case 5: if (checkKing(xMove, yMove)){return true;}
                    break;
        }
        return false;
    }

    public boolean checkPawn(int xMove, int yMove) {
        if (team == 1) {
            if (yMove - locationY > 1) {
                return false;
            }
            if (xMove - locationX != 0) {
                if (boardtracker[xMove][yMove] == null || boardtracker[xMove][yMove].team == team)
                    return false;
            }
        }
        else {
            if (locationY - yMove > 1) {
                return false;
            }
            if (locationX - xMove != 0) {
                if (boardtracker[xMove][yMove] == null || boardtracker[xMove][yMove].team == team)
                    return false;
            }
        }
        boardtracker[locationX][locationY] = null;
        return true;
    }

    public boolean checkRook(int xMove, int yMove) {
        if (xMove != locationX && yMove == locationY) {
            if (xMove > locationX) {
                for (int i = locationX+1; i <= xMove + locationX && i < 8; i++) {
                    if(boardtracker[i][locationY] != null){
                         Piece blockingPiece = boardtracker[i][locationY];
                         if (blockingPiece.team == team) {
                             return false;
                         }
                    }
                }
                boardtracker[locationX][locationY] = null;
                return true;
            }
            else {
                for (int i = locationX-1; i >= xMove && i >= 0; i--){
                    if(boardtracker[i][locationY] != null){
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                boardtracker[locationX][locationY] = null;
                return true;
            }
        }
        else if (yMove != locationY && xMove == locationX) {
            if (yMove > locationY) {
                for (int i = locationY+1; i <= yMove + locationY && i < 8; i++) {
                    if(boardtracker[locationX][i] != null){
                        Piece blockingPiece = boardtracker[locationX][i];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                boardtracker[locationX][locationY] = null;
                return true;
            }
            else {
                System.out.println(yMove);
                System.out.println(locationY);
                for (int i = locationY-1; i >= yMove && i >= 0; i--){
                    if(boardtracker[locationX][i] != null){
                        Piece blockingPiece = boardtracker[locationX][i];
                        if (blockingPiece.team == team) {
                            System.out.println("trace");
                            return false;
                        }
                    }
                }
                boardtracker[locationX][locationY] = null;
                return true;
            }
        }
        return false;
    }

    public boolean checkKnight(int xMove, int yMove) {
        int xDif = 0;
        int yDif = 0;

        if (xMove > locationX) {
            xDif = xMove - locationX;
        }
        else {
            xDif = locationX - xMove;
        }
        if (yMove > locationY) {
            yDif = yMove - locationY;
        }
        else {
            yDif = locationY - yMove;
        }
        System.out.println("-----------Move----------");
        System.out.println(xMove);
        System.out.println(locationX);
        System.out.println(xDif);
        System.out.println(yMove);
        System.out.println(locationY);
        System.out.println(yDif);
        if (xDif != 0) {
            //Check if our move is valid
            if (xDif == 1) {
                if (yDif == 2) {
                    //Check if our move is blocked by our team
                    if (boardtracker[xMove][yMove] != null && boardtracker[xMove][yMove].team == team){
                        return false;
                    }
                    else {
                        boardtracker[locationX][locationY] = null;
                        return true;
                    }
                }
            }
            //Check if our move is valid
            if (xDif == 2) {
                if (yDif == 1) {
                    //Check if our move is blocked by our team
                    if (boardtracker[xMove][yMove] != null && boardtracker[xMove][yMove].team == team){
                        return false;
                    }
                    else {
                        boardtracker[locationX][locationY] = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkBishop(int xMove, int yMove) {
        int xDif = 0;
        int yDif = 0;

        if (xMove > locationX) {
            xDif = xMove - locationX;
        }
        else {
            xDif = locationX - xMove;
        }
        if (yMove > locationY) {
            yDif = yMove - locationY;
        }
        else {
            yDif = locationY - yMove;
        }
        
        if (xMove != locationX && yMove != locationY) {
            if (xMove > locationX) {
                for (int i = locationX+1; i <= xMove + locationX && i < 8; i++) {
                    if(boardtracker[i][locationY] != null){
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                boardtracker[locationX][locationY] = null;
                return true;
            }
            else {
                for (int i = locationX-1; i >= xMove && i >= 0; i--){
                    if(boardtracker[i][locationY] != null){
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                boardtracker[locationX][locationY] = null;
                return true;
            }
        }
        return false;
    }

    private boolean checkQueen(int xMove, int yMove) {
        return true;
    }

    private boolean checkKing(int xMove, int yMove) {
        return false;
    }
}
