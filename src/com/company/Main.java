package com.company;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gui.pack();
        gui.setResizable(true);
        gui.setLocationRelativeTo( null );
        gui.setVisible(true);
    }

}
