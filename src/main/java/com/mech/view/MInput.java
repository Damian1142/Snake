package com.mech.view;

import javax.swing.*;
import java.awt.*;

class MInput extends JDialog {

    private JLabel jInfo;
    private JTextField jIP;
    private JTextField jName;
    private JButton jConnect;

    public MInput(Frame owner) {
        super(owner, "Połączenie", true);
        setSize(400, 600);
        setLayout(new FlowLayout());
        jInfo = new JLabel("Podaj Dane", SwingConstants.CENTER);
        jIP = new JTextField("IP");
        jName = new JTextField("Nazwa");
        jConnect = new JButton("Połącz");
        jInfo.setFont(new Font("Arial", Font.PLAIN,30));
        jIP.setFont(new Font("Arial", Font.PLAIN,30));
        jName.setFont(new Font("Arial", Font.PLAIN,30));
        jConnect.setFont(new Font("Arial", Font.PLAIN,30));
        jConnect.addActionListener(e -> {
            setVisible(false);
            repaint();
        });
        add(jInfo);
        add(jIP);
        add(jName);
        add(jConnect);

    }
}