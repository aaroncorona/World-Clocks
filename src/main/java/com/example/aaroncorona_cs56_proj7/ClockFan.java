package com.example.aaroncorona_cs56_proj7;

import java.awt.*;

// The class for drawing arcs on a panel
public class ClockFan extends Component implements Runnable {

    // Position vars
    private int width, height;
    private int xCenter, yCenter;

    // Rotation vars
    private int rotationStartAngle;
    private String rotationStartTime, rotationEndTime;

    private Clock clock;

    public ClockFan(Clock clock) {
        this.clock = clock;
        this.width = clock.getWidth();
        this.height = clock.getHeight();
        this.xCenter = clock.getXCenter();
        this.yCenter = clock.getYCenter() + Panel.COMPONENT_HEIGHT;

        // Default rotation settings (not rotating)
        rotationStartAngle = 30;
        rotationStartTime = "000000";
        rotationEndTime = "000000";
    }

    // Rotation setter with predetermined window
    public void startRotating(String rotationStartTime, String rotationEndTime) {
        this.rotationStartTime = rotationStartTime;
        this.rotationEndTime = rotationEndTime;
    }

    // Rotation setter for an indefinite rotation
    public void startRotating() {
        this.rotationStartTime = "000000";
        this.rotationEndTime = "999999";
    }

    public void stopRotating() {
        this.rotationStartTime = "000000";
        this.rotationEndTime = "000000";
    }

    // Helper method to check if the fan should be rotating or not
    private boolean shouldRotate() {
        // Convert the rotation input times to integers
        int rotationStartHour = Integer.parseInt(rotationStartTime.substring(0,2));
        int rotationStartMin = Integer.parseInt(rotationStartTime.substring(2,4));
        int rotationEndHour = Integer.parseInt(rotationEndTime.substring(0,2));
        int rotationEndMin = Integer.parseInt(rotationEndTime.substring(2,4));
        // Check if the clock's time is in the rotation window
        if(rotationStartHour <= clock.getHour()
           && rotationStartMin <= clock.getMinute()
           && clock.getHour() <= rotationEndHour
           && clock.getMinute() <= rotationEndMin) {
            return true;
        }
        return false;
    }

    // Helper method to rotate the fan by updating its position
    private void rotate() {
        rotationStartAngle += 15;
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
        for(int i=0; i < 4; i++) {
            g.fillArc(x, y, 2 * radius, 2 * radius, rotationStartAngle, 30);
            rotationStartAngle += 90;
        }
    }

    @Override
    public void run() {
        try{
            while(true) {
                // Rotate the Fan if needed
                if(shouldRotate()) {
                    rotate();
                }
                Thread.sleep(100);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}