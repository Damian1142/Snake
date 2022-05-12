package com.mech.input;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import com.mech.Main;
import com.mech.view.Direction;
import com.mech.view.MainPanel;
import com.mech.view.ViewController;
import com.mech.view.przeszkody.SpawnType;
import com.mech.view.przeszkody.Spawner;

import javax.swing.*;

public class InputKey extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W: {
                if (Main.view.mainPanel.snake.getSnakeDirection() != Direction.DOWN)
                    Main.view.mainPanel.snake.setSnakeDirection(Direction.UP);
                break;
            }
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S: {
                if (Main.view.mainPanel.snake.getSnakeDirection() != Direction.UP)
                Main.view.mainPanel.snake.setSnakeDirection(Direction.DOWN);
                    break;
            }
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A: {
                if (Main.view.mainPanel.snake.getSnakeDirection() != Direction.RIGHT)
                Main.view.mainPanel.snake.setSnakeDirection(Direction.LEFT);
                break;
            }
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D: {
                if (Main.view.mainPanel.snake.getSnakeDirection() != Direction.LEFT)
                Main.view.mainPanel.snake.setSnakeDirection(Direction.RIGHT);
                break;
            }
            case KeyEvent.VK_ESCAPE: {
                if (MainPanel.timer.isRunning()) {
                    MainPanel.timer.stop();
                    Spawner.stop();
                }
                else {
                    MainPanel.timer.start();
                    Spawner.start();
                }

                break;
            }
            case KeyEvent.VK_R: {
                reset();
            }
        }
    }

    public static void reset() {
        System.out.println("Reset");
        List<Point> body = Main.view.mainPanel.snake.getBody();
        body.clear();
        body.add(new Point(17,15));
        body.add(new Point(17,16));
        body.add(new Point(17,17));
        Spawner.przeszkody.clear();
        Spawner.spawn(SpawnType.FOOD);
        Main.view.mainPanel.snake.setSnakeDirection(Direction.UP);
        MainPanel.timer.start();
        Spawner.start();
    }
}
