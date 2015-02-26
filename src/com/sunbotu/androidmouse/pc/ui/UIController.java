package com.sunbotu.androidmouse.pc.ui;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.sunbotu.androidmouse.pc.connection.SocketServer;

public class UIController {
    private enum State {
        IDLE, STARTED
    }

    private State state;
    private SocketServer server;
    private JButton startButton;
    private JLabel connectionInfoLabel;

    public UIController(final SocketServer server, final JButton startButton,
            final JLabel connectionInfo) {
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
        case "2d_mode":
            server.setMode("2D");
            break;
        case "3d_mode":
            server.setMode("3D");
            break;
        case "3d_game_mode":
            server.setMode("3D_GAME");
            break;
        default:
            System.err.println("Unknown action command: " + actionCommand);
        }
    }
}
