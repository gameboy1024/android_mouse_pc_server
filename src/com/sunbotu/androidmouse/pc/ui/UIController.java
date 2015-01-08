package com.sunbotu.androidmouse.pc.ui;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.sunbotu.androidmouse.pc.controller.SocketServer;

public class UIController {
  private enum State {
    IDLE, STARTED
  }

  private State state;
  private SocketServer server;
  private JButton startButton;
  private JLabel connectionInfoLabel;

  public UIController(final SocketServer server, final JButton startButton, final JLabel connectionInfo) {
    this.server = server;
    this.server.setActionListener(new ActionListener(this));
    state = State.IDLE;
    this.startButton = startButton;
    connectionInfoLabel = connectionInfo;
  }

  public void processEvent(String actionCommand) {
    switch (actionCommand) {
    case "start":
      if (state == State.IDLE) {
        server.start();
        state = State.STARTED;
        startButton.setText("Stop server");
      } else {
        server.stop();
        state = State.IDLE;
        startButton.setText("Start server");
      }
      break;
    case "connected":
      connectionInfoLabel.setText("Connected!");
      break;
    case "disconnected":
      connectionInfoLabel.setText("Disconnected!");
      break;
    default:
      System.err.println("Unknown action command: " + actionCommand);
    }
  }
}
