package com.sunbotu.androidmouse.pc.ui;

import java.lang.Thread.State;

import com.sunbotu.androidmouse.pc.controller.SocketServer;

public class UIController {
  private enum State {IDLE, STARTED}
  private State state;
  private SocketServer server;
  
  public UIController(SocketServer server) {
    this.server = server;
    state = State.IDLE;
  }
  
  public void processEvent(String actionCommand) {
    switch (actionCommand) {
    case "start":
      new Thread(new Runnable() {
        
        @Override
        public void run() {
          server.start();
          
        }
      }).start();
      state = State.STARTED;
    }
  }
}
