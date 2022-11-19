package com.example.aaroncorona_cs56_proj7;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

// The class for drawing arcs on a panel
public class ClockFan extends Canvas implements Runnable {

    // Rotation vars
    private int rotationStartAngle;
    private String rotationStartTime, rotationEndTime;

    private Clock clock;

    private Thread thread = new Thread(this);

    public ClockFan(Clock clock) {
        super(250, 250);

        this.clock = clock;

        // Default rotation settings (not rotating)
        rotationStartAngle = 30;
        rotationStartTime = "000000";
        rotationEndTime = "000000";

        thread.start();
    }

    // Rotation setter for a predetermined time range
    public void startRotating(String rotationStartTime, String rotationEndTime) {
        this.rotationStartTime = rotationStartTime;
        this.rotationEndTime = rotationEndTime;
    }

    // Rotation setter for an indefinite rotation (e.g. for a force start button)
    public void startRotating() {
        this.rotationStartTime = "000000";
        this.rotationEndTime = "999999";
    }

    // Rotation setter to stop (e.g. for a force stop button)
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
    public void draw() {
        GraphicsContext g = this.getGraphicsContext2D();

        // Determine size
        int xCenter = (int) (getWidth() / 2);
        int yCenter = (int) (getHeight() / 2);
        int radius = (int) (Math.min(getWidth(), getHeight()) * 0.4);
        int x = xCenter - radius;
        int y = yCenter - radius;
        // Draw the fan (4 arcs)
        g.setFill(javafx.scene.paint.Color.BLACK);
        for(int i=0; i < 4; i++) {
            g.fillArc(x, y, 2 * radius, 2 * radius, rotationStartAngle, 30, ArcType.ROUND);
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
                draw();
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}