package com.sunbotu.androidmouse.pc.controller;

import java.util.LinkedList;

import com.sunbotu.androidmouse.pc.utils.Point;

/**
 * This class smoothen the control by using a filter on the mouse moving data.
 * 
 * @author Botu Sun
 *
 */
public class MouseMoveFilter {
  public static final int SMOOTH_N_TH = 10;
  public static final double gradient = 0.01;

  private LinkedList<Point> previousPoints;
  private double[] coeffs;

  public MouseMoveFilter() {
    previousPoints = new LinkedList<Point>();
    coeffs = getFilterCoeff(0, SMOOTH_N_TH);
  }

  public Point filter(int x, int y) {
    previousPoints.push(new Point(x, y));
    while (previousPoints.size() >= SMOOTH_N_TH) {
      previousPoints.removeLast();
    }

    double x_sum = 0;
    double y_sum = 0;
    int i = 0;
    for (Point point : previousPoints) {
      x_sum += point.x * coeffs[i];
      y_sum += point.y * coeffs[i];
    }
    return new Point((int) x_sum, (int) y_sum);
  }

  /**
   * Generate a series of coefficients used by the filter. The sum of all
   * coefficients is 1.
   * 
   * @param gradient
   *          The difference between two successive coefficients.
   * @param n
   *          The total number of coefficients.
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
