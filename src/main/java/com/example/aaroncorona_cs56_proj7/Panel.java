package com.example.aaroncorona_cs56_proj7;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {

    // Component objects
    private Clock c1,c2,c3;
    private ClockFan cFan1, cFan2, cFan3;
    private ClockFanSettingsMenu cFan1Settings, cFan2Settings, cFan3Settings;

    // Component settings
    public static final int COMPONENT_WIDTH = 200;
    public static final int COMPONENT_HEIGHT = COMPONENT_WIDTH;

    // Threads
    private Thread panelThread;
    private Thread c1Thread, c2Thread, c3Thread;
    private Thread cFan1Thread, cFan2Thread, cFan3Thread;

    public Panel() {
        // Default UI settings
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(getPreferredSize());
        this.setLayout(null);
        this.setFocusable(true);

        // Add Clock and Fan components
        buildComponents();

        // Launch Threads
        startThreads();
    }

    // Helper to build clocks and fans on the Panel
    private void buildComponents() {
        // Set consistent start positions for alignment
        int xStartPos = 100;
        int yStartPos = 100;
        // Add Clocks
        c1 = new Clock(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                        xStartPos, yStartPos);
        c1.setTimeZone("PST");
        c2 = new Clock(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                xStartPos + COMPONENT_WIDTH, yStartPos);
        c2.setTimeZone("KST");
        c3 = new Clock(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                xStartPos + COMPONENT_WIDTH*2, yStartPos);
        c3.setTimeZone("HST");
        // Add Fans that correspond to the Clocks
        cFan1 = new ClockFan(c1);
        cFan2 = new ClockFan(c2);
        cFan3 = new ClockFan(c3);
        // Add Fan Settings for each fan
        cFan1Settings = new ClockFanSettingsMenu(cFan1);
        cFan2Settings = new ClockFanSettingsMenu(cFan2);
        cFan3Settings = new ClockFanSettingsMenu(cFan3);
        // Place the Menus on the Panel TODO
        cFan1Settings.setLocation(xStartPos + COMPONENT_WIDTH*4, yStartPos + COMPONENT_WIDTH*4);
        cFan2Settings.setLocation(xStartPos + COMPONENT_WIDTH*5, yStartPos + COMPONENT_WIDTH*4);
        cFan3Settings.setLocation(xStartPos + COMPONENT_WIDTH*6, yStartPos + COMPONENT_WIDTH*4);

        // Place the Fan Setting Text Fields on the Panel TODO

        // Place the Fan Setting Buttons on the Panel TODO
    }

    // Helper to launch component threads
    private void startThreads() {
        // Panel Thread
        panelThread = new Thread(this);
        panelThread.start();
        // Clock Threads
        c1Thread = new Thread(c1);
        c2Thread = new Thread(c2);
        c3Thread = new Thread(c3);
        c1Thread.start();
        c2Thread.start();
        c3Thread.start();
        // Fan Threads
        cFan1Thread = new Thread(cFan1);
        cFan2Thread = new Thread(cFan2);
        cFan3Thread = new Thread(cFan3);
        cFan1Thread.start();
        cFan2Thread.start();
        cFan3Thread.start();
    }

    // Draw all the Panel child objects
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint Clocks
        c1.paint(g);
        c2.paint(g);
        c3.paint(g);
        // Paint Fans
        cFan1.paint(g);
        cFan2.paint(g);
        cFan3.paint(g);
    }

    // Thread that will continually repaint
    @Override
    public void run() {
        try{
            while(true) {
                // Repaint the Panel continually
                repaint();
                Thread.sleep(100);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
