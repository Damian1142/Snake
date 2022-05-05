package com.mech;

import com.mech.view.ViewController;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ViewController controller = new ViewController();
            controller.setVisible(true);
        });
    }
}
