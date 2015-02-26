package com.sunbotu.androidmouse.pc.test;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/**
 * Test if the Robot works!
 * 
 * @author Botu Sun
 *
 */
public class MouseControlTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Robot robot;
        Point b;
        try {
            robot = new Robot();
            for (int i = 0; i < 100; i++) {
                b = MouseInfo.getPointerInfo().getLocation();
                robot.mouseMove(b.x + 1, b.y + 1);
                Thread.sleep(10);

            }
        } catch (AWTException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
