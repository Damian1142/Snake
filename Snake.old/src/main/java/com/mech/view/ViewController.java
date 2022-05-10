package com.mech.view;

import com.mech.input.InputKey;
import com.mech.snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewController extends JFrame {

    public static JLabel score;
    public ViewController() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        mainPanel = new MainPanel();
        score = new JLabel("Wynik: ", SwingConstants.CENTER);

        add(mainPanel);
        add(score,BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(new InputKey());
    }
    public MainPanel mainPanel;

}
