package com.mech.multiplayer;

import com.mech.Main;
import com.mech.snake.Snake;
import com.mech.view.Board;
import com.mech.view.przeszkody.Food;
import com.mech.view.przeszkody.Obstacle;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;

@Data
public class Map {

    private ArrayList<Obstacle> obstacles;
    private ArrayList<Food> foods;
    private ArrayList<Snake> snakes;

    public void draw(Graphics g,Point p) {
        if(obstacles != null)
            obstacles.forEach(o -> o.draw(g,p));
        if(foods != null)
            foods.forEach(f -> f.draw(g,p));
        if(snakes != null)
            snakes.forEach(s -> {
                //snakes.forEach(snake -> System.out.println(snake.name));
                //System.out.println(Main.view.mainPanel.snake.name);
                if (!s.name.equals(Main.view.mainPanel.snake.name)) {
                    s.setColor(Color.ORANGE);
                    s.draw(g,p);
                }
                else {
                    //System.out.println();
                    s.draw(g,p);
                    Main.view.mainPanel.camera.setLocation(s.getBody().get(0).x - Board.FIELD_X / 2,s.getBody().get(0).y - Board.FIELD_Y / 2);
                    //System.out.println(s.getBody().get(0));
                }

            });

    }
}
