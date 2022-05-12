package com.mech.view;

import java.awt.*;

import javax.swing.*;

public class ViewController extends JFrame {

    public static JLabel score;
    public static ViewController viewController;
    public ViewController() throws HeadlessException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");

        score = new JLabel("Wynik: 0", SwingConstants.CENTER);
        mainPanel = new MainPanel();
        add(mainPanel);
        add(score,BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        viewController = this;
    }
    public MainPanel mainPanel;
}
//class GameMenu extends JDialog {
//
//    public GameMenu(Frame owner) {
//        super(owner,"Tryb Gry",true);
//        setSize(new Dimension(600,300));
//        setResizable(false);
//        setLocationRelativeTo(null);
//        JLabel jLabel = new JLabel("Wybierz tryb Gry",SwingConstants.CENTER);
//        jLabel.setFont(new Font("Arial", Font.PLAIN,30));
//        add(jLabel, BorderLayout.NORTH);
//    }
//}
