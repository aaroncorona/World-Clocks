package com.example.aaroncorona_cs56_proj7;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.*;
import static java.util.TimeZone.getTimeZone;

// Draws a Clock within its own Panel container
public class Clock extends Canvas implements Runnable {

  // Time vars
  private int hour, minute, second;
  private String timezone = "PST";

  private Thread thread = new Thread(this);

  public Clock() {
    super(250, 250);

    updateToCurrentTime();
    thread.start();
  }

  public Clock(String timezone) {
    super(250, 250);

    this.timezone = timezone;

    updateToCurrentTime();
    thread.start();
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public int getSecond() {
    return second;
  }

  public void setSecond(int second) {
    this.second = second;
  }

  // Sets the timezone based on user input
  public void setTimeZone(String timeZone) {
    this.timezone = timeZone;
  }

  public String getTimeZone() {
    return timezone;
  }

  // Draw the Clock
  public void draw() {

    // Determine size
    GraphicsContext g = this.getGraphicsContext2D();
    int clockRadius = (int) (Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
    double xCenter = getWidth() / 2;
    double yCenter = getHeight() / 2;
    g.clearRect(0.0,0.0,getWidth(),getHeight());

    // Draw circle
    g.setFill(javafx.scene.paint.Color.TRANSPARENT);
    g.setStroke(javafx.scene.paint.Color.BLACK);
    g.fillOval(xCenter - clockRadius, yCenter - clockRadius,
            2 * clockRadius, 2 * clockRadius);
    g.setFill(javafx.scene.paint.Color.BLACK);
    g.fillText("12", xCenter - 5, yCenter - clockRadius + 12);
    g.fillText("9", xCenter - clockRadius + 3, yCenter + 5);
    g.fillText("3", xCenter + clockRadius - 10, yCenter + 3);
    g.fillText("6", xCenter - 3, yCenter + clockRadius - 3);
    g.strokeOval(xCenter - clockRadius, yCenter - clockRadius,
            2 * clockRadius, 2 * clockRadius);

    // Draw second hand
    double sLength = (clockRadius * 0.8);
    double xSecond = (xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
    double ySecond = (yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
    g.setStroke(javafx.scene.paint.Color.RED);
    g.strokeLine(xCenter, yCenter, xSecond, ySecond);

    // Draw minute hand
    double mLength = (clockRadius * 0.65);
    double xMinute = (xCenter + mLength *      Math.sin(minute * (2 * Math.PI / 60)));
    double yMinute = (yCenter - mLength *      Math.cos(minute * (2 * Math.PI / 60)));
    g.setStroke(javafx.scene.paint.Color.BLUE);
    g.strokeLine(xCenter, yCenter, xMinute, yMinute);

    // Hour hand
    double hLength = (clockRadius * 0.5);
    double xHour = (xCenter + hLength *
            Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
    double yHour = (yCenter - hLength *
            Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
    g.setStroke(javafx.scene.paint.Color.GREEN);
    g.strokeLine(xCenter, yCenter, xHour, yHour);

    // Draw location name
    g.setFill(Color.BLACK);
    g.setTextAlign(TextAlignment.CENTER);
    g.fillText(timezone,(xCenter),yCenter - (clockRadius/2));

    // Draw time as string
    String time = hour + ": " + minute + ": " + second;
    g.fillText(time,(xCenter),yCenter + (clockRadius/2));
  }

  // Helper method to update the clock time variables with the current time
  private void updateToCurrentTime() {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();
    calendar.setTimeZone(TimeZone.getTimeZone(timezone));
    // Set current hour, minute and second
    this.hour = calendar.get(Calendar.HOUR_OF_DAY);
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);
  }

  // Thread to continually sync the clock to the current time
  @Override
  public void run() {
    try{
      while(true) {
        // Update the clock's data and draw it again
        updateToCurrentTime();
        draw();
        Thread.sleep(1000);
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }
}