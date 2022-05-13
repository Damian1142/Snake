package com.mech.input;

import com.google.gson.Gson;
import com.mech.Main;
import com.mech.multiplayer.Client;
import com.mech.multiplayer.packets.PlayerPacket;
import com.mech.multiplayer.packets.PlayerPacketType;
import com.mech.view.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputKeyM extends KeyAdapter {

    Gson gson;
    Client client;
    Direction direction = Direction.UP;

    public InputKeyM(Client client) {
        this.client = client;
        gson = new Gson();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W: {
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                    client.sendObject(new PlayerPacket(client.getId(), client.getName(), PlayerPacketType.DIR,gson.toJson(direction)));
                }
                break;
            }
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S: {
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                    client.sendObject(new PlayerPacket(client.getId(), client.getName(), PlayerPacketType.DIR,gson.toJson(direction)));
                }
                break;
            }
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A: {
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                    client.sendObject(new PlayerPacket(client.getId(), client.getName(), PlayerPacketType.DIR,gson.toJson(direction)));
                }
                break;
            }
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D: {
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                    client.sendObject(new PlayerPacket(client.getId(), client.getName(), PlayerPacketType.DIR,gson.toJson(direction)));
                }
                break;
            }
            case KeyEvent.VK_ESCAPE: {
                client.closeConnection();
                Main.view.mainPanel.goMenu();
                break;
            }
        }
    }
}
