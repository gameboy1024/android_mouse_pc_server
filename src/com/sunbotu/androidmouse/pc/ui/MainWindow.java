package com.sunbotu.androidmouse.pc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.sunbotu.androidmouse.pc.connection.SocketServer;
import com.sunbotu.androidmouse.pc.utils.IpGetter;

public class MainWindow {
    private UIController controller;

    public MainWindow(SocketServer server) {
        // Frames
        // JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame mainFrame = new JFrame("Android Mouse PC Server");
        mainFrame.setSize(400, 150);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        
        JRadioButton mode2dButton = new JRadioButton("2D");
        mode2dButton.setSelected(true);
        mode2dButton.setActionCommand("2d_mode");
        JRadioButton mode3dButton = new JRadioButton("3D");
        mode3dButton.setActionCommand("3d_mode");
        JRadioButton mode3dGameButton = new JRadioButton("3D Game");
        mode3dGameButton.setActionCommand("3d_game_mode");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(mode2dButton);
        buttonGroup.add(mode3dButton);
        buttonGroup.add(mode3dGameButton);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JPanel modePanel = new JPanel();

        modePanel.add(mode2dButton);
        modePanel.add(mode3dButton);
        modePanel.add(mode3dGameButton);
        JButton startButton = new JButton("Start server");
        JLabel connectionInfo = new JLabel("Disconnected!");
        // Controller
        controller = new UIController(server, startButton, connectionInfo);

        JLabel ipLabel = new JLabel("Your local IP address:\n"
                + IpGetter.getWlanIpAddr());
        mainFrame.add(ipLabel, BorderLayout.NORTH);

        ActionListener buttonListener = new ActionListener(controller);
        
        mode2dButton.addActionListener(buttonListener);
        mode3dButton.addActionListener(buttonListener);
        mode3dGameButton.addActionListener(buttonListener);
        startButton.setPreferredSize(new Dimension(100, 20));
        startButton.setActionCommand("start");
        startButton.addActionListener(buttonListener);
        buttonPanel.add(modePanel, BorderLayout.NORTH);
        buttonPanel.add(startButton, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
//        mainFrame.add(startButton, BorderLayout.CENTER);

        mainFrame.add(connectionInfo, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

    }

}
