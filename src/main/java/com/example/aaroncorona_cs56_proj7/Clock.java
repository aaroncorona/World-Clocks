package com.example.aaroncorona_cs56_proj7;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import static java.util.TimeZone.getTimeZone;

// Draws a Clock within its own Panel container
public class Clock extends Component implements Runnable {

  // Time vars
  private int hour, minute, second;
  private String timezone = "PST";

  // Position vars
  int width, height;
  int xCenter, yCenter;

  // Construct a default clock with the current time
  public Clock(int width, int height, int xCenter, int yCenter) {
    this.width = width;
    this.height = height;
    this.xCenter = xCenter;
    this.yCenter = yCenter;

    updateToCurrentTime();
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

  public int getXCenter() {
    return xCenter;
  }

  public int getYCenter() {
    return yCenter;
  }

  // Sets the timezone based on user input
  public void setTimeZone(String timeZone) {
    this.timezone = timeZone;
  }

  // Draw the Clock
  public void paint(Graphics g) {
    // Initialize clock parameters
    int clockRadius = (int)(Math.min(width, height) * 0.8 * 0.5);
    // Draw the background yellow
    g.setColor(Color.YELLOW);
    g.fillRect(xCenter - clockRadius - 20, yCenter - clockRadius - 20, width, height);
    // Draw circle
    g.setColor(Color.black);
    g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
            2 * clockRadius, 2 * clockRadius);
    g.setColor(Color.LIGHT_GRAY.brighter());
    g.fillOval(xCenter - clockRadius, yCenter - clockRadius,
            2 * clockRadius, 2 * clockRadius);
    g.setColor(Color.black);
    g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
    g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
    g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
    g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);
    // Draw second hand
    int sLength = (int)(clockRadius * 0.8);
    int xSecond = (int)(xCenter + sLength *
            Math.sin(second * (2 * Math.PI / 60)));
    int ySecond = (int)(yCenter - sLength *
            Math.cos(second * (2 * Math.PI / 60)));
    g.setColor(Color.red);
    g.drawLine(xCenter, yCenter, xSecond, ySecond);
    // Draw minute hand
    int mLength = (int)(clockRadius * 0.65);
    int xMinute = (int)(xCenter + mLength *
            Math.sin(minute * (2 * Math.PI / 60)));
    int yMinute = (int)(yCenter - mLength *
            Math.cos(minute * (2 * Math.PI / 60)));
    g.setColor(Color.blue);
    g.drawLine(xCenter, yCenter, xMinute, yMinute);
    // Draw hour hand
    int hLength = (int)(clockRadius * 0.5);
    int xHour = (int)(xCenter + hLength *
            Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
    int yHour = (int)(yCenter - hLength *
            Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
    g.setColor(Color.green);
    g.drawLine(xCenter, yCenter, xHour, yHour);
    // Draw timezone
    g.setColor(Color.black);
    g.setFont(new Font("default", Font.BOLD, 16));
    g.drawString(timezone, xCenter - 13, yCenter - 25);
    g.setFont(new JLabel().getFont()); // reset font
    // Draw time
    String time = String.valueOf(hour) + ": " + String.valueOf(minute) + ": " + String.valueOf(second);
    g.drawString(time, xCenter - 30, yCenter + 35);
  }

  // Helper method to update the clock time variables with the current time
  private void updateToCurrentTime() {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();
    calendar.setTimeZone(getTimeZone(timezone));
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
        // Update the clock's data
        this.updateToCurrentTime();
        Thread.sleep(1000);
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }
}