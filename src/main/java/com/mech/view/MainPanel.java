package com.mech.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.mech.snake.Snake;
import com.mech.view.przeszkody.Obstacle;
import com.mech.view.przeszkody.SpawnType;
import com.mech.view.przeszkody.Spawner;

public class MainPanel extends JPanel {
    public MainPanel() {
        setPreferredSize(new Dimension(Board.MAX_X,Board.MAX_Y));
        snake = new Snake();
        Spawner.start();
        timer = new Timer(100, e -> {
            if (snake.isCollision()){
                MainPanel.timer.stop();
                Spawner.stop();
            } else {
                snake.move();
            }
            if (snake.eat()){
                Spawner.spawn(SpawnType.FOOD);
                snake.addBody();
            }
            repaint();
        });
        timer.start();
        addAllComponent();
        staticSnake = snake;
    }
    public static Snake staticSnake;

    private static final ArrayList<Drawing> drawings = new ArrayList<>();
    private final Snake snake;
    public static Timer timer;

    @Override
    protected void paintComponent(Graphics g) {
        Board.draw(g);
        for (Obstacle p : Spawner.przeszkody){
            p.draw(g);
        }
        Spawner.food.draw(g);
        for (Drawing d: drawings){
            d.draw(g);
        }

    }
    public static void addDrawings(Drawing d){
        drawings.add(d);
    }
    private static final ArrayList<Drawing> elementList = new ArrayList<>();

    public void addAllComponent(){
        elementList.add(snake);
    }
}
