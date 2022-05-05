package com.mech.view.przeszkody;

import com.mech.view.Board;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Spawner {
    private static int x = Board.FIELD_X,y = Board.FIELD_Y;

    private static final Timer timer = new Timer(5000, e -> spawn(SpawnType.OBSTACLE));
    public static ArrayList<Obstacle> przeszkody = new ArrayList<>();
    public static Food food;

    public static void spawn(SpawnType type){
        switch (type){
            case OBSTACLE: przeszkody.add(new Obstacle(new Random().nextInt(Board.FIELD_X),new Random().nextInt(Board.FIELD_Y)));
                break;
            case FOOD: { boolean b = true;
                Food f = null;
                while (b) {
                f = new Food(new Random().nextInt(x - 1), new Random().nextInt(y - 1));
                if (przeszkody.isEmpty()){food = f;return;}
                for (Obstacle o : przeszkody) {
                    b = o.getLocation().equals(f.getLocation());
                    if(b) break;
                }
            }
            food = f;
                break;
            }
        }

    }

    public static void start() {
        timer.start();
    }

    public static void stop() {
        timer.stop();
    }
    static {
        spawn(SpawnType.FOOD);
    }
}
