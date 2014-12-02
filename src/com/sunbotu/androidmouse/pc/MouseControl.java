package com.sunbotu.androidmouse.pc;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

public class MouseControl {
  private Robot robot;
  private Point startPoint;
  private MouseMoveFilter filter;
  private double sensitivity;

  public MouseControl(double sensitivity) {
    this.sensitivity = sensitivity;
    filter = new MouseMoveFilter();
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
    Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
    Point center = new Point(resolution.width / 2, resolution.height / 2);
    // startPoint = MouseInfo.getPointerInfo().getLocation();
    startPoint = center;
  }

  public MouseControl() {
    this(50);
  }

  public void moveRelative(double x, double y) {
    Point point = filter.filter((int) (startPoint.x + x * sensitivity),
        (int) (startPoint.y + y * sensitivity));
    robot.mouseMove(point.x, point.y);
  }

  public void clickLeftDown() {
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
  }

  public void clickLeftUp() {
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  public void clickRightDown() {
    robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
  }

  public void clickRightUp() {
    robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
  }
}
