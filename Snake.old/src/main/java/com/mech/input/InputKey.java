package com.mech.input;

import com.mech.snake.Snake;
import com.mech.view.Direction;
import com.mech.view.MainPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputKey extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                if (MainPanel.staticSnake.getSnakeDirection() != Direction.DOWN)
                    MainPanel.staticSnake.setSnakeDirection(Direction.UP);
            }
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                if (MainPanel.staticSnake.getSnakeDirection() != Direction.UP)
                    MainPanel.staticSnake.setSnakeDirection(Direction.DOWN);
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                if (MainPanel.staticSnake.getSnakeDirection() != Direction.RIGHT)
                    MainPanel.staticSnake.setSnakeDirection(Direction.LEFT);
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                if (MainPanel.staticSnake.getSnakeDirection() != Direction.LEFT)
                    MainPanel.staticSnake.setSnakeDirection(Direction.RIGHT);
            }
            case KeyEvent.VK_ESCAPE -> {
                if (MainPanel.timer.isRunning()) MainPanel.timer.stop();
                else MainPanel.timer.start();

            }
        }
    }
}
