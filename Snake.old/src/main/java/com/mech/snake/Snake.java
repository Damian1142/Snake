package com.mech.snake;

import com.mech.view.*;
import com.mech.view.przeszkody.Przeszkoda;
import com.mech.view.przeszkody.PrzeszkodaSpawner;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Snake implements Drawing {

    private int wynik = 0;

    public Direction getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(Direction snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(17,15));
        body.add(new Point(17,16));
        body.add(new Point(17,17));
        MainPanel.addDrawings(this);

        snakeDirection = Direction.UP;
        timer = new Timer(10000, e -> {
            body.add(new Point(0,0));
            wynik++;
            ViewController.score.setText("Wynik: " + wynik);
        });
        timer.start();
    }
    private final Timer timer;
    private Direction snakeDirection;

    public void move() {
        for (int i = body.size() - 1; i > 0 ; i--) {
            body.get(i).setLocation(body.get(i - 1));
        }

        switch (snakeDirection){

            case UP -> body.get(0).y--;
            case DOWN -> body.get(0).y++;
            case RIGHT -> body.get(0).x++;
            case LEFT -> body.get(0).x--;
        }
    }

    private List<Point> body;

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        for (Point p: body.subList(1,body.size())){
            g.fillRect(p.x * Board.SIZE,p.y * Board.SIZE,Board.SIZE,Board.SIZE);
        }
        g.setColor(Color.WHITE);
        g.fillRect(body.get(0).x * Board.SIZE,body.get(0).y * Board.SIZE,Board.SIZE,Board.SIZE);
    }

    public boolean isCollision() {
        Point head = body.get(0);
        if (head.x < 0 || head.x > Board.FIELD_X || head.y < 0 || head.y > Board.FIELD_Y ){
            return true;
        }
        for (Przeszkoda p: PrzeszkodaSpawner.przeszkody){
            if (head.equals(p)){
                return true;
            }
        }
        return false;
    }
}
