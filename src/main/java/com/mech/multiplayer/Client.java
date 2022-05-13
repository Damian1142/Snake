package com.mech.multiplayer;

import com.google.gson.Gson;
import com.mech.Main;
import com.mech.Textures;
import com.mech.multiplayer.packets.PlayerPacket;
import com.mech.multiplayer.packets.PlayerPacketType;
import com.mech.view.Drawing;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client implements Runnable, Drawing {


    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running = false;
    private EventListener listener;
    private int id;
    private String name;

    @SneakyThrows
    public Client(String host, int port, String name) {
        this.host = host;
        this.port = port;
        Thread.sleep(2000);
        this.name = JOptionPane.showInputDialog(Main.view,"Podaj Nazwę","Podaj Nazwę", JOptionPane.INFORMATION_MESSAGE);
        connect();
    }

    @SneakyThrows
    public void connect(){
        socket = new Socket(host,port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        new Thread(this).start();
        listener = new EventListener(this);
        sendObject(new PlayerPacket(0,name,PlayerPacketType.ADD));
        System.out.println("Połączono");
    }

    @SneakyThrows
    public void closeConnection(){
        running = false;
        sendObject(new PlayerPacket(id,name, PlayerPacketType.REMOVE));
        in.close();
        out.close();
        socket.close();

    }

    @SneakyThrows
    public void sendObject(PlayerPacket packet){
        Gson gson = new Gson();
        if (running)
            out.writeObject(gson.toJson(packet));

    }

    @SneakyThrows
    @Override
    public void run() {
        running = true;
        while (running) {
            Gson gson = new Gson();
            PlayerPacket data = null;
            try {
                data = gson.fromJson((String) in.readObject(), PlayerPacket.class);
            } catch (SocketException ignored) {

            }
            if (data != null)
                listener.received(data);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void draw(Graphics g) {
        Map map = listener.maps.poll();
        if (map != null) {
            g.drawImage(Textures.grass.getImage(), 0, 0, null);
            map.draw(g);
        }
    }
}
