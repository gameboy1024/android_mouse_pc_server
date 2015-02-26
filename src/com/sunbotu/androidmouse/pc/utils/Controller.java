package com.sunbotu.androidmouse.pc.utils;

import java.io.IOException;

public interface Controller {

    void orient(double x, double y, double z) throws IOException;

    void move(int x, int y) throws IOException;

    void clickLeftDown() throws IOException;

    void clickLeftUp() throws IOException;
    
    void clickLeft() throws IOException;

    void clickRightDown() throws IOException;

    void clickRightUp() throws IOException;

    void startCursorControl() throws IOException;
    
    void stopCursorControl() throws IOException;

    void rotate(double parseDouble, double parseDouble2) throws IOException;
    
    void stopRotating() throws IOException;

}
