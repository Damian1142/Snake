package com.mech.snake;

import com.mech.view.*;
import com.mech.view.przeszkody.Obstacle;
import com.mech.view.przeszkody.Spawner;
import lombok.Getter;
import lombok.Setter;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Snake implements Drawing {

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
//        timer = new Timer(10000, e -> {
//            body.add(new Point(0,0));
//            wynik++;
//            ViewController.score.setText("Wynik: " + wynik);
//        });
//        timer.start();
    }
    //private final Timer timer;
    private Direction snakeDirection;

    public void move() {
        for (int i = body.size() - 1; i > 0 ; i--) {
            body.get(i).setLocation(body.get(i - 1));
        }

        switch (snakeDirection){

            case UP: body.get(0).y--;break;
            case DOWN: body.get(0).y++;break;
            case RIGHT: body.get(0).x++;break;
            case LEFT: body.get(0).x--;break;
        }
    }

    private final List<Point> body;

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
        for (Obstacle p: Spawner.przeszkody){
            if (head.equals(p)){
                return true;
            }
        }
        for (Point p: body.subList(1, body.size())){
            if (p.equals(head)){
                return true;
            }
        }
        return false;
    }
    public void addBody(){
        body.add(new Point(0,0));
    }

    public boolean eat() {
        Point head = body.get(0);
        return Spawner.food.getLocation().equals(head);
    }
}
