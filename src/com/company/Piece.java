package com.company;

import javax.swing.*;

public class Piece extends JLabel {
    static Piece[][] boardtracker;
    int type; //Type of piece 0=pawn,1=rook,2=knight,3=bishop,4=queen,5=king
    int locationX;
    int locationY;
    int team; //0=white, 1=black
    int id;

    public Piece(int type, int locationX, int locationY, int team, int id) {
        this.type = type;
        this.locationX = locationX;
        this.locationY = locationY;
        this.team = team;
        this.id = id;
    }

    public boolean checkMovement(int xMove, int yMove) {
        switch (type) {
            case 0:
                if (checkPawn(xMove, yMove)) {
                    return true;
                }
                break;
            case 1:
                if (checkRook(xMove, yMove)) {
                    return true;
                }
                break;
            case 2:
                if (checkKnight(xMove, yMove)) {
                    return true;
                }
                break;
            case 3:
                if (checkBishop(xMove, yMove)) {
                    return true;
                }
                break;
            case 4:
                if (checkQueen(xMove, yMove)) {
                    return true;
                }
                break;
            case 5:
                if (checkKing(xMove, yMove)) {
                    return true;
                }
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
        } else {
            if (locationY - yMove > 1) {
                return false;
            }
            if (locationX - xMove != 0) {
                if (boardtracker[xMove][yMove] == null || boardtracker[xMove][yMove].team == team)
                    return false;
            }
        }
        return true;
    }

    public boolean checkRook(int xMove, int yMove) {
        if (xMove != locationX && yMove == locationY) {
            if (xMove > locationX) {
                for (int i = locationX + 1; i <= xMove && i < 8; i++) {
                    if (boardtracker[i][locationY] != null) {
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                for (int i = locationX - 1; i >= xMove && i >= 0; i--) {
                    if (boardtracker[i][locationY] != null) {
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } else if (yMove != locationY && xMove == locationX) {
            if (yMove > locationY) {
                for (int i = locationY + 1; i <= yMove && i < 8; i++) {
                    if (boardtracker[locationX][i] != null) {
                        Piece blockingPiece = boardtracker[locationX][i];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                System.out.println(yMove);
                System.out.println(locationY);
                for (int i = locationY - 1; i >= yMove && i >= 0; i--) {
                    if (boardtracker[locationX][i] != null) {
                        Piece blockingPiece = boardtracker[locationX][i];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
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
        } else {
            xDif = locationX - xMove;
        }
        if (yMove > locationY) {
            yDif = yMove - locationY;
        } else {
            yDif = locationY - yMove;
        }
        if (xDif != 0) {
            //Check if our move is valid
            if (xDif == 1) {
                if (yDif == 2) {
                    //Check if our move is blocked by our team
                    if (boardtracker[xMove][yMove] != null && boardtracker[xMove][yMove].team == team) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            //Check if our move is valid
            if (xDif == 2) {
                if (yDif == 1) {
                    //Check if our move is blocked by our team
                    if (boardtracker[xMove][yMove] != null && boardtracker[xMove][yMove].team == team) {
                        return false;
                    } else {
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
        } else {
            xDif = locationX - xMove;
        }
        if (yMove > locationY) {
            yDif = yMove - locationY;
        } else {
            yDif = locationY - yMove;
        }


        //Check if we haven't moved
        if (xMove != locationX && yMove != locationY) {
            //Check if we are making a diagonal move
            if (yDif != xDif) {
                return false;
            }
            // Check if we are moving right
            if (xMove > locationX) {
                //Check if we are moving down
                if (yMove > locationY) {
                    for (int i = locationX + 1; i <= xMove && i < 8; i++) {
                        for (int j = locationY + 1; j <= yMove && j < 8; j++) {
                            if (boardtracker[i][j] != null) {
                                Piece blockingPiece = boardtracker[i][j];
                                if (blockingPiece.team == team) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                //Else we are now moving up
                else {
                    for (int i = locationX + 1; i <= xMove && i < 8; i++) {
                        for (int j = locationY - 1; j <= yMove && j > 0; j--) {
                            if (boardtracker[i][j] != null) {
                                Piece blockingPiece = boardtracker[i][j];
                                if (blockingPiece.team == team) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                return true;
            }
            //Else if we are moving left
            else {
                //Check if we are moving down
                if (yMove > locationY) {
                    for (int i = locationX - 1; i >= xMove && i >= 0; i--) {
                        for (int j = locationY + 1; j <= yMove && j < 8; j++) {
                            if (boardtracker[i][j] != null) {
                                Piece blockingPiece = boardtracker[i][j];
                                if (blockingPiece.team == team) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                //Check if we are moving up
                else {
                    for (int i = locationX - 1; i >= xMove && i >= 0; i--) {
                        for (int j = locationY - 1; j >= yMove && j > 0; j--) {
                            if (boardtracker[i][j] != null) {
                                Piece blockingPiece = boardtracker[i][j];
                                if (blockingPiece.team == team) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    private boolean checkQueen(int xMove, int yMove) {
        int xDif = 0;
        int yDif = 0;
        int xCheck = locationX;
        int yCheck = locationY;

        if (xMove > locationX) {
            xDif = xMove - locationX;
        } else {
            xDif = locationX - xMove;
        }
        if (yMove > locationY) {
            yDif = yMove - locationY;
        } else {
            yDif = locationY - yMove;
        }

        //Check if we haven't moved
        if (xMove == locationX && yMove == locationY) {
            System.out.println("flag 1");
            return false;
        }


        //Check if we are moving diagonally
        if (xMove != locationX && yMove != locationY && xDif == yDif) {
            System.out.println("diag");
            // Check if we are moving right
            if (xMove > locationX) {
                System.out.println("right");
                //Check if we are moving down
                if (yMove > locationY) {
                    System.out.println("down");
                    xCheck++;
                    yCheck++;
                    while (xCheck < 8 && yCheck < 8 && xCheck > xMove && yCheck > yMove) {
                        if (boardtracker[xCheck][yCheck] != null) {
                            Piece blockingPiece = boardtracker[xCheck][yCheck];
                            if (blockingPiece.team == team || blockingPiece.locationY != yMove) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                        System.out.println(xCheck == yCheck);
                        xCheck++;
                        yCheck++;
                    }
                    return true;
                }
                //Check if we are moving up
                else {
                    System.out.println("up");
                    xCheck++;
                    yCheck--;
                    while (xCheck < 8 && yCheck != 0 && xCheck > xMove && yCheck > yMove) {
                        if (boardtracker[xCheck][yCheck] != null) {
                            Piece blockingPiece = boardtracker[xCheck][yCheck];
                            if (blockingPiece.team == team || blockingPiece.locationY != yMove) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                        xCheck++;
                        yCheck--;
                    }
                    return true;
                }
            }
            //Else if we are moving left
            else {
                System.out.println("left");
                //Check if we are moving down
                if (yMove > locationY) {
                    System.out.println("down");
                    xCheck--;
                    yCheck++;
                    while (xCheck > 0 && yCheck < 8 && xCheck > xMove && yCheck > yMove) {
                        if (boardtracker[xCheck][yCheck] != null) {
                            Piece blockingPiece = boardtracker[xCheck][yCheck];
                            if (blockingPiece.team == team || blockingPiece.locationY != yMove) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                        System.out.println(xCheck == yCheck);
                        xCheck--;
                        yCheck++;
                    }
                    return true;
                }
                //Check if we are moving up
                else {
                    System.out.println("up");
                    xCheck--;
                    yCheck--;
                    while (xCheck > 0 && yCheck != 0 && xCheck > xMove && yCheck > yMove) {
                        if (boardtracker[xCheck][yCheck] != null) {
                            Piece blockingPiece = boardtracker[xCheck][yCheck];
                            if (blockingPiece.team == team || blockingPiece.locationY != yMove) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                        xCheck--;
                        yCheck--;
                    }
                    return true;
                }
            }
        }
        if (yDif == 0 || xDif == 0) {
            //Right
            if (xMove > locationX && yDif == 0) {
                for (int i = locationX + 1; i <= xMove && i < 8; i++) {
                    if (boardtracker[i][locationY] != null) {
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            }
            //Left
            else if (xMove < locationX && yDif == 0) {
                for (int i = locationX - 1; i >= xMove && i >= 0; i--) {
                    if (boardtracker[i][locationY] != null) {
                        Piece blockingPiece = boardtracker[i][locationY];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            }
            //Down
            else if (yMove > locationY && xDif == 0) {
                for (int i = locationY + 1; i <= yMove && i < 8; i++) {
                    if (boardtracker[locationX][i] != null) {
                        System.out.println("Here");
                        Piece blockingPiece = boardtracker[locationX][i];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            }
            //Up
            else if (yMove < locationY && xDif == 0){
                for (int i = locationY - 1; i >= yMove && i >= 0; i--) {
                    if (boardtracker[locationX][i] != null) {
                        Piece blockingPiece = boardtracker[locationX][i];
                        if (blockingPiece.team == team) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkKing(int xMove, int yMove) {
        int xDif = 0;
        int yDif = 0;

        if (xMove > locationX) {
            xDif = xMove - locationX;
        } else {
            xDif = locationX - xMove;
        }
        if (yMove > locationY) {
            yDif = yMove - locationY;
        } else {
            yDif = locationY - yMove;
        }

        if (xDif > 1 || yDif > 1) {

            return false;
        }


        if (boardtracker[xMove][yMove] != null) {
            Piece blockingPiece = boardtracker[xMove][yMove];
            if (blockingPiece.team == team) {
                return false;
            }
        }
        return true;

    }
}
