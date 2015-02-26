package com.sunbotu.androidmouse.pc.controller3d;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Quat4f;

import com.sunbotu.androidmouse.pc.utils.Controller;
import com.sunbotu.androidmouse.pc.utils.Tuple3D;

/**
 * This class connects this server with the 3D navigation java application. The
 * raw data received from Android app will be processed and sent there. The
 * communication protocol can be located in Pilotage.java.
 * 
 * @author Botu Sun
 *
 */
public class Navigation3DSocketConnector implements Controller {
    private Navigation3DFilter filter;
    private Socket socket;
    private ObjectOutputStream outputStream;

    public Navigation3DSocketConnector() {
        filter = new Navigation3DFilter();
        try {
            socket = new Socket("localhost", 4446);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private synchronized void sendMessage(ObjectOutputStream outputStream,
            String obj, String cmd, HashMap<String, Object> hashmap)
            throws IOException {
        outputStream.writeObject(obj);
        outputStream.writeObject(cmd);
        outputStream.writeObject(hashmap);
    }

    @Override
    /**
     * This method takes x, y as input and transform into quaternion and
     * send to navigation application.  
     * @param x
     * @param y
     * @throws IOException
     */
    public void orient(double x, double y, double z) throws IOException {
        Tuple3D tuple3D = filter.filter(x, y, z);
        Quat4f quat = Cartesian2Quaternion(tuple3D.x, tuple3D.y, tuple3D.z);
        HashMap<String, Object> cod = new HashMap<String, Object>();
        cod.put("x", (double) quat.getX());
        cod.put("y", (double) quat.getY());
        cod.put("z", (double) quat.getZ());
        cod.put("w", (double) quat.getW());
        sendMessage(outputStream, "head", "orientate", cod);
    }

    @Override
    public void rotate(double x, double y) throws IOException {
        double sensibility = 0.001;
        Quat4f quat = Cartesian2Quaternion(y, 0, -x);
        HashMap<String, Object> cod = new HashMap<String, Object>();
        cod.put("x", (double) quat.getX() * sensibility);
        cod.put("y", (double) quat.getY() * sensibility);
        cod.put("z", (double) quat.getZ() * sensibility);
        cod.put("w", (double) quat.getW());
        sendMessage(outputStream, "viewpoint", "rotate", cod);
    }
    
    @Override
    public void stopRotating() throws IOException {
        sendMessage(outputStream, "reset", "rotate",
                new HashMap<String, Object>());
    }

    @Override
    public void move(int x, int y) throws IOException {
        if (Math.abs(x) < 3) x = 0;
        if (Math.abs(y) < 3) y = 0;
        double sensibility = 0.0005;
        HashMap<String, Object> cod = new HashMap<String, Object>();
        cod.put("x", x * sensibility);
        cod.put("y", (double) 0);
        cod.put("z", -y * sensibility);
        sendMessage(outputStream, "viewpoint", "translate", cod);
    }

    @Override
    public void clickLeftDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clickLeftUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clickRightDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clickRightUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void startCursorControl() throws IOException {
        sendMessage(outputStream, "reset_anchor", "orientate",
                new HashMap<String, Object>());
    }

    @Override
    public void stopCursorControl() throws IOException {
        sendMessage(outputStream, "reset", "orientate",
                new HashMap<String, Object>());
    }

    public static Quat4f Cartesian2Quaternion(double x, double y, double z) {
        double sensibility = 10;
        double azimuth = Math.toRadians(z * sensibility);
        double pitch = Math.toRadians(x * sensibility);
        // double elevation = Math.toRadians(-y * sensibility * 0.5);
        double elevation = 0;
        Quat4f orientationF = new Quat4f();
        Quat4f orientationX = new Quat4f();
        orientationX.set(new AxisAngle4d(1, 0, 0, pitch));
        Quat4f orientationY = new Quat4f();
        orientationY.set(new AxisAngle4d(0, 1, 0, azimuth));
        Quat4f orientationZ = new Quat4f();
        orientationZ.set(new AxisAngle4d(0, 0, 1, elevation));
        orientationF.mul(orientationY, orientationX);
        orientationF.mul(orientationF, orientationZ);
        orientationF.normalize();
        return orientationF;
    }

    @Override
    public void clickLeft() throws IOException {
        // TODO Auto-generated method stub
        
    }
}
