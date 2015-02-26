package com.sunbotu.androidmouse.pc.controller3d;

import java.util.LinkedList;

import com.sunbotu.androidmouse.pc.utils.Tuple3D;

/**
 * This class smoothen the control by using a filter on the mouse moving data.
 * 
 * @author Botu Sun
 *
 */
public class Navigation3DFilter {
    public static final int SMOOTH_N_TH = 10;
    public static final double gradient = 0.01;

    private LinkedList<Tuple3D> previousPoints;
    private double[] coeffs;

    public Navigation3DFilter() {
        previousPoints = new LinkedList<Tuple3D>();
        coeffs = getFilterCoeff(0, SMOOTH_N_TH);
    }

    public Tuple3D filter(double x, double y, double z) {
        previousPoints.push(new Tuple3D(x, y, z));
        while (previousPoints.size() > SMOOTH_N_TH) {
            previousPoints.removeLast();
        }
        double x_sum = 0;
        double y_sum = 0;
        double z_sum = 0;
        int i = 0;
        for (Tuple3D tuple3D : previousPoints) {
            x_sum += tuple3D.x * coeffs[i];
            y_sum += tuple3D.y * coeffs[i];
            z_sum += tuple3D.z * coeffs[i];
        }
        return new Tuple3D(x_sum, y_sum, z_sum);
    }

    /**
     * Generate a series of coefficients used by the filter. The sum of all
     * coefficients is 1.
     * 
     * @param gradient
     *            The difference between two successive coefficients.
     * @param n
     *            The total number of coefficients.
     * @return Array of coefficients
     */
    private double[] getFilterCoeff(double gradient, int n) {
        double[] result = new double[n];
        double tmp = (2.0 / n - (n - 1) * gradient) * 0.5;
        for (int i = 0; i < n; i++) {
            result[i] = tmp;
            tmp += gradient;
        }
        return result;
    }
}
