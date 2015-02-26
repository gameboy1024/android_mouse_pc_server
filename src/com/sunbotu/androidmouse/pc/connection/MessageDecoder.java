package com.sunbotu.androidmouse.pc.connection;

import java.io.IOException;

import com.sunbotu.androidmouse.pc.controller2d.RobotController;
import com.sunbotu.androidmouse.pc.controller3d.Navigation3DSocketConnector;
import com.sunbotu.androidmouse.pc.utils.Controller;

public class MessageDecoder {
    final static String MOUSE_LEFT_BTN_DOWN = "LD";
    final static String MOUSE_LEFT_BTN_UP = "LU";
    final static String MOUSE_RIGHT_BTN_DOWN = "RD";
    final static String MOUSE_RIGHT_BTN_UP = "RU";
    final static String MOUSE_MIDDLE_BTN_DOWN = "MD";
    final static String MOUSE_MIDDLE_BTN_UP = "MU";
    final static String MOUSE_LEFT_CLICK = "LC";
    final static String LOCATION = "XY";
    final static String ROTATE = "RT";
    final static String ROTATE_STOP = "R0";
    final static String MOVE = "MV";
    final static String CURSOR_START = "C1";
    final static String CURSOR_STOP = "C2";

    private Controller controller;

    public MessageDecoder(String mode) {
        switch (mode) {
        case "2D":
            controller = new RobotController();
            break;
        case "3D":
            controller = new Navigation3DSocketConnector(); 
            break;
        case "3D_GAME":
            controller = new RobotController(true);
            break;
        default:
            // TODO: handle this.
            controller = null;
        }
    }

    public void decode(String message) {
        // System.out.println(message);
        String[] pairs = message.split("_");
        try {
            switch (pairs[0]) {
            case LOCATION:
                controller.orient(Double.parseDouble(pairs[1]),
                        Double.parseDouble(pairs[2]),
                        Double.parseDouble(pairs[3]));
                break;
            case ROTATE:
                controller.rotate(Double.parseDouble(pairs[1]),
                        Double.parseDouble(pairs[2]));
                break;
            case MOVE:
                controller.move(Integer.parseInt(pairs[1]),
                        Integer.parseInt(pairs[2]));
                break;
            case MOUSE_LEFT_BTN_DOWN:
                controller.clickLeftDown();
                break;
            case MOUSE_LEFT_BTN_UP:
                controller.clickLeftUp();
                break;
            case MOUSE_RIGHT_BTN_DOWN:
                controller.clickRightDown();
                break;
            case MOUSE_RIGHT_BTN_UP:
                controller.clickRightUp();
                break;
            case MOUSE_LEFT_CLICK:
                controller.clickLeft();
                break;
            case CURSOR_START:
                controller.startCursorControl();
                break;
            case CURSOR_STOP:
                controller.stopCursorControl();
                break;
            case ROTATE_STOP:
                controller.stopRotating();
                break;
            default:
                System.err.println("Unknown message: " + message);
                break;
            }
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
