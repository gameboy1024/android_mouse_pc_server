package com.sunbotu.androidmouse.pc.controller2d;

import java.io.IOException;

import com.sunbotu.androidmouse.pc.connection.KeyboardController;
import com.sunbotu.androidmouse.pc.utils.Controller;

public class RobotController implements Controller {
    private MouseController mouseController;
    private KeyboardController keyboardController;
    private boolean gameMode = false;

    public RobotController() {
        mouseController = new MouseController();
        keyboardController = new KeyboardController();
    }
    
    public RobotController(boolean gameMode) {
        this();
        this.gameMode = true;
    }

    @Override
    public void orient(double x, double y, double z) {
        if (gameMode) {
            mouseController.moveGame(-z * 2.5f, y * 2.0f);
        } else {
            mouseController.moveRelative(-z * 2.5f, -x * 2.0f);
        }
    }

    @Override
    public void move(int x, int y) {
        keyboardController.move(x, y);
    }

    @Override
    public void clickLeftDown() {
        mouseController.clickLeftDown();
    }

    @Override
    public void clickLeftUp() {
        mouseController.clickLeftUp();
    }

    @Override
    public void clickRightDown() {
        mouseController.clickRightDown();
    }

    @Override
    public void clickRightUp() {
        mouseController.clickRightUp();
    }

    @Override
    public void startCursorControl() throws IOException {
        
    }

    @Override
    public void stopCursorControl() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rotate(double parseDouble, double parseDouble2)
            throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stopRotating() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clickLeft() throws IOException {
        mouseController.clickLeft();
    }
}
