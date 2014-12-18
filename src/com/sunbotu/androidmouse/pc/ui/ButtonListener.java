package com.sunbotu.androidmouse.pc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
  private UIController controller;
  
  public ButtonListener(UIController controller) {
    this.controller = controller;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    controller.processEvent(e.getActionCommand());
  }

}
