package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI  extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel board;
    Piece piece;
    Piece[][] boardTracker = new Piece[8][8];
    int x;
    int y;

    public Piece[][] getBoardTracker() {
        return boardTracker;
    }

    public void setBoardTracker(Piece[][] boardTracker) {
        this.boardTracker = boardTracker;
    }

    public GUI() {


        Dimension boardSize = new Dimension(800, 800);


        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        board = new JPanel();
        layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
        board.setLayout(new GridLayout(8, 8));
        board.setPreferredSize(boardSize);
        board.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            board.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.darkGray);
            else
                square.setBackground(i % 2 == 0 ? Color.darkGray : Color.white);
        }
        placePieces();
    }


    public void mousePressed(MouseEvent e) {
        piece = null;
        Component c = board.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        x = parentLocation.x - e.getX();
        y = parentLocation.y - e.getY();
        piece = (Piece) c;
        piece.setLocation(e.getX() + x, e.getY() + y);
        piece.setSize(piece.getWidth(), piece.getHeight());
        layeredPane.add(piece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
        if (piece == null) return;
        piece.setLocation(me.getX() + x, me.getY() + y);
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {
        if (piece == null) return;
        if (piece.checkMovement((e.getX()/100), (e.getY())/100)) {
            piece.setVisible(false);
            Component c = board.findComponentAt(e.getX(), e.getY());
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                parent.add(piece);
            } else {
                Container parent = (Container) c;
                parent.add(piece);
            }
            piece.setVisible(true);
            piece.locationX = e.getX()/100;
            piece.locationY = e.getY()/100;
            boardTracker[e.getX()/100][e.getY()/100] = piece;
        }
        else {
            piece.setVisible(false);
            Component c = board.findComponentAt(piece.locationX*100, piece.locationY*100);
            Container parent = (Container) c;
            parent.add(piece);
            piece.setVisible(true);
        }
    }

    public void mouseClicked(MouseEvent e) { }

    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void placePieces() {
        //Adding each piece to the board scaled down to our board size

        ImageIcon icon = new ImageIcon("Pieces/BlkRook.jpg");
        Image img = icon.getImage();
        Piece blkRook = new Piece(1,0,0,1);
        Image imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        blkRook.setIcon(scaledIcon);
        JPanel panel = (JPanel) board.getComponent(0);
        panel.add(blkRook);
        boardTracker[blkRook.locationX][blkRook.locationY] = blkRook;


        icon = new ImageIcon("Pieces/BlkKnight.jpg");
        img = icon.getImage();
        Piece blkKnight = new Piece(2, 1, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkKnight.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(1);
        panel.add(blkKnight);
        boardTracker[blkKnight.locationX][blkKnight.locationY] = blkKnight;

        icon = new ImageIcon("Pieces/BlkBishop.jpg");
        img = icon.getImage();
        Piece blkBishop = new Piece(3, 2, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkBishop.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(2);
        panel.add(blkBishop);
        boardTracker[blkBishop.locationX][blkBishop.locationY] = blkBishop;

        icon = new ImageIcon("Pieces/BlkKing.jpg");
        img = icon.getImage();
        Piece blkKing = new Piece(5, 3, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkKing.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(3);
        panel.add(blkKing);
        boardTracker[blkKing.locationX][blkKing.locationY] = blkKing;

        icon = new ImageIcon("Pieces/BlkQueen.jpg");
        img = icon.getImage();
        Piece blkQueen = new Piece(4, 4, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkQueen.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(4);
        panel.add(blkQueen);
        boardTracker[blkQueen.locationX][blkQueen.locationY] = blkQueen;

        icon = new ImageIcon("Pieces/BlkBishop.jpg");
        img = icon.getImage();
        Piece blkBishop2 = new Piece(3, 5, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkBishop2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(5);
        panel.add(blkBishop2);
        boardTracker[blkBishop2.locationX][blkBishop2.locationY] = blkBishop2;

        icon = new ImageIcon("Pieces/BlkKnight.jpg");
        img = icon.getImage();
        Piece blkKnight2 = new Piece(2, 6, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkKnight2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(6);
        panel.add(blkKnight2);
        boardTracker[blkKnight2.locationX][blkKnight2.locationY] = blkKnight2;

        icon = new ImageIcon("Pieces/BlkRook.jpg");
        img = icon.getImage();
        Piece blkRook2 = new Piece(1, 7, 0,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkRook2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(7);
        panel.add(blkRook2);
        boardTracker[blkRook2.locationX][blkRook2.locationY] = blkRook2;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn = new Piece(0, 0, 1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(8);
        panel.add(blkPawn);
        boardTracker[blkPawn.locationX][blkPawn.locationY] = blkPawn;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn2 = new Piece(0, 1, 1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(9);
        panel.add(blkPawn2);
        boardTracker[blkPawn2.locationX][blkPawn2.locationY] = blkPawn2;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn3 = new Piece(0, 2, 1 ,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn3.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(10);
        panel.add(blkPawn3);
        boardTracker[blkPawn3.locationX][blkPawn3.locationY] = blkPawn3;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn4 = new Piece(0, 3 , 1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn4.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(11);
        panel.add(blkPawn4);
        boardTracker[blkPawn4.locationX][blkPawn4.locationY] = blkPawn4;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn5 = new Piece(0, 4, 1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn5.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(12);
        panel.add(blkPawn5);
        boardTracker[blkPawn5.locationX][blkPawn5.locationY] = blkPawn5;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn6 = new Piece(0, 5, 1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn6.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(13);
        panel.add(blkPawn6);
        boardTracker[blkPawn6.locationX][blkPawn6.locationY] = blkPawn6;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn7 = new Piece(0, 6 ,1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn7.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(14);
        panel.add(blkPawn7);
        boardTracker[blkPawn7.locationX][blkPawn7.locationY] = blkPawn7;

        icon = new ImageIcon("Pieces/BlkPawn.jpg");
        img = icon.getImage();
        Piece blkPawn8 = new Piece(0, 7, 1,1);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        blkPawn8.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(15);
        panel.add(blkPawn8);
        boardTracker[blkPawn8.locationX][blkPawn8.locationY] = blkPawn8;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn = new Piece(0, 0 ,6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(48);
        panel.add(whtPawn);
        boardTracker[whtPawn.locationX][whtPawn.locationY] = whtPawn;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn2 = new Piece(0, 1, 6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(49);
        panel.add(whtPawn2);
        boardTracker[whtPawn2.locationX][whtPawn2.locationY] = whtPawn2;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn3 = new Piece(0, 2, 6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn3.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(50);
        panel.add(whtPawn3);
        boardTracker[whtPawn3.locationX][whtPawn3.locationY] = whtPawn3;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn4 = new Piece(0, 3, 6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn4.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(51);
        panel.add(whtPawn4);
        boardTracker[whtPawn4.locationX][whtPawn4.locationY] = whtPawn4;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn5 = new Piece(0,4 ,6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn5.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(52);
        panel.add(whtPawn5);
        boardTracker[whtPawn5.locationX][whtPawn5.locationY] = whtPawn5;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn6 = new Piece(0,5,6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn6.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(53);
        panel.add(whtPawn6);
        boardTracker[whtPawn6.locationX][whtPawn6.locationY] = whtPawn6;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn7 = new Piece(0,6,6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn7.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(54);
        panel.add(whtPawn7);
        boardTracker[whtPawn7.locationX][whtPawn7.locationY] = whtPawn7;

        icon = new ImageIcon("Pieces/WhtPawn.jpg");
        img = icon.getImage();
        Piece whtPawn8 = new Piece(0,7,6,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtPawn8.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(55);
        panel.add(whtPawn8);
        boardTracker[whtPawn8.locationX][whtPawn8.locationY] = whtPawn8;

        icon = new ImageIcon("Pieces/WhtRook.jpg");
        img = icon.getImage();
        Piece whtRook = new Piece(1,0,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtRook.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(56);
        panel.add(whtRook);
        boardTracker[whtRook.locationX][whtRook.locationY] = whtRook;

        icon = new ImageIcon("Pieces/WhtKnight.jpg");
        img = icon.getImage();
        Piece whtKnight = new Piece(2, 1, 7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtKnight.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(57);
        panel.add(whtKnight);
        boardTracker[whtKnight.locationX][whtKnight.locationY] = whtKnight;

        icon = new ImageIcon("Pieces/WhtBishop.jpg");
        img = icon.getImage();
        Piece whtBishop = new Piece(3,2,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtBishop.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(58);
        panel.add(whtBishop);
        boardTracker[whtBishop.locationX][whtBishop.locationY] = whtBishop;

        icon = new ImageIcon("Pieces/WhtQueen.jpg");
        img = icon.getImage();
        Piece whtQueen = new Piece(4,3,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtQueen.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(59);
        panel.add(whtQueen);
        boardTracker[whtQueen.locationX][whtQueen.locationY] = whtQueen;

        icon = new ImageIcon("Pieces/WhtKing.jpg");
        img = icon.getImage();
        Piece whtKing = new Piece(5,4,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtKing.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(60);
        panel.add(whtKing);
        boardTracker[whtKing.locationX][whtKing.locationY] = whtKing;

        icon = new ImageIcon("Pieces/WhtBishop.jpg");
        img = icon.getImage();
        Piece whtBishop2 = new Piece(3,5,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtBishop2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(61);
        panel.add(whtBishop2);
        boardTracker[whtBishop2.locationX][whtBishop2.locationY] = whtBishop2;

        icon = new ImageIcon("Pieces/WhtKnight.jpg");
        img = icon.getImage();
        Piece whtKnight2 = new Piece(2,6,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtKnight2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(62);
        panel.add(whtKnight2);
        boardTracker[whtKnight2.locationX][whtKnight2.locationY] = whtKnight2;

        icon = new ImageIcon("Pieces/WhtRook.jpg");
        img = icon.getImage();
        Piece whtRook2 = new Piece(1,7,7,0);
        imgScale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(imgScale);
        whtRook2.setIcon(scaledIcon);
        panel = (JPanel) board.getComponent(63);
        panel.add(whtRook2);
        boardTracker[whtRook2.locationX][whtRook2.locationY] = whtRook2;


        Piece.boardtracker = boardTracker;
    }

}

