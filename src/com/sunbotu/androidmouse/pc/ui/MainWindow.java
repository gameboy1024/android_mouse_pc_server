package com.sunbotu.androidmouse.pc.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.sunbotu.androidmouse.pc.controller.SocketServer;

public class MainWindow {
  private UIController controller;
  
  public MainWindow(SocketServer server) {
    // Frames
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame mainFrame = new JFrame("Android Mouse PC Server");
    mainFrame.setSize(600, 400);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    
    // Controller
    controller = new UIController(server);
    ButtonListener buttonListener = new ButtonListener(controller);
    
    JButton startButton = new JButton("Start server");
    startButton.setActionCommand("start");
    startButton.addActionListener(buttonListener);
    
    mainFrame.add(startButton);
    mainFrame.setVisible(true);
  }
  
  
}
