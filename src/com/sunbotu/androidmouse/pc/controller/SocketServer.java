package com.sunbotu.androidmouse.pc.controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.sunbotu.androidmouse.pc.ui.ActionListener;

public class SocketServer {

  private ServerSocket serverSocket;
  private Socket clientSocket;
  private InputStreamReader inputStreamReader;
  private BufferedReader bufferedReader;
  private String message;
  private boolean running;
  private ActionListener actionListener;

  public SocketServer() {
  }

  public void start() {
    running = true;
    new Thread(new Runnable() {
      @Override
      public void run() {
        startInternal();
      }
    }).start();
  }

  public void stop() {
    running = false;
  }

  private void startInternal() {
    MessageDecoder decoder = new MessageDecoder();

    try {
      serverSocket = new ServerSocket(4445); // Server socket

    } catch (IOException e) {
      System.out.println("Could not listen on port: 4445");
    }
    System.out.println("Server started. Listening to the port 4445");

    while (running) {
      try {
        // Accept the client
        clientSocket = serverSocket.accept();
        System.out.println("New client connected!");
        actionListener.actionPerformed(new ActionEvent(this, 0, "connected"));
        // Connection
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        // Client
        while (running) {
          message = bufferedReader.readLine();
          if (message == null) {
            break;
          } else {
            decoder.decode(message);
          }
        }
        inputStreamReader.close();
        clientSocket.close();
        actionListener
            .actionPerformed(new ActionEvent(this, 0, "disconnected"));
      } catch (IOException ex) {
        System.out.println("Problem in message reading");
      }
    }
  }

  public void setActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }
}
