package com.mech.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.mech.view.Direction;
import com.mech.view.MainPanel;

public class InputKey extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W: {
                //if (MainPanel.staticSnake.getSnakeDirection() != Direction.DOWN)
                    MainPanel.staticSnake.setSnakeDirection(Direction.UP);
                break;
            }
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S: {
                //if (MainPanel.staticSnake.getSnakeDirection() != Direction.UP)
                    MainPanel.staticSnake.setSnakeDirection(Direction.DOWN);
                    break;
            }
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A: {
                //if (MainPanel.staticSnake.getSnakeDirection() != Direction.RIGHT)
                    MainPanel.staticSnake.setSnakeDirection(Direction.LEFT);
                break;
            }
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D: {
                //if (MainPanel.staticSnake.getSnakeDirection() != Direction.LEFT)
                    MainPanel.staticSnake.setSnakeDirection(Direction.RIGHT);
                break;
            }
            case KeyEvent.VK_ESCAPE: {
                if (MainPanel.timer.isRunning()) MainPanel.timer.stop();
                else MainPanel.timer.start();
                break;
            }
        }
    }
}
