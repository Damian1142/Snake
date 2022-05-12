package com.mech.view.przeszkody;

import com.mech.view.Board;
import com.mech.view.Drawing;
import com.mech.view.MainPanel;

import java.awt.*;

public class Food extends Point implements Drawing {
    public Food() {
    }

    public Food(Point p) {
        super(p);
    }

    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x * Board.SIZE,y * Board.SIZE,Board.SIZE,Board.SIZE);



    }
}
