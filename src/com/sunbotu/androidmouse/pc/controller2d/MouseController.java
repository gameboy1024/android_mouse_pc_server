package com.sunbotu.androidmouse.pc.controller2d;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

import com.sunbotu.androidmouse.pc.utils.Point;

public class MouseController {
    private Robot robot;
    private Point startPoint;
    private MouseMoveFilter filter;
    private double sensitivity;

    private enum State {
        IDLE, CLICKING
    };

    private State state = State.IDLE;
    private Timer timer;

    public MouseController(double sensitivity) {
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
        timer = new Timer();
    }

    public MouseController() {
        // Default sensibility is 50.
        this(50);
    }

    public double sensibilityFunction(double input) {
        return input * (Math.pow(Math.abs(input), 1.001) * 0.0001 + 1)
                * sensitivity;
    }

    public void moveRelative(double x, double y) {
        if (state == State.CLICKING) {
            return;
        }
        Point point = filter.filter(
                (int) (startPoint.x + sensibilityFunction(x)),
                (int) (startPoint.y + sensibilityFunction(y)));
        // System.out.println(point.x + " " + point.y);
        robot.mouseMove(point.x, point.y);
    }

    public void moveGame(double d, double e) {
        double coeff = 2;
        java.awt.Point currentLocation = MouseInfo.getPointerInfo()
                .getLocation();
        robot.mouseMove(currentLocation.x + (int) (d * coeff), currentLocation.y + (int) (e * coeff));
    }
    
    public void clickLeft() {
        synchronized (robot) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    public void clickLeftDown() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        state = State.CLICKING;
        // Pause a little to avoid unwanted dragging.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                state = State.IDLE;
            }
        }, 100);
    }

    public void clickLeftUp() {
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void clickRightDown() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        state = State.CLICKING;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                state = State.IDLE;
            }
        }, 200);
    }

    public void clickRightUp() {
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

}
