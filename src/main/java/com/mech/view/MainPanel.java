package com.mech.view;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import com.mech.Main;
import com.mech.input.InputKey;
import com.mech.snake.Snake;
import com.mech.view.przeszkody.Obstacle;
import com.mech.view.przeszkody.SpawnType;
import com.mech.view.przeszkody.Spawner;
import lombok.SneakyThrows;

public class MainPanel extends JPanel {
    @SneakyThrows
    public MainPanel() {
        drawings = new ArrayList<>();
        setPreferredSize(new Dimension(Board.MAX_X,Board.MAX_Y));
        snake = new Snake();
        Spawner.start();
        Point head = snake.getBody().get(0);
        timer = new Timer(100, e -> {
            if (snake.isCollision()){
                MainPanel.timer.stop();
                Spawner.stop();
                int odp = JOptionPane.showConfirmDialog(null,"    Przegrałeś!\nZ Wynikiem " + (snake.getBody().size() - 3) + " Punktów\n  Czy Zresetować?","Game Over", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if ( odp == JOptionPane.YES_OPTION) InputKey.reset();
                else if (odp == JOptionPane.CLOSED_OPTION)Main.view.dispose();
            } else {
                snake.move();
            }
            if (snake.eat()){
                Spawner.spawn(SpawnType.FOOD);
                snake.addBody();
                pick();
            }

            if(head.x < 0){
                head.setLocation(Board.FIELD_X - 1,head.y);
            }
            if(head.y < 0){
                head.setLocation(head.x,Board.FIELD_Y - 1);
            }
            if(head.y > Board.FIELD_Y){
                head.setLocation(head.x,0);
            }
            if(head.x > Board.FIELD_X){
                head.setLocation(0,head.y);
            }
            ViewController.score.setText("Wynik: " + (snake.getBody().size() - 3));
            repaint();
        });
        timer.start();
        addAllComponent();
        new Thread(() -> {
            try {
                // Open an audio input stream.
                URL url = this.getClass().getClassLoader().getResource("songs/YourLove.wav");
                assert url != null;
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                // Get a sound clip resource.
                Clip clip = AudioSystem.getClip();
                // Open audio clip and load samples from the audio input stream.
                clip.open(audioIn);
                clip.loop(10000);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
        //staticSnake = snake;
    }
    //public static Snake staticSnake;

    private static ArrayList<Drawing> drawings;
    public final Snake snake;
    public static Timer timer;

    private Sprite grass = new Sprite("textures/grass.png");
    @Override
    protected void paintComponent(Graphics g) {
        //Board.draw(g);
        g.drawImage(grass.getImage(),0,0,Board.MAX_X,Board.MAX_Y,null);
        for (Obstacle p : Spawner.przeszkody){
            p.draw(g);
        }
        Spawner.food.draw(g);
        for (Drawing d: drawings){
            d.draw(g);
        }

    }
    public static void addDrawings(Drawing d){
        drawings.add(d);
    }
    private static final ArrayList<Drawing> elementList = new ArrayList<>();

    public void addAllComponent(){
        elementList.add(snake);
    }
    @SneakyThrows
    public void pick(){
        URL pickURL = this.getClass().getClassLoader().getResource("songs/pick.wav");
        AudioInputStream pickIn;
        Clip clip;
        assert pickURL != null;
        pickIn = AudioSystem.getAudioInputStream(pickURL);
        clip = AudioSystem.getClip();
        clip.open(pickIn);
        clip.start();
    }
}
