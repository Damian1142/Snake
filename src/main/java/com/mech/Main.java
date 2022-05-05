package com.mech;

import com.mech.view.ViewController;
import lombok.SneakyThrows;

import java.awt.*;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        //ProcessBuilder pb = new ProcessBuilder("game.bat");
        //pb.start();
        EventQueue.invokeLater(() -> {
            ViewController controller = new ViewController();
            controller.setVisible(true);
        });
    }
}
