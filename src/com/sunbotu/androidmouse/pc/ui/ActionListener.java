package com.sunbotu.androidmouse.pc.ui;

import java.awt.event.ActionEvent;

public class ActionListener implements java.awt.event.ActionListener {
  private UIController controller;
  
  public ActionListener(UIController controller) {
    this.controller = controller;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    controller.processEvent(e.getActionCommand());
  }

}
