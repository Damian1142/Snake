package com.mech.view.przeszkody;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class PrzeszkodaSpawner {
    private Timer timer;
    public static ArrayList<Przeszkoda> przeszkody = new ArrayList<>();

    public PrzeszkodaSpawner() {
        timer = new Timer(10000,e -> {
            przeszkody.add(new Przeszkoda(1 + new Random().nextInt(21),1 + new Random().nextInt(21)));
            System.out.println("Nowa przeszkoda");
        });
        timer.start();
    }
}
