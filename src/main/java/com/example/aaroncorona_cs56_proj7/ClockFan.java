package com.example.aaroncorona_cs56_proj7;

import java.awt.*;

// The class for drawing arcs on a panel
public class ClockFan extends Component implements Runnable {

    // Position vars
    int width, height;
    int xCenter, yCenter;

    // TODO - add rotation setter method
    // TODO - rotationStart and rotationEnd variables
    // TODO - add rotation variable if needed
    // TODO - add rotation method to set the angleStart based on above vars if rotation is true and corresponds w its clock time

    public ClockFan(Clock clock) {
        this.width = clock.getWidth();
        this.height = clock.getHeight();
        this.xCenter = clock.getXCenter();
        this.yCenter = clock.getYCenter() + Panel.COMPONENT_HEIGHT;
    }

    // Draw the Fan
    public void paint(Graphics g) {
        int radius = (int) (Math.min(width, height) * 0.4);
        int x = xCenter - radius;
        int y = yCenter - radius;
        // Draw the background pink
        g.setColor(Color.pink);
        g.fillRect(x-20, y-20, width, height);
        // Draw the fan
        // TODO - change startAngle based on rotation or method
        g.setColor(Color.black);
        g.fillArc(x, y, 2 * radius, 2 * radius, 0, 30);
        g.fillArc(x, y, 2 * radius, 2 * radius, 90, 30);
        g.fillArc(x, y, 2 * radius, 2 * radius, 180, 30);
        g.fillArc(x, y, 2 * radius, 2 * radius, 270, 30);
    }

    @Override
    public void run() {
        try{
            while(true) {
                // Update the clock's data
                // TODO - run rotation method here to update data for the next draw
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}