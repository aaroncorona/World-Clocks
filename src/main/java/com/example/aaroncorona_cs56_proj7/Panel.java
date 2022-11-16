package com.example.aaroncorona_cs56_proj7;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {

    // Component objects
    private Clock c1,c2,c3;
    private Fan fan1,fan2,fan3;

    // Component settings
    public static final int COMPONENT_WIDTH = 200;
    public static final int COMPONENT_HEIGHT = COMPONENT_WIDTH;

    // Threads
    private Thread panelThread;
    private Thread c1Thread, c2Thread, c3Thread;

    public Panel() {
        // Default UI settings
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(getPreferredSize());
        this.setFocusable(true);

        // Add Clock and Fan components
        buildComponents();

        // Threads
        panelThread = new Thread(this);
        panelThread.start();
        startComponentThreads();
    }

    // Helper to build clocks and fans on the Panel
    private void buildComponents() {
        // Add Component objects to Panel (Clocks)
        int xStartPos = 100;
        int yStartPos = 100;
        c1 = new Clock(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                        xStartPos, yStartPos);
        c1.setTimeZone("PST"); // TODO set timezones
        c2 = new Clock(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                xStartPos + COMPONENT_WIDTH, yStartPos);
        c3 = new Clock(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                xStartPos + COMPONENT_WIDTH*2, yStartPos);
        this.add(c1); this.add(c2); this.add(c2);
        // Add Component objects to Panel (Fans)
        fan1 = new Fan(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                        xStartPos, yStartPos + COMPONENT_HEIGHT);
        fan2 = new Fan(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                xStartPos + COMPONENT_WIDTH, yStartPos + COMPONENT_HEIGHT);
        fan3 = new Fan(COMPONENT_WIDTH, COMPONENT_HEIGHT,
                xStartPos + COMPONENT_WIDTH*2, yStartPos + COMPONENT_HEIGHT);
        this.add(fan1); this.add(fan2); this.add(fan3);
    }

    // Helper to launch component threads
    private void startComponentThreads() {
        // Clock threads
        c1Thread = new Thread(c1);
        c2Thread = new Thread(c2);
        c3Thread = new Thread(c3);
        c1Thread.start();
        c2Thread.start();
        c3Thread.start();
        // Fan Threads TODO
    }

    @Override /** Draw all the Panel child objects */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint Clocks
        c1.paint(g); c2.paint(g); c3.paint(g);
        // Paint Fans
        fan1.paint(g); fan2.paint(g); fan3.paint(g);
    }

    // Thread that will continually repaint
    @Override
    public void run() {
        try{
            while(true) {
                // Repaint the Panel concurrently with the time updates
                repaint();
                Thread.sleep(100);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
