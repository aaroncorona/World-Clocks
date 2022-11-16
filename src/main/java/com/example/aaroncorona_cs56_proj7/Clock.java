package com.example.aaroncorona_cs56_proj7;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import static java.util.TimeZone.getTimeZone;

// Draws a Clock within its own Panel container
public class Clock extends Component implements Runnable {

  // Time vars
  private double hour, minute, second;

  // Position vars
  int width, height;
  int xCenter, yCenter;

  /** Construct a default clock with the current time*/
  public Clock(int width, int height, int xCenter, int yCenter) {
    this.width = width;
    this.height = height;
    this.xCenter = xCenter;
    this.yCenter = yCenter;

    setCurrentTime();
  }

  /** Return hour */
  public double getHour() {
    return hour;
  }

  /** Set a new hour */
  public void setHour(int hour) {
    this.hour = hour;
  }

  /** Return minute */
  public double getMinute() {
    return minute;
  }

  /** Set a new minute */
  public void setMinute(int minute) {
    this.minute = minute;
  }

  /** Return second */
  public double getSecond() {
    return second;
  }

  /** Set a new second */
  public void setSecond(int second) {
    this.second = second;
  }

  /** Draw the Clock */
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
  }

  // Increase the clock's seconds to mimic time passing
  public void addSecond() {
      second++;
      minute = minute + 0.017;
      hour = hour + 0.00017;
  }

  // Sets the timezone based on user input
  public void setTimeZone(String timeZone) {
    setCurrentTime(timeZone);
  }

  // Helper method to update the clock time variables with the current time
  private void setCurrentTime() {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();
    calendar.setTimeZone(getTimeZone("PST"));
    // Set current hour, minute and second
    this.hour = calendar.get(Calendar.HOUR_OF_DAY);
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);
  }

  // Helper method to update the clock time variables with the current time (with Timezone chosen)
  private void setCurrentTime(String timezone) {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();
    calendar.setTimeZone(getTimeZone(timezone));
    // Set current hour, minute and second
    this.hour = calendar.get(Calendar.HOUR_OF_DAY);
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);
  }

  // Thread to increase the clock's time by 1 second
  public void run() {
    try{
      while(true) {
        // Update the clock's data
        this.addSecond();
        Thread.sleep(1000);
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }
}