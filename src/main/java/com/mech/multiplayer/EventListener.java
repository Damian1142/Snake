package com.mech.multiplayer;
;
import com.mech.packets.PlayerPacket;
import com.mech.packets.PlayerPacketType;

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
        }
    }
}
