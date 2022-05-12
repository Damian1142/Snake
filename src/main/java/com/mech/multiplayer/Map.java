package com.mech.multiplayer;

import com.mech.snake.Snake;
import com.mech.view.Drawing;
import com.mech.view.przeszkody.Food;
import com.mech.view.przeszkody.Obstacle;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.util.ArrayList;

@Data
public class Map implements Drawing {

    private ArrayList<Obstacle> obstacles;
    private ArrayList<Food> foods;
    private ArrayList<Snake> snakes;

    @Override
    public void draw(Graphics g) {
        if(obstacles != null)
            obstacles.forEach(o -> o.draw(g));
        if(foods != null)
            foods.forEach(f -> f.draw(g));
        if(snakes != null)
            snakes.forEach(s -> s.draw(g));

    }
}