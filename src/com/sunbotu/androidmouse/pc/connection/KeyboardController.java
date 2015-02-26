package com.sunbotu.androidmouse.pc.connection;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyboardController {

    private static final int THRESHOLD = 4;
    private Robot robot;
    private Thread threadX, threadY;
    private int x = 0;
    private int y = 0;

    public KeyboardController() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        threadX = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        if (x < THRESHOLD && x > -THRESHOLD) {
                            Thread.sleep(100);
                        } else {
                            if (x > 0) {
                                robot.keyPress(KeyEvent.VK_RIGHT);
                                Thread.sleep(x * 10);
                                robot.keyRelease(KeyEvent.VK_RIGHT);
                                Thread.sleep((10 - x) * 10);
                            } else {
                                robot.keyPress(KeyEvent.VK_LEFT);
                                Thread.sleep(-x * 10);
                                robot.keyRelease(KeyEvent.VK_LEFT);
                                Thread.sleep((10 + x) * 10);
                            }

                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        threadX.start();

        threadY = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        if (y < THRESHOLD && y > -THRESHOLD) {
                            Thread.sleep(100);
                        } else {
                            if (y > 0) {
                                robot.keyPress(KeyEvent.VK_UP);
                                Thread.sleep(y * 10);
                                robot.keyRelease(KeyEvent.VK_UP);
                                Thread.sleep((10 - y) * 10);
                            } else {
                                robot.keyPress(KeyEvent.VK_DOWN);
                                Thread.sleep(-y * 10);
                                robot.keyRelease(KeyEvent.VK_DOWN);
                                Thread.sleep((10 + y) * 10);
                            }

                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        threadY.start();
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
