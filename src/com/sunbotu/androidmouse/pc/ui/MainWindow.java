package com.sunbotu.androidmouse.pc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sunbotu.androidmouse.pc.controller.SocketServer;
import com.sunbotu.androidmouse.pc.utils.IpGetter;

public class MainWindow {
  private UIController controller;

  public MainWindow(SocketServer server) {
    // Frames
    // JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame mainFrame = new JFrame("Android Mouse PC Server");
    mainFrame.setSize(400, 80);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);

    JButton startButton = new JButton("Start server");
    JLabel connectionInfo = new JLabel("Disconnected!");
    // Controller
    controller = new UIController(server, startButton, connectionInfo);

    JLabel ipLabel = new JLabel("Your local IP address:\n"
        + IpGetter.getWlanIpAddr());
    mainFrame.add(ipLabel, BorderLayout.NORTH);

    ActionListener buttonListener = new ActionListener(controller);

    
    startButton.setPreferredSize(new Dimension(100, 20));
    
    startButton.setActionCommand("start");
    startButton.addActionListener(buttonListener);
    mainFrame.add(startButton, BorderLayout.CENTER);
    
    
    mainFrame.add(connectionInfo, BorderLayout.SOUTH);
    
    mainFrame.setVisible(true);
 
  }

}
