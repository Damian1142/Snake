package com.mech.view.przeszkody;

import com.mech.view.Board;
import com.mech.view.Drawing;

import java.awt.*;

public class Obstacle extends Point implements Drawing {

    public Obstacle() {
    }

    public Obstacle(Point p) {
        super(p);
    }

    public Obstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x * Board.SIZE,y * Board.SIZE, Board.SIZE,Board.SIZE);
    }
    public void draw(Graphics g,Point c) {
        g.setColor(Color.MAGENTA);
        g.fillRect((x - c.x) * Board.SIZE,(y - c.y) * Board.SIZE, Board.SIZE,Board.SIZE);
    }
}
