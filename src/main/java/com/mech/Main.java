package com.mech;

import com.mech.view.ViewController;
import lombok.SneakyThrows;

import java.awt.*;

public class Main {
    public static ViewController view;
    @SneakyThrows
    public static void main(String[] args) {
        //ProcessBuilder pb = new ProcessBuilder("game.bat");
        //pb.start();
        EventQueue.invokeLater(() -> {
            ViewController controller = new ViewController();
            view = controller;
            controller.setVisible(true);
        });
    }
}
