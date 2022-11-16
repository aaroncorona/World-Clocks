package com.example.aaroncorona_cs56_proj7;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        // Default GUI settings
        this.setTitle("World Clocks");
        this.setLocationRelativeTo(null); // Center the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Panel.COMPONENT_WIDTH * 3, Panel.COMPONENT_HEIGHT * 3);
        // Add Panel
        Panel panel = new Panel();
        this.add(panel);
        this.setVisible(true);
    }

    // Main
    public static void main(String[] args) {
        new Frame();
    }
}