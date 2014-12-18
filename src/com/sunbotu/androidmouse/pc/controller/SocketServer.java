package com.sunbotu.androidmouse.pc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

  private static ServerSocket serverSocket;
  private static Socket clientSocket;
  private static InputStreamReader inputStreamReader;
  private static BufferedReader bufferedReader;
  private static String message;

  public SocketServer() {}
  
  public void start() {
    MessageDecoder decoder = new MessageDecoder();

    try {
      serverSocket = new ServerSocket(4445); // Server socket

    } catch (IOException e) {
      System.out.println("Could not listen on port: 4445");
    }
    System.out.println("Server started. Listening to the port 4445");

    while (true) {
      try {
        // Accept the client
        clientSocket = serverSocket.accept(); 
        System.out.println("New client connected!");
        // Connection
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        // Client
        while (true) {
          message = bufferedReader.readLine();
          if (message == null) {
            break;
          } else {
            decoder.decode(message);
          }
        }
        inputStreamReader.close();
        clientSocket.close();
      } catch (IOException ex) {
        System.out.println("Problem in message reading");
      }
    }
  }
}
