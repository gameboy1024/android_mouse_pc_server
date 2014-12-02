package com.sunbotu.androidmouse.pc;

import java.awt.Point;
import java.util.LinkedList;

public class MouseMoveFilter {
  public static final int SMOOTH_N_TH = 10;
  
  private LinkedList<Point> previousPoints;
  
  public MouseMoveFilter() {
    previousPoints = new LinkedList<Point>();
  }
  
  public Point filter(int x, int y) {
    int totalPoints = 1;
    previousPoints.push(new Point(x, y));
    if (previousPoints.size() == SMOOTH_N_TH) {
      previousPoints.removeLast();
    }
    totalPoints += previousPoints.size();
    
    double x_smoothed = x;
    double y_smoothed = y;
    for (Point point : previousPoints) {
      x_smoothed += point.x;
      y_smoothed += point.y;
    }
    x_smoothed /= totalPoints;
    y_smoothed /= totalPoints;
    return new Point((int)x_smoothed, (int)y_smoothed);
  }
}
