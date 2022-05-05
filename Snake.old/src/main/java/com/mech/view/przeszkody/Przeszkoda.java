package com.mech.view.przeszkody;

import com.mech.view.Board;
import com.mech.view.Drawing;

import java.awt.*;

public class Przeszkoda extends Point implements Drawing {

    public Przeszkoda() {
    }

    public Przeszkoda(Point p) {
        super(p);
    }

    public Przeszkoda(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x * Board.SIZE,y * Board.SIZE, Board.SIZE,Board.SIZE);
    }
}
