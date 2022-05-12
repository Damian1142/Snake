package com.mech.view;

import com.mech.Main;
import com.mech.Textures;
import com.mech.input.InputKey;
import com.mech.input.InputKeyM;
import com.mech.multiplayer.Client;
import com.mech.snake.Snake;
import com.mech.view.przeszkody.Obstacle;
import com.mech.view.przeszkody.SpawnType;
import com.mech.view.przeszkody.Spawner;
import lombok.SneakyThrows;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    Tryb tryb;
    @SneakyThrows
    public MainPanel() {
        tryb = Tryb.MENU;
        drawings = new ArrayList<>();
        setPreferredSize(new Dimension(Board.MAX_X,Board.MAX_Y));
        snake = new Snake();
        Spawner.start();
        setFocusable(true);
        addKeyListener(new KeyController());
        addKeyListener(new InputKey());

        timer = new Timer(100, e -> {
            Point head = snake.getBody().get(0);
            if (snake.isCollision()){
                MainPanel.timer.stop();
                Spawner.stop();
                int odp = JOptionPane.showConfirmDialog(null,"    Przegrałeś!\nZ Wynikiem " + (snake.getBody().size() - 3) + " Punktów\n  Czy Zresetować?","Game Over", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if ( odp == JOptionPane.YES_OPTION) InputKey.reset();
                else goMenu();
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
                System.out.println("Ponorzej zera X");
            }
            if(head.y < 0){
                head.setLocation(head.x,Board.FIELD_Y - 1);
                System.out.println("Ponorzej zera Y");
            }
            if(head.y > Board.FIELD_Y){
                head.setLocation(head.x,0);
            }
            if(head.x > Board.FIELD_X){
                head.setLocation(0,head.y);
            }
            ViewController.score.setText("Wynik: " + (snake.getBody().size() - 3));
            //System.out.println(head.x + "x" + head.y);
        });
        new Thread(() -> {
            while(true)
                repaint();
        }).start();
        //timer.start();
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
        fps.start();
        //staticSnake = snake;
    }

    private void goMenu() {
        tryb = Tryb.MENU;
        timer.stop();
    }
    //public static Snake staticSnake;

    private static ArrayList<Drawing> drawings;
    public final Snake snake;
    public static Timer timer;

    private Timer fps = new Timer(1000, this::actionPerformed);
    private int frames = 0;
    @Override
    protected void paintComponent(Graphics g) {
        switch (tryb){

            case MENU: menuRender(g);
                break;
            case GAME: gameRender(g);
                break;
            case MGAME: mGameRender(g);
                break;
        }
        frames++;
    }
    Timer tm = new Timer(100,e -> repaint());
    Client client;
    private void mGameRender(Graphics g) {
        client.draw(g);
//        Timer timer1 = new Timer(100, e -> {
//            //Odbieranie
//        });
//        Timer timer2 = new Timer(100, e -> {
//            //Wysyłanie
//        });
    }


    private void gameRender(Graphics g) {
        g.drawImage(Textures.grass.getImage(),0,0,Board.MAX_X,Board.MAX_Y,null);
        for (Obstacle p : Spawner.przeszkody){
            p.draw(g);
        }
        Spawner.food.draw(g);
        for (Drawing d: drawings){
            d.draw(g);
            //System.out.println(snake.getBody().get(0).x + "x" + snake.getBody().get(0).y);
        }
    }


    private Direction upAndDown = Direction.UP;
    private void menuRender(Graphics g){
        g.drawImage(Textures.tlo.getImage(),0,0,Board.MAX_X,Board.MAX_Y,null);
        g.drawImage(Textures.jed.getImage(), 100, 300,640,80,null);
        g.drawImage(Textures.wie.getImage(), 100, 400,640,80,null);
        g.drawImage(Textures.exit.getImage(), 165,485,640,80,null);

        switch (upAndDown){

            case UP: g.drawImage(Textures.st.getImage(),740,300,80,80,null);
                break;
            case DOWN: g.drawImage(Textures.st.getImage(),740,400,80,80,null);
                break;
            case RIGHT: g.drawImage(Textures.st.getImage(),740,485,80,80,null);
                break;
            default:
                break;
        }
        repaint();
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

    private void actionPerformed(ActionEvent e) {
        System.out.println(frames);
        frames = 0;
    }

    private class KeyController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (tryb == Tryb.MENU){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (upAndDown == Direction.DOWN)
                            upAndDown = Direction.UP;
                        else if (upAndDown == Direction.RIGHT)
                            upAndDown = Direction.DOWN;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (upAndDown == Direction.UP)
                            upAndDown = Direction.DOWN;
                        else
                            upAndDown = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_ENTER:
                        if (upAndDown == Direction.UP) {
                            tryb = Tryb.GAME;
                            InputKey.reset();
                        }else if (upAndDown == Direction.RIGHT){
                            Main.view.dispose();
                        }
                        else if (upAndDown == Direction.DOWN) {
                            tryb = Tryb.MGAME;
                            client = new Client("88.156.231.130",6500,"Mech");
                            System.out.println("Łączenie ...");
                            addKeyListener(new InputKeyM(client));
                            tm.start();
                        }
                }
            }
        }
    }
}

