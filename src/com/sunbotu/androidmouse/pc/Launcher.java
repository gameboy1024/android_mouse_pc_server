package com.sunbotu.androidmouse.pc;

import com.sunbotu.androidmouse.pc.connection.SocketServer;
import com.sunbotu.androidmouse.pc.ui.MainWindow;

public class Launcher {

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        @SuppressWarnings("unused")
        MainWindow mainWindow = new MainWindow(server);
    }
}
