package com.mech.view;

import com.mech.input.InputKey;
import com.mech.snake.Snake;
import com.mech.view.przeszkody.Przeszkoda;
import com.mech.view.przeszkody.PrzeszkodaSpawner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    public MainPanel() {
        setPreferredSize(new Dimension(Board.MAX_X,Board.MAX_Y));
        snake = new Snake();
        timer = new Timer(100, e -> {
            if (snake.isCollision()) MainPanel.timer.stop();
            snake.move();
            repaint();
        });
        timer.start();
        addAllComponent();
        staticSnake = snake;
        PrzeszkodaSpawner spawner = new PrzeszkodaSpawner();
    }
    public static Snake staticSnake;

    private static final ArrayList<Drawing> drawings = new ArrayList<>();
    private final Snake snake;
    public static Timer timer;

    @Override
    protected void paintComponent(Graphics g) {
        Board.draw(g);
        for (Przeszkoda p : PrzeszkodaSpawner.przeszkody){
            p.draw(g);
        }
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
