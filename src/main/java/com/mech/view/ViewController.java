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
//class InputName extends JDialog {
//
//    public InputName(Frame owner) {
//        super(owner,"Wpisz nazwę",true);
//        setSize(new Dimension(200,300));
//        setResizable(false);
//        setLocationRelativeTo(null);
//        JLabel jLabel = new JLabel("Wpisz nazwę",SwingConstants.CENTER);
//        JButton jButton = new JButton("OK");
//        jButton.addActionListener(e -> {setName(jLabel.getText()); setVisible(false);});
//        jLabel.setFont(new Font("Arial", Font.PLAIN,30));
//        JTextField jTextField = new JTextField();
//        jTextField.setFont(new Font("Arial", Font.PLAIN,30));
//        jTextField.setSize(280,40);
//        add(jLabel, BorderLayout.NORTH);
//        add(jTextField, BorderLayout.CENTER);
//        add(jButton,BorderLayout.SOUTH);
//
//    }

