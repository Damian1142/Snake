package com.mech.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

import com.mech.Main;
import com.mech.input.InputKey;
import com.mech.snake.Snake;
import com.mech.view.przeszkody.Obstacle;
import com.mech.view.przeszkody.SpawnType;
import com.mech.view.przeszkody.Spawner;

public class MainPanel extends JPanel {
    public MainPanel() {
        drawings = new ArrayList<>();
        setPreferredSize(new Dimension(Board.MAX_X,Board.MAX_Y));
        snake = new Snake();
        Spawner.start();
        timer = new Timer(100, e -> {
            if (snake.isCollision()){
                MainPanel.timer.stop();
                Spawner.stop();
                int odp = JOptionPane.showConfirmDialog(null,"    Przegrałeś!\nZ Wynikiem " + (snake.getBody().size() - 3) + " Punktów\n  Czy Zresetować?","Game Over", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if ( odp == JOptionPane.YES_OPTION) InputKey.reset();
                else if (odp == JOptionPane.CLOSED_OPTION)Main.view.dispose();
            } else {
                snake.move();
            }
            if (snake.eat()){
                Spawner.spawn(SpawnType.FOOD);
                snake.addBody();
            }
            ViewController.score.setText("Wynik: " + (snake.getBody().size() - 3));
            repaint();
        });
        timer.start();
        addAllComponent();
        //staticSnake = snake;
    }
    //public static Snake staticSnake;

    private static ArrayList<Drawing> drawings;
    public final Snake snake;
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
