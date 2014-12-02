package com.sunbotu.androidmouse.pc;

public class MessageDecoder {
  final static String MOUSE_LEFT_BTN_DOWN = "LD";
  final static String MOUSE_LEFT_BTN_UP = "LU";
  final static String MOUSE_RIGHT_BTN_DOWN = "RD";
  final static String MOUSE_RIGHT_BTN_UP = "RU";
  final static String MOUSE_MIDDLE_BTN_DOWN = "MD";
  final static String MOUSE_MIDDLE_BTN_UP = "MU";
  final static String LOCATION = "XY";
  
  private MouseControl controller;
  
  public MessageDecoder() {
    controller = new MouseControl();
  }
  
  public void decode(String message) {
    String[] pairs = message.split("_");
    switch (pairs[0]) {
      case LOCATION:
        double tilt = Double.parseDouble(pairs[1]);
        double rotation = Double.parseDouble(pairs[2]);
        controller.moveRelative(tilt, rotation);
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
      default:
        System.err.println("Unknown message: " + message);
        break;
    }
    
  }
}
