package com.mech.view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.mech.input.InputKey;

public class ViewController extends JFrame {

    public static JLabel score;
    public static ViewController viewController;
    public ViewController() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        mainPanel = new MainPanel();
        score = new JLabel("Wynik: 0", SwingConstants.CENTER);

        add(mainPanel);
        add(score,BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(new InputKey());
        viewController = this;
    }
    public MainPanel mainPanel;

}
