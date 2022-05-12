package com.mech.multiplayer;
;
import com.google.gson.Gson;
import com.mech.multiplayer.packets.PlayerPacket;
import com.mech.multiplayer.packets.PlayerPacketType;

public class EventListener {

    private final Client client;

    public EventListener(Client client) {
        this.client = client;
    }

    public void received(PlayerPacket packet){
        if (packet.getType() == PlayerPacketType.ADD){
            PlayerHandler.players.put(packet.getId(), new NetPlayer(packet.getId(),packet.getName()));
            System.out.println("Gracz " + packet.getName() + " Dołączył do Gry");
        }else if(packet.getType() == PlayerPacketType.REMOVE){
            System.out.println("Gracz " + packet.getName() + " Wyszedł z Gry");
            PlayerHandler.players.remove(packet.getId());
        } else if (packet.getType() == PlayerPacketType.ID) {
            if(!packet.getName().equals("ID")) throw new RuntimeException("Błędne Odpowiedzi Serwera");
            client.setId(packet.getId());
        } else if (packet.getType() == PlayerPacketType.MAP) {
            Gson gson = new Gson();
            Map map = gson.fromJson(packet.getPacket(),Map.class);
            map.draw(client.gr);
        }
    }
}
