package com.mech.multiplayer;

import com.mech.Main;
import com.mech.snake.Snake;
import com.mech.view.Board;
import com.mech.view.Drawing;
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
                if (s.name.equalsIgnoreCase(Main.view.mainPanel.snake.name)) {
                    s.setColor(Color.ORANGE);
                    s.draw(g,p);
                }
                else {
                    //System.out.println();
                    Main.view.mainPanel.camera.setLocation(s.getBody().get(0).x - Board.FIELD_X / 2,s.getBody().get(0).y - Board.FIELD_Y / 2);
                    s.draw(g,p);
                }

            });

    }
}
