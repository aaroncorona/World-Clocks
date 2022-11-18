package com.example.aaroncorona_cs56_proj7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel implements Runnable {

    // Component objects
    private Clock c1,c2,c3;
    private ClockFan cFan1, cFan2, cFan3;

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
        this.setFocusable(true);
        this.setLayout(null);

        // Add Clock and Fan components
        buildComponents();

        // Launch Threads
        startThreads();
    }

    // Helper to build components (clocks, fans, menus, and buttons) on the Panel
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
        // Add a Fan Settings menu for each fan
        ClockFanMenu cFan1Menu = new ClockFanMenu(cFan1);
        ClockFanMenu cFan2Menu = new ClockFanMenu(cFan2);
        ClockFanMenu cFan3Menu = new ClockFanMenu(cFan3);
        // Add a button to open and close each Menu
        Button cFan1MenuButton = new Button("Fan Settings");
        cFan1MenuButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                cFan1Menu.toggleMenu();
            }
        });
        Button cFan2MenuButton = new Button("Fan Settings");
        cFan2MenuButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                cFan2Menu.toggleMenu();
            }
        });
        Button cFan3MenuButton = new Button("Fan Settings");
        cFan3MenuButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                cFan3Menu.toggleMenu();
            }
        });
        // Add the buttons to the Panel
        this.add(cFan1MenuButton); this.add(cFan2MenuButton); this.add(cFan3MenuButton);
        cFan1MenuButton.setBounds(cFan1.getXCenter() - 60, cFan1.getYCenter() + COMPONENT_HEIGHT/2,
                120,40);
        cFan2MenuButton.setBounds(cFan2.getXCenter() - 60, cFan2.getYCenter() + COMPONENT_HEIGHT/2,
                120,40);
        cFan3MenuButton.setBounds(cFan3.getXCenter() - 60, cFan3.getYCenter() + COMPONENT_HEIGHT/2,
                120,40);
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
