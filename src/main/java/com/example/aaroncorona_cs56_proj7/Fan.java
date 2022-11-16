package com.example.aaroncorona_cs56_proj7;

import java.awt.*;

// The class for drawing arcs on a panel
public class Fan extends Component {

    // Position vars
    int width, height;
    int xCenter, yCenter;

    public Fan(int width, int height, int xCenter, int yCenter) {
        this.width = width;
        this.height = height;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
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
        g.setColor(Color.black);
        g.fillArc(x, y, 2 * radius, 2 * radius, 0, 30);
        g.fillArc(x, y, 2 * radius, 2 * radius, 90, 30);
        g.fillArc(x, y, 2 * radius, 2 * radius, 180, 30);
        g.fillArc(x, y, 2 * radius, 2 * radius, 270, 30);
    }
}